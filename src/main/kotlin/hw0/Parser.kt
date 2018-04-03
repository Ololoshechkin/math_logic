package hw0

private fun tokenize(s: String): List<String> {
    val tokens = arrayListOf<String>()
    val skip = {}
    val curLetter = StringBuilder()
    val cancelLetter = {
        if (curLetter.length != 0) {
            tokens.add(curLetter.toString())
            curLetter.setLength(0)
        }
    }
    for (i in 0 until s.length) {
        when (s[i]) {
            '!', '&', '|', '(', ')' -> cancelLetter().also { tokens.add(s[i].toString()) }
            '-' -> cancelLetter().also { tokens.add("->") }
            '>' -> skip()
            else -> curLetter.append(s[i])
        }
    }
    cancelLetter()
    return tokens
}

var left = 0
var tokens: List<String> = listOf()
fun skipToken() {
    left++
}

fun isEnd() = left == tokens.size
fun curToken() = tokens[left]

private fun parseNeg(): NodeWrapper {
    when (curToken()) {
        "(" -> {
            skipToken() //   (
            val exp = parseExpr()
            skipToken() //   )
            return exp
        }
        "!" -> {
            skipToken() //   !
            return Negation(parseNeg())
        }
        else -> return Letter(curToken()).also { skipToken() }
    }
}

private fun parseConj(): NodeWrapper {
    val neg = parseNeg()
    if (!isEnd() && curToken() == "&") {
        skipToken() //  &
        return Conjunction(neg, parseNeg())
    }
    return neg
}

private fun parseDisj(): NodeWrapper {
    val conj = parseConj()
    if (!isEnd() && curToken() == "|") {
        skipToken()
        return Disjunction(conj, parseConj())
    }
    return conj
}

private fun parseExpr(): NodeWrapper {
    val disj = parseDisj()
    if (!isEnd() && curToken() == "->") {
        skipToken()
        return Implication(disj, parseExpr())
    }
    return disj
}


fun String.parse(): NodeWrapper {
//    println("s : $this")
    tokens = tokenize(this)
//    println("tokens : $tokens")
    left = 0
//    val res = parseExpr()
//    fun nodeToString(exp: NodeWrapper): String =
//            when (exp) {
//                is Disjunction -> "(|, ${nodeToString(exp.left)}, ${nodeToString(exp.right)})"
//                is Conjunction -> "(&, ${nodeToString(exp.left)}, ${nodeToString(exp.right)})"
//                is Implication -> "(->, ${nodeToString(exp.left)}, ${nodeToString(exp.right)})"
//                is Negation -> "(!, ${nodeToString(exp.child)})"
//                is Letter -> exp.letter
//                else -> "_#_"
//            }
//    println("res : ${nodeToString(res)}")
    return parseExpr()
}

//val grammar = object : Grammar<NodeWrapper>() {
//    val letter by token("[A-Z]([A-Z0-9])*")
//    val not by token("!")
//    val and by token("&")
//    val or by token("\\|")
//    val ws by token("\\s+", ignore = true)
//    val implication by token("->")
//    val open by token("\\(")
//    val close by token("\\)")
//
//    val term: Parser<NodeWrapper> by
//    (letter use { Letter(text) }) or
//            (-not * parser(this::term) map { Negation(it) }) or
//            (-open * parser(this::rootParser) * -close)
//
//    val conChain by leftAssociative(term, and) { l, _, r -> Conjunction(l, r) }
//    val disChain by leftAssociative(conChain, or) { l, _, r -> Disjunction(l, r) }
//    override val rootParser by rightAssociative(disChain, implication) { l, _, r -> Implication(l, r) }
//}