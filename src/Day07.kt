fun main() = justRun(
    object : Solution(day = 7, expected1 = 21, expected2 = 40) {
        override fun part1(input: List<String>): Long {
            val used = HashSet<Pair<Int, Int>>()

            fun dfs(i: Int, j: Int): Int {
                if (i == input.size || !used.add(i to j)) return 0

                return if (input[i][j] == '.') {
                    dfs(i + 1, j)
                } else { // split
                    1 + dfs(i + 1, j - 1) + dfs(i + 1, j + 1)
                }
            }

            return dfs(1, input.first().indexOf('S')).toLong()
        }

        override fun part2(input: List<String>): Long {
            val memo = HashMap<Pair<Int, Int>, Long>()

            fun dfs(i: Int, j: Int): Long {
                if (i == input.size) return 1L

                return memo.getOrPut(i to j) {
                    if (input[i][j] == '.') {
                        dfs(i + 1, j)
                    } else { // split
                        dfs(i + 1, j - 1) + dfs(i + 1, j + 1)
                    }
                }
            }

            return dfs(1, input.first().indexOf('S'))
        }
    }
)
