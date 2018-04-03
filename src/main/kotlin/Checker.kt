import java.io.File

fun extractSufix(s: String): String =
        s.replace(" ", "").let {
            it.substring(it.lastIndexOf('('), it.lastIndexOf(')') + 1)
        }

fun isMP(s: String) = s.startsWith("(M.P.")

fun getNmbrs(s: String) = s.removePrefix("(M.P.").removeSuffix(")").split(",").map(String::toInt)

fun main(args: Array<String>) {
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