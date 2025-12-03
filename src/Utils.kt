import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

fun readInput(name: String, delimiter: Char) =
    Path("src/$name.txt").readText().trim()
        .split(delimiter)

fun String.md5() =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16).padStart(32, '0')

fun Any?.println() = println(this)
fun <T> T.alsoPrintln(): T = also { println(it) }

abstract class Solution(
    val day: Int,
    val delimiter: Char = '\n',
    val expected1: Long,
    val expected2: Long,
) {
    abstract fun part1(input: List<String>): Long
    abstract fun part2(input: List<String>): Long
}

fun justRun(solution: Solution) {
    val paddedDay = solution.day.toString().padStart(2, '0')
    
    val testInput = readInput("Day${paddedDay}_test", solution.delimiter)
    check(solution.part1(testInput), solution.expected1)
    check(solution.part2(testInput), solution.expected2)

    val input = readInput("Day${paddedDay}", solution.delimiter)
    solution.part1(input).println()
    solution.part2(input).println()
}

private fun check(result: Long, expected: Long) =
    check(result == expected) {
        "$expected, got $result"
    }