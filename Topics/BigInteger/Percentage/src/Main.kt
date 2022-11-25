import java.math.BigInteger

fun main() {
    val a = BigInteger(readln())
    val b = BigInteger(readln())
    print("${a * BigInteger.valueOf(100) / (a + b)}% ${b * BigInteger.valueOf(100) / (a + b)}%")
}