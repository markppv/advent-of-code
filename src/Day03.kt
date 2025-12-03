fun main() = justRun(
    object : Solution(day = 3, expected1 = 357, expected2 = 3121910778619) {
        override fun part1(input: List<String>): Long {
            return solve(input, 2)
        }

        override fun part2(input: List<String>): Long {
            return solve(input, 12)
        }

        private fun solve(input: List<String>, n: Int): Long {
            return input
                .map { it.map { it.digitToInt() } }
                .sumOf { bank ->
                    (0 until n).fold(
                        initial = IndexedValue(-1, 0L)
                    ) { (lastIndex, acc), i ->
                        bank
                            .withIndex()
                            .drop(lastIndex + 1)
                            .dropLast(n - i - 1)
                            .maxBy { it.value }
                            .let {
                                IndexedValue(
                                    index = it.index,
                                    value = acc * 10 + it.value,
                                )
                            }
                    }.value
                }
        }
    }
)
