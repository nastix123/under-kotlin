package org.example

import ExpensiveComputation
import LoggingDelegate
import TranslatedText
import transformersFunction
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


fun main() {

    var name by LoggingDelegate("Default Name")
    var age by LoggingDelegate(0)

    val smth by lazy {
        mutableMapOf(1 to 1)
    }


    val text = TranslatedText()
    text.text = "Hello, world!"
    println(text.text)
    smth[2] = 2

    name = "Hello"
    age += 10

    val computation = ExpensiveComputation()
    println(computation.result)
    println(computation.result)

//    transformersFunction()
}





