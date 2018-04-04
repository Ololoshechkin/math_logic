package hw0

private var _tokens = arrayListOf<String>()
private val skip = {}
private val curLetter = StringBuilder()

private fun tokenize(s: String): List<String> {
    _tokens.clear()
    val cancelLetter = {
        if (curLetter.length != 0) {
            _tokens.add(curLetter.toString())
            curLetter.setLength(0)
        }
    }
    for (i in 0 until s.length) {
        when (s[i]) {
            '!', '&', '|', '(', ')' -> cancelLetter().also { _tokens.add(s[i].toString()) }
            '-' -> cancelLetter().also { _tokens.add("->") }
            '>', ' ', '\n', '\t', '\r' -> skip()
            else -> curLetter.append(s[i])
        }
    }
    cancelLetter()
    return _tokens
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
    var cur = parseNeg()
    while (!isEnd() && curToken() == "&") {
        skipToken() //  &
        cur = Conjunction(cur, parseNeg())
    }
    return cur
}

private fun parseDisj(): NodeWrapper {
    var cur = parseConj()
    while (!isEnd() && curToken() == "|") {
        skipToken() //  |
        cur = Disjunction(cur, parseConj())
    }
    return cur
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
    tokens = tokenize(this)
    left = 0
    return parseExpr()
}