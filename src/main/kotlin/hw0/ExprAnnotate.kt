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

val indexByNode = hashMapOf<NodeWrapper, Int>()
val indexByReasonNode = hashMapOf<NodeWrapper, Int>()
val indexByAssumptionNode = hashMapOf<NodeWrapper, Int>()

typealias Reason = NodeWrapper

val assumptions = hashSetOf<NodeWrapper>()
val reasons = hashMapOf<NodeWrapper, HashSet<Reason>>()

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
                        ass.parser().expression().node.also { indexByAssumptionNode[it] = id }
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
            && node.opcode == expectedNode.opcode
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
    if (reasons.containsKey(expr)) {
        for (reason in reasons[expr]!!) {
            val reasonIndex = indexByNode[reason]
            if (reasonIndex != null)
                return MP(indexByReasonNode[reason]!!, indexByNode[reason]!!)
        }
    }
    return null
}

fun checkAssumption(expr: NodeWrapper) =
        if (assumptions.contains(expr))
            Assumption(indexByAssumptionNode[expr]!! + 1)
        else null

fun setTrue(expr: NodeWrapper, index: Int) {
    indexByNode.putIfAbsent(expr, index)
}

fun tryAddToImplications(expr: NodeWrapper, index: Int) {
    if (expr is Implication) {
        val consequence = expr.right
        val reason = expr.left
        reasons.putIfAbsent(consequence, hashSetOf())
        reasons[consequence]!!.add(reason)
        indexByReasonNode[reason] = index
    }
}

fun annotateLine(line: String, index: Int): AnnotationInfo {
    val expr = line.parser().expression().node
    val result = checkAssumption(expr) ?: checkAxiom(expr) ?: checkModusPonens(expr)
    setTrue(expr, index)
    tryAddToImplications(expr, index)
    return result ?: None()
}