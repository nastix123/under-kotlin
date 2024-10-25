
fun transformersFunction() {
    //any|all
    val numbers = listOf(2,10,100,3838)
    val isAny = numbers.any()
    val isAnyOdd = numbers.any{ it % 2 == 0 }
    println(" Have at least one number: $isAny,Have at least on odd number: $isAnyOdd")
    
    val isAllEqual = numbers.all { it > 1 }// проверяет все числа
    val isAnyFor = numbers.none { it == 1000 } // есть ли здесь тысяча, если нет то true

    //map
    val doubles: List<Double?> = listOf(3.0, 5.3, 8.1, null, 9.0)
    val newList1 = doubles.map { it?.minus(1.0) }
    val newList2 = doubles.mapIndexed {index, it -> "${index+1} ${it?.minus(1.0)}"} //добавляем индекс
    println(newList1)
    println(newList2)
    println(doubles.mapNotNull { it?.minus(1.0) })

    //flatMap
    val list: List<List<Int>> = listOf(listOf(1,2,3), listOf(5,6,7))
    val newList = list.flatMap { it } // для более сложных случаев трансформации (сложнее чем объединение списков)
    val newListFlatten = list.flatten() // для более простых случаев, под копотом создает новый Array, через for загоняет туда все элементы и возвращает
    println("New list with flat map $newList")
    println("New list with flatten $newListFlatten")

    //groupBy
    val groupList = listOf(1,5,23,546,20)
    val newGroupList = groupList.groupBy {
        when {
            it == 1 -> "this element is 1"
            it > 20 -> "This element more then 20"
            else -> "Unknown"
        }
    } // возвращает мапу с типом Map<String, List<Int>>
    println(newGroupList)


    //sequence
//    val darkColor = generateSequence("name of color") {
//
//    }.first()
//
    // мы получаем следующее значение до тех пор, пока в first мы не найдем то, что подходит нам под условие
    //является более эффективным алгоритмом с sequence

    //в этом примере скачаются все страницы
    val catPage = listOf(
        "sjdfhkd",
        "asdfasdf",
        "awsdfsadfas"
    ).map { fetchPage() }
    .first { isCat(it) }

    //в этом каждая страница будет проверятся по очереди, скачается только первая страница, если она не подходит, то следующая и тд.
    val catPageSec = sequenceOf(
        "sjdfhkd",
        "asdfasdf",
        "awsdfsadfas"
    ).map { fetchPage() }
    .first { name ->
        isCat(name)
    }
}
//для примера
fun fetchPage() {

}

//для примера
fun isCat(isCat: Unit): Boolean {
    return true
}
