fun main() = justRun(
    object : Solution(day = 5, expected1 = 3, expected2 = 14) {
        override fun part1(input: List<String>): Long {
            val emptyIdx = input.indexOf("")

            val ranges = input
                .take(emptyIdx)
                .map { it.split('-').let { (a, b) -> a.toLong() .. b.toLong() } }

            return input
                .drop(emptyIdx + 1)
                .map { it.toLong() }
                .count { id -> ranges.any { range -> id in range } }
                .toLong()
        }

        override fun part2(input: List<String>): Long {
            val emptyIdx = input.indexOf("")

            val ranges = input
                .take(emptyIdx)
                .map { it.split('-').let { (a, b) -> a.toLong() .. b.toLong() } }
                .sortedBy { it.first }

            var prevLast = 0L

            return ranges.sumOf {
                if (it.last > prevLast) {
                    val count = it.last - maxOf(prevLast + 1, it.first) + 1
                    prevLast = it.last
                    count
                } else {
                    0
                }
            }
        }
    }
)
