import java.math.BigDecimal
import java.math.RoundingMode

fun main() {             
    val number = readln().toBigDecimal()
    val newscale = readln().toInt()

    println(number.setScale(newscale, RoundingMode.HALF_DOWN))

}