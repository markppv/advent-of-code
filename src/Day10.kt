import java.util.LinkedList

fun main() = justRun(
    object : Solution(day = 10, expected1 = 7, expected2 = 0) {
        override fun part1(input: List<String>): Long {
            return input.sumOf { query ->
                val splitted = query.split(' ')

                val n = splitted.first().length - 2

                val target = splitted.first()
                    .drop(1).dropLast(1)
                    .map { if (it == '.') 0 else 1 }
                    .reduce { acc, i -> (acc shl 1) + i }

                val buttons = splitted
                    .drop(1).dropLast(1)
                    .map {
                        it.drop(1).dropLast(1)
                            .split(',')
                            .sumOf { 1 shl n - it.toInt() - 1 }
                    }

                val q = LinkedList<Int>().also { it += 0 }
                val visited = HashSet<Int>()
                var steps = 0

                while (q.isNotEmpty()) {
                    steps++

                    repeat (q.size) {
                        val curr = q.removeFirst()

                        for (button in buttons) {
                            val next = curr xor button
                            if (next == target) return@sumOf steps
                            if (visited.add(next)) q += next
                        }
                    }
                }

                error("")
            }.toLong()
        }

        override fun part2(input: List<String>): Long {
            return 0L
        }
    }
)
