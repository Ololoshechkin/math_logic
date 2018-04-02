package hw0

val hashes = hashMapOf<ArrayList<Int>, Int>()

open class NodeWrapper(val hash: Int) {

    override fun hashCode() = hash

    override fun equals(other: Any?) = other is NodeWrapper && hash == other.hash

}

fun calcHash(arr: ArrayList<Int>): Int {
    hashes.putIfAbsent(arr, hashes.size)
    return hashes[arr]!!
}

sealed class Binary(opcode: Int, val left: NodeWrapper, val right: NodeWrapper) : NodeWrapper(
        hash= calcHash(arrayListOf(opcode, left.hash, right.hash))
)
class Disjunction(left: NodeWrapper, right: NodeWrapper) : Binary(1, left, right)
class Conjunction(left: NodeWrapper, right: NodeWrapper) : Binary(2, left, right)
class Implication(left: NodeWrapper, right: NodeWrapper) : Binary(3, left, right)
class Negation(val child: NodeWrapper) : NodeWrapper(hash= calcHash(arrayListOf(4, child.hash)))
class Letter(val letter: String) : NodeWrapper(hash=calcHash(arrayListOf(letter.hashCode())))