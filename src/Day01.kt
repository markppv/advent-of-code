import kotlin.math.abs

fun main() = justRun(
    object : Solution(day = 1, expected1 = 3, expected2 = 6) {
        override fun part1(input: List<String>): Long {
            var count = 0L
            var curr = 50

            for (line in input) {
                var num = line.drop(1).toInt()
                if (line[0] == 'L') num = -num
                curr = (curr + num).mod(100)
                if (curr == 0) count++
            }

            return count
        }

        override fun part2(input: List<String>): Long {
            var count = 0L
            var curr = 50

            for (line in input) {
//            repeat(line.drop(1).toInt()) {
//                curr = (curr + if (line[0] == 'L') -1 else 1).mod(100)
//                if (curr == 0) count++
//            }
                var num = line.drop(1).toInt()
                if (line[0] == 'L') num = -num
                val next = curr + num
                count += abs(next) / 100
                if (next <= 0 && curr != 0) count++
                curr = next.mod(100)
            }

            return count
        }
    }
)