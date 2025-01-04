import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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
): ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        //здесь условно имитация перевода текста
        return "$value + in English" as T
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this.value = value
    }


}
