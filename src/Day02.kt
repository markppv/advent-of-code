fun main() = justRun(
    object : Solution(day = 2, delimiter = ',', expected1 = 1227775554, expected2 = 4174379265) {
        override fun part1(
            input: List<String>
        ) = solve(input) {
            val s = it.toString()

            if (s.length % 2 != 0) return@solve false

            for (i in 0 until s.length / 2)
                if (s[i] != s[s.length / 2 + i])
                    return@solve false

            true
        }

        override fun part2(
            input: List<String>
        ) = solve(input) {
            val s = it.toString()

            for (n in s.length / 2 downTo 1) {
                if (s.substring(0, n).repeat(s.length / n) != s) continue
                return@solve true
            }

            false
        }

        private fun solve(
            input: List<String>,
            check: (Long) -> Boolean,
        ) = input.sumOf { range ->
            range
                .split('-')
                .map { it.toLong() }
                .let { (a, b) -> a..b }
                .filter { check(it) }
                .sum()
        }
    }
)
