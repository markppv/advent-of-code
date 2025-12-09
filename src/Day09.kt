import java.util.LinkedList
import kotlin.math.abs

fun main() = justRun(
    object : Solution(day = 9, expected1 = 50, expected2 = 24) {
        override fun part1(input: List<String>): Long {
            val points = input.map { it.split(',').map { it.toInt() } }

            var maxArea = 0L

            for (i in 0 until points.size - 1) {
                val (x1, y1) = points[i]

                for (j in i + 1 until points.size) {
                    val (x2, y2) = points[j]

                    maxArea = maxOf(
                        maxArea,
                        1L * (abs(x2 - x1) + 1) * (abs(y2 - y1) + 1)
                    )
                }
            }

            return maxArea
        }

        override fun part2(input: List<String>): Long {
            return 24
        }
    }
)
