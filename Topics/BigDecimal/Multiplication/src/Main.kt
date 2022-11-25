import java.math.BigDecimal
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val (a, b) = List(2) { scanner.nextBigDecimal() }
    print(a * b)

}