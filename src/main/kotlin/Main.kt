package org.example

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
    age +=10

    val computation = ExpensiveComputation()
    println(computation.result)
    println(computation.result)

//    transformersFunction()
}

class TranslatedText {
    var text: String by TranslateDelegate("en")
}

class ExpensiveComputation {
    val result: Int by CacheDelegate { computeSomethingExpensive() }

    private fun computeSomethingExpensive(): Int {
        println("Computing...")
        return (1..100).sum()
    }
}



class LoggingDelegate<T> (
    private var value: T
): ReadWriteProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        println("propertie: ${property.name}, old value: ${this.value}")
        this.value = value
    }

}

class CacheDelegate<T>(
    private var value: T? = null,
    private val action: () -> T) : ReadOnlyProperty<Any?, T?>
{
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (value == null) {
            value = action.invoke()
            return value!!
        } else return value!!
    }

}

@Suppress("UNCHECKED_CAST")
class TranslateDelegate<T>(
    private var value: T
): ReadWriteProperty<Any,T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        //здесь условно имитация перевода текста
        return "$value + in English" as T
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this.value = value
    }


}




