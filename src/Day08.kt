import java.util.TreeMap
import kotlin.math.sqrt

fun main() = justRun(
    object : Solution(day = 8, expected1 = 40, expected2 = 25272) {
        override fun part1(input: List<String>): Long {
            val (_, pointsByDist, dsu) = prepare(input)

            val n = input.size
            val amount = if (n == 1000) n else 10
            pointsByDist.values.flatten().take(amount)
                .forEach { (i, j) -> dsu.union(i, j) }

            val ranks = (0 until n)
                .map { dsu.find(it) }
                .distinct()
                .map { dsu.rank[it] }
                .sorted()

            return ranks.takeLast(3).reduce { acc, x -> acc * x }.toLong()
        }

        override fun part2(input: List<String>): Long {
            val (points, pointsByDist, dsu) = prepare(input)

            for ((i, j) in pointsByDist.values.flatten()) {
                if (dsu.union(i, j) == input.size) {
                    return 1L * points[i].x * points[j].x
                }
            }

            return -1L
        }
    }
)

fun prepare(input: List<String>): Triple<List<Point>, TreeMap<Double, MutableList<Pair<Int, Int>>>, UnionFind> {
    val n = input.size

    val points = input
        .map { it.split(',').map { it.toInt() } }
        .map { (x, y, z) -> Point(x, y, z) }

    val pointsByDist = TreeMap<Double, MutableList<Pair<Int, Int>>>()

    for (i in 0 until n - 1) {
        for (j in i + 1 until n) {
            pointsByDist.getOrPut(
                points[i].distance(points[j])
            ) { ArrayList() } += i to j
        }
    }

    return Triple(points, pointsByDist, UnionFind(n))
}

data class Point(val x: Int, val y: Int, val z: Int) {
    fun distance(o: Point): Double {
        return sqrt(
            1.0 * (x - o.x) * (x - o.x) +
                    1.0 * (y - o.y) * (y - o.y) +
                    1.0 * (z - o.z) * (z - o.z)
        )
    }
}

class UnionFind(n: Int) {

    val parent = IntArray(n) { it }
    val rank = IntArray(n) { 1 }

    fun find(i: Int): Int {
        return parent[i].takeIf { it == i }
            ?: find(parent[i])
    }

    fun union(i: Int, j: Int): Int {
        val a = find(i)
        val b = find(j)

        if (a == b) return rank[a]

        val (smaller, larger) =
            if (rank[a] < rank[b]) {
                b to a
            } else {
                a to b
            }

        parent[smaller] = larger
        rank[larger] += rank[smaller]

        return rank[larger]
    }
}
