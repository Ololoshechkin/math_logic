package hw0

import antlr.generated.ExpressionLexer
import antlr.generated.ExpressionParser
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

sealed class AnnotationInfo {
    abstract val number: Int
}

class Assumption(override val number: Int) : AnnotationInfo() {
    override fun toString() = "(Предп. $number)"
}

class Axiom(override val number: Int) : AnnotationInfo() {
    override fun toString() = "(Сх. акс. $number)"
}

class MP(override val number: Int, val number2: Int) : AnnotationInfo() {
    override fun toString() = "(M.P. $number, $number2)"
}

class None : AnnotationInfo() {
    override val number = -1
    override fun toString() = "(Не доказано)"
}

data class NodeWithIndex(val node: NodeWrapper, val index: Int) {
    override fun hashCode() = node.hashCode()
    override fun equals(other: Any?) = other is NodeWithIndex && other.node == node
}

typealias Reason = NodeWithIndex

val assumptions = hashSetOf<NodeWithIndex>()
val trueExpressions = hashSetOf<NodeWithIndex>()
val implications = hashMapOf<NodeWrapper, HashSet<Reason>>()

fun String.parser() = ExpressionParser(CommonTokenStream(ExpressionLexer(ANTLRInputStream(this))))

val axioms = File("axioms.txt")
        .readLines()
        .map { it.parser().expression().node }

fun processHeader(header: String) {
    val assumptionsEnd = header.indexOf("|-");
    if (assumptionsEnd < 1)
        return
    assumptions.addAll(
            header
                    .substring(0, assumptionsEnd)
                    .split(',')
                    .mapIndexed { id, ass ->
                        NodeWithIndex(
                                ass.parser().expression().node,
                                id
                        )
                    }
    )
}

val letterToNode = hashMapOf<String, NodeWrapper>()
fun isomorphismCheck(node: NodeWrapper, expectedNode: NodeWrapper, i: Int): Boolean = when (expectedNode) {
    is Letter -> {
        val letter = expectedNode.letter
        if (letterToNode.containsKey(letter))
            letterToNode[letter] == node
        else {
            letterToNode[letter] = node
            true
        }
    }
    is Binary -> node is Binary
            && isomorphismCheck(node.left, expectedNode.left, i)
            && isomorphismCheck(node.right, expectedNode.right, i)
    is Negation -> node is Negation && isomorphismCheck(node.child, expectedNode.child, i)
    else -> false
}

private fun checkAxiom(expr: NodeWrapper): AnnotationInfo? {
    for (i in 0 until axioms.size) {
        letterToNode.clear()
        if (isomorphismCheck(expr, axioms[i], i))
            return Axiom(i + 1)
    }
    return null
}

fun checkModusPonens(expr: NodeWrapper): AnnotationInfo? {
    if (implications.containsKey(expr)) {
        for (impl in implications[expr]!!) {
            val reason = trueExpressions.find { it == impl }
            if (reason != null)
                return MP(impl.index, reason.index)
        }
        return null
    } else return null
}

fun checkAssumption(expr: NodeWrapper) = assumptions
        .find { it.node == expr }
        ?.let { Assumption(it.index + 1) }

fun setTrue(expr: NodeWrapper, index: Int) = trueExpressions.add(NodeWithIndex(expr, index))

fun tryAddToImplications(expr: NodeWrapper, index: Int) {
    if (expr is Implication) {
        val consequence = expr.right
        implications.putIfAbsent(consequence, hashSetOf())
        implications[consequence]!!.add(NodeWithIndex(expr.left, index))
    }
}

fun annotateLine(line: String, index: Int): AnnotationInfo {
    val expr = line.parser().expression().node
    val result = checkAssumption(expr) ?: checkAxiom(expr) ?: checkModusPonens(expr)
    setTrue(expr, index)
    tryAddToImplications(expr, index)
    return result ?: None()
}