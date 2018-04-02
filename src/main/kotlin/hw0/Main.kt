package hw0

import java.io.File
import kotlin.streams.asSequence

val time = { System.currentTimeMillis().toDouble() }
val st = time()
val deltaTime = { (time() - st) / 1000.0 }

fun main(args: Array<String>) {
    File("files/output.txt").bufferedWriter().use { output ->
        File("files/17.in").bufferedReader().use { input ->
            input.lines().asSequence().filter { !it.isEmpty() }.forEachIndexed { index, line ->
                if (index == 0)
                    processHeader(line)
                else
                    output.append("($index) $line ${annotateLine(line, index)}\n")
            }
        }
    }
    println("time : ${deltaTime()}")
}