package converter

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import kotlin.math.pow

val alphabet = 'a'..'z'

fun main() {
    var sourceBase: Int
    var targetBase: Int
    while (true) {
        print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ")
        val input = readln().split(' ')
        if (input[0] == "/exit") {
            println()
            break
        } else {
            sourceBase = input[0].toInt()
            targetBase = input[1].toInt()
        }
        while (true) {
            print("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back) ")
            val inputNumber = readln()
            val isFractional = '.' in inputNumber || ',' in inputNumber
            val number = inputNumber.split(',', '.')[0]
            val fractional = if (isFractional) inputNumber.split(',', '.')[1] else ""
            if (number == "/back") break
            else
                print("Conversion result: ")
            val numToDec = convertToDec(number, sourceBase)
            val targetNum = convertFromDec(numToDec, targetBase.toBigInteger())
            val fractionalToDec = convertFractionalToDec(fractional, sourceBase)
            val targetFrag = convertFractionalFromDec(fractionalToDec, targetBase.toBigDecimal())

            println("$targetNum${if (isFractional) ".$targetFrag" else ""}")
            println()
        }
        println()
    }
}

fun convertToDec(source: String, base: Int): BigInteger {
    var result = BigInteger.ZERO
    for (i in source.length - 1 downTo 0) {
        val num = if (source[i].isDigit()) source[i].digitToInt()
        else alphabet.indexOf(source[i]) + 10
        result += BigInteger.valueOf((num * base.toDouble().pow(source.length - 1 - i)).toLong())
    }
    return result
}

fun convertFromDec(source: BigInteger, base: BigInteger): String {
    var result = source
    var remains: BigInteger
    var strResult = ""
    do {
        remains = result % base
        val strRemains = if (remains > BigInteger.valueOf(9)) alphabet.elementAt(remains.toInt() - 10).toString()
        else remains.toString()
        result /= base
        strResult = strRemains + strResult
    } while (result != BigInteger.ZERO)
    return strResult
}

fun convertFractionalToDec(source: String, base: Int): BigDecimal {
    var result = BigDecimal.ZERO
    for (i in source.indices) {
        val num = if (source[i].isDigit()) source[i].digitToInt()
        else alphabet.indexOf(source[i]) + 10
        result += BigDecimal.valueOf((num.toDouble() / base.toDouble().pow(i + 1)))
    }
    return result
}

fun convertFractionalFromDec(source: BigDecimal, base: BigDecimal): String {
    var strResult = ""
    var fractional = source
    do {
        val int = (fractional * base).setScale(0, RoundingMode.FLOOR)
        fractional = (fractional * base) - int

        val addStrResult = if (int < BigDecimal.TEN) int.toInt().toString() else alphabet.elementAt(int.toInt() - 10)
        strResult += addStrResult
    } while (fractional != BigDecimal.ZERO.setScale(fractional.scale()) && strResult.length < 5)
    if (strResult.length < 5) repeat(5 - strResult.length) {strResult += '0'}
    return strResult
}