package hw0

import java.io.File

//val time = { System.currentTimeMillis().toDouble() }
//val st = time()
//val deltaTime = { (time() - st) / 1000.0 }

fun main(args: Array<String>) {
//    File("files/output.txt").bufferedWriter().use { output ->
//        val lines = File("/Users/Vadim/Downloads/17.in").readLines()
    File("output.txt").bufferedWriter().use { output ->
        val lines = File("input.txt").readLines()
        for (index in 0 until lines.size)  {
            val line = lines[index]
            if (!line.isEmpty()) {
                if (index == 0)
                    processHeader(line)
                else
                    output.append("($index) $line ${annotateLine(line, index)}\n")
            }
        }
    }
//    println("time : ${deltaTime()}")
}