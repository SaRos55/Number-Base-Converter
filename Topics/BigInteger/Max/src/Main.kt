import java.math.BigInteger
import kotlin.math.abs

fun main() {
    val a = BigInteger(readln())
    val b = BigInteger(readln())
    print(myMax(a, b))
}

fun myMax(a: BigInteger, b: BigInteger): BigInteger {
    val myAbs = if (a - b < BigInteger.ZERO) -(a - b) else a - b
    return (a + b + myAbs) / BigInteger.TWO
}