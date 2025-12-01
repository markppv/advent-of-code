import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

fun String.md5() =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16).padStart(32, '0')

fun Any?.println() = println(this)

abstract class Solution(val day: Int, val expected1: Int, val expected2: Int) {
    abstract fun part1(input: List<String>): Int
    abstract fun part2(input: List<String>): Int
}

fun justRun(solution: Solution) {
    val paddedDay = solution.day.toString().padStart(2, '0')
    
    val testInput = readInput("Day${paddedDay}_test")
    check(solution.part1(testInput), solution.expected1)
    check(solution.part2(testInput), solution.expected2)

    val input = readInput("Day${paddedDay}")
    solution.part1(input).println()
    solution.part2(input).println()
}

private fun check(result: Int, expected: Int) =
    check(result == expected) {
        "$expected, got $result"
    }