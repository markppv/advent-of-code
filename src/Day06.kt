fun main() = justRun(
    object : Solution(day = 6, expected1 = 4277556, expected2 = 3263827) {
        override fun part1(input: List<String>): Long {
            val splittedInput = input.map { it.trim().split("\\s+".toRegex()) }

            val nums = splittedInput
                .dropLast(1)
                .map { it.map { it.toLong() } }

            val ops = splittedInput.last().map {
                if (it == "*") Multiplication else Addition
            }

            return ops.mapIndexed { i, op ->
                nums.drop(1).fold(nums[0][i]) { acc, row -> op(acc, row[i]) }
            }.sum()
        }

        override fun part2(input: List<String>): Long {
            var sum = 0L

            var currOp: Op? = null
            val values = ArrayList<Long>()

            val maxLen = input.maxOf { it.length }
            val paddedInput = input.map { it.padEnd(maxLen) }

            for ((i, op) in paddedInput.last().withIndex()) {
                if (op != ' ') {
                    currOp?.let { sum += values.reduce(it) }
                    currOp = if (op == '*') Multiplication else Addition
                    values.clear()
                }

                var value = 0L

                for (j in 0 until paddedInput.size - 1) {
                    val c = paddedInput[j][i].takeIf { it.isDigit() } ?: continue
                    value = value * 10 + c.digitToInt().toLong()
                }

                if (value != 0L) values += value
            }

            currOp?.let { sum += values.reduce(it) }

            return sum
        }

        private val Addition = { a: Long, b: Long -> a + b }
        private val Multiplication = { a: Long, b: Long -> a * b }
    }
)

typealias Op = (Long, Long) -> Long