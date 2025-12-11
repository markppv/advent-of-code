import java.util.HashSet

fun main() = justRun(
    object : Solution(day = 11, name2 = "test2", expected1 = 5, expected2 = 2) {
        override fun part1(input: List<String>): Long {
            val map = input.associate {
                it.split(": ").let { (a, b) ->
                    a to b.split(' ')
                }
            }

            fun dfs(key: String): Int = when (key) {
                "out" -> 1
                else -> map[key]!!.sumOf { dfs(it) }
            }

            return dfs("you").toLong()
        }

        override fun part2(input: List<String>): Long {
            val map = input.associate {
                it.split(": ").let { (a, b) ->
                    a to b.split(' ')
                }
            }

            fun count(from: String, to: String): Long {
                val memo = HashMap<String, Long>()
                fun dfs(key: String): Long = when (key) {
                    to -> 1L
                    else -> memo.getOrPut(key) {
                        map[key]
                            ?.sumOf { dfs(it) }
                            ?: 0L
                    }
                }
                return dfs(from)
            }

            var result = count("svr", "fft")
            result *= count("fft", "dac")
            result *= count("dac", "out")
            return result
        }
    }
)
