import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun main(): Unit = coroutineScope {
    val persons = listOf(
        Person("Олег",23),
        Person("Ира",24),
        Person("Иван",21),
        Person("Маша",28),
        Person("Никита",26)
    )
    val weathers = listOf(
        Weather("Москва","Большой и красивый","+25"),
        Weather("Питер","Культурный, красивые белые ночи","+19"),
        Weather("Сочи","Теплый, курортный город","+31"),
        Weather("Владимир","Краеведческий, имеющий историческую ценность","+17"),
    )
    val numbers = listOf(
        (1..100).random(),
        (1..100).random(),
        (1..100).random(),
        (1..100).random(),
        (1..100).random(),
        (1..100).random(),
        (1..100).random(),
        (1..100).random(),
        (1..100).random(),
        (1..100).random()
    )

    val weatherDownloadList = async { downloadList(weathers) }
    val personDownloadList = async { downloadList(persons) }
    val randomDownload = async { downloadList(numbers) }

    val weatherList = weatherDownloadList.await()
    val personList = personDownloadList.await()
    val randoms = randomDownload.await()
    println("\nДанные загружены\n")

    println(weatherList)
    println(personList)
    println(randoms)
    println("\nПрограмма завершена\n")

}

suspend fun <T> downloadList(lists:List<T>):List<T>{
    for (i in lists) {
        delay(1000L)
        println(i)
    }
    return lists
}

data class Person(val name:String, val age:Int)
data class Weather(val city:String, val description:String, val temp:String)