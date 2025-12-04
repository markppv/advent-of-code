fun main() = justRun(
    object : Solution(day = 4, expected1 = 13, expected2 = 43) {
        override fun part1(input: List<String>): Long {
            return input.toMatrix().removeAccessible().toLong()
        }

        override fun part2(input: List<String>): Long {
            return input.toMatrix().let { mat ->
                generateSequence {
                    mat.removeAccessible().takeIf { it > 0 }
                }.sum().toLong()
            }
        }

        private fun List<String>.toMatrix(): Array<BooleanArray> {
            return Array(size) { i -> BooleanArray(first().length) { j -> this[i][j] == '@' } }
        }

        private fun Array<BooleanArray>.removeAccessible(): Int {
            val m = size
            val n = first().size

            val removed = Array(m) { BooleanArray(n) }
            var removedCount = 0

            for (i in 0 until m) {
                for (j in 0 until n) {
                    if (this[i][j] && check(i, j)) {
                        removed[i][j] = true
                        removedCount++
                    }
                }
            }

            for (i in 0 until m) {
                for (j in 0 until n) {
                    if (removed[i][j]) this[i][j] = false
                }
            }

            return removedCount
        }

        private fun Array<BooleanArray>.check(x: Int, y: Int): Boolean {
            val m = size
            val n = first().size

            return dirs
                .map { (dx, dy) -> intArrayOf(x + dx, y + dy) }
                .filter { (nx, ny) -> nx >= 0 && nx < m && ny >= 0 && ny < n }
                .count { (nx, ny) -> this[nx][ny] } < 4
        }

        private val dirs = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(-1, 1),
            intArrayOf(1, -1),
            intArrayOf(-1, -1),
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
            intArrayOf(0, 1),
            intArrayOf(0, -1),
        )
    }
)
