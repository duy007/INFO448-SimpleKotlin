package edu.uw.basickotlin

import java.lang.Exception

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String

fun whenFn(k: Any) : String {
    var result: String = "I don't understand"
    if (k is String) {
        result = when (k) {
            "Hello" -> "world"
            else -> "Say what?"
        }
    }
    if (k is Int) {
        result = when (k) {
            0 -> "zero"
            1 -> "one"
            in 2..10 -> "low number"
            else -> "a number"

        }
    }
    return result;
}

// write an "add" function that takes two Ints, returns an Int, and adds the values

fun add(x: Int, y: Int): Int {
    return x + y;
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values

fun sub(x: Int, y: Int): Int {
    return x - y;
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(x: Int, y: Int, op: (Int, Int) -> Int): Int {
    return op(x, y);
}
// write a class "Person" with first name, last name and age
class Person(
        val firstName: String,
        val lastName: String,
        val age: Int
) {
    val debugString: String = "[Person firstName:${firstName} lastName:${lastName} age:${age}]";
}
// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money() {
    var amount: Int = 0
    lateinit var currency: String
    private val currencyList: List<String> = listOf("USD", "EUR", "CAN" ,"GBP")

    constructor(amount: Int, currency: String) : this() {
        if (currency in currencyList && amount >= 0) {
            this.amount = amount
            this.currency = currency
        } else {
            throw IllegalArgumentException("Type mismatch")
        }
    }

    /*
    10 USD converts to 5 GBP (2 USD == 1 GBP)

    10 USD converts to 15 EUR (2 USD == 3 EUR)

    12 USD converts to 15 CAN (4 USD == 5 CAN)
    */
    fun convert(currency: String) : Money {
        if (currency !in currencyList) {
            throw Exception("not available currency")
        }
        var amount: Int = this.amount
        if (this.currency == "USD") {
            amount = when (currency) {
                "GBP" -> amount / 2
                "EUR" -> amount / 2 * 3
                else -> {
                    amount / 4 * 5
                }
            }
        }
        if (this.currency == "GBP") {
            amount = when (currency) {
                "USD" -> amount * 2
                "EUR" -> amount * 3
                else -> {
                    amount * 2 / 4 * 5
                }
            }
        }
        if (this.currency == "EUR") {
            amount = when (currency) {
                "USD" -> amount / 3 * 2
                "GBP" -> amount / 3
                else -> {
                    amount / 3 * 2 / 4 * 5
                }
            }
        }
        if (this.currency == "CAN") {
            amount = when (currency) {
                "USD" -> amount / 5 * 4
                "GBP" -> amount / 5 * 4 / 2
                else -> {
                    amount / 5 * 4 / 3 * 2
                }
            }
        }
        return Money(amount, currency);
    }

    operator fun plus(increment: Money) : Money {
        // check if currency is the same, if not convert
        if (increment.currency != this.currency) {
            val convertedValue = increment.convert(this.currency)
            return Money(convertedValue.amount+this.amount, this.currency)
        }
        // return new amount
        return Money(increment.amount+this.amount, this.currency)
    }
}