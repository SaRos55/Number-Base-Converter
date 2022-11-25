import java.math.BigDecimal
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val (dwalin, balin, thorin) = List<BigDecimal>(3) { scanner.nextBigDecimal() }
    print("${dwalin + balin + thorin}")
}