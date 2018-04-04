import hw0.*
import java.io.File

fun extractSufix(s: String): String =
        s.replace(" ", "").let {
            it.substring(it.lastIndexOf('('), it.lastIndexOf(')') + 1)
        }

fun isMP(s: String) = s.startsWith("(M.P.")

fun getNmbrs(s: String) = s.removePrefix("(M.P.").removeSuffix(")").split(",").map(String::toInt)

fun check16() {
    File("files/output.txt").useLines { lines ->
        val lines2 = File("/Users/Vadim/Downloads/10.out").readLines()
        lines.forEachIndexed { i, s ->
            val suf1 = extractSufix(s)
            val suf2 = extractSufix(lines2[i])
            if (suf1 != suf2 && isMP(suf1)) {
                val nmbs = getNmbrs(suf1)
                if (nmbs[0] <= nmbs[1])
                    println("line : $i. expected \"$suf2\", found \"$suf1\"")
            }
        }
    }
}

fun NodeWrapper.toStr(): String = when (this) {
    is Implication -> "(->, ${left.toStr()}, ${right.toStr()})"
    is Disjunction -> "(|, ${left.toStr()}, ${right.toStr()})"
    is Conjunction -> "(&, ${left.toStr()}, ${right.toStr()})"
    is Negation -> "(!, ${child.toStr()})"
    is Letter -> letter
    else -> "###"
}

fun checkParser() {
//    val expr = "A -> B -> C&D|(EX->F15)|G&N->H&M"
    val expr = "P ->  ! QQ   ->!R10&S|!T&U&V"
    expr.parse().toStr().let(::println)
}

fun main(args: Array<String>) {
    checkParser()
}