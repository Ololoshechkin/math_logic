package hw0

import java.io.File

//val time = { System.currentTimeMillis().toDouble() }
//val st = time()
//val deltaTime = { (time() - st) / 1000.0 }

fun main(args: Array<String>) {
    File("output.txt").bufferedWriter().use { output ->
        File("input.txt").bufferedReader().use { input ->
            var index = 0
            input.lines().filter { !it.isEmpty() }.forEach { line ->
                if (index == 0)
                    processHeader(line)
                else
                    output
                            .append('(')
                            .append(index.toString())
                            .append(") ")
                            .append(line)
                            .append(' ')
                            .append(annotateLine(line, index).toString())
                            .append('\n')
                index++
            }
        }
    }
//    println("time : ${deltaTime()}")
}