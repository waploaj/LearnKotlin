package functionAndVariable

import java.io.File

fun main(){
    watus.filter { it.age == watus.maxByOrNull(Watu::age)?.age?:0 }

    println(watus.find(canBeInClub))
    println(watus.all(canBeInClub))
    println(watus.count(canBeInClub))
    println(watus.any(canBeInClub))
    println(watus.firstOrNull(canBeInClub))
    println(watus.groupBy { it.age })
    println(watus.groupBy(canBeInClub))
    println(trings.map { it.toList() })
    println(trings.flatMap { it.toList() })
    println(vitabu.flatMap { it.author })
    println(vitabu.flatMap { it.author }.toSet())
    println(pipo.asSequence()
        .filter { it.age >12 }
        .map(Persons::name)
        .toList())
    println(naturalUpto100.sum())
    println(file.isHiddenDirectory())
    println(strLength(null))
    println(stringLenght(null))
    run { printAllCaps(null) }
    println(managerName(developer))
    println(mtu.getCountryName())
    println(mtuMwengine.getShippingLabel())
    run {  }

}

data class Watu(val name:String, val age:Int)
data class Books(val title:String, val author:List<String>)

val watus = listOf(Watu("hadija", 23), Watu("naima",21), Watu("Thabit", 21))
val trings = listOf("abc", "def")
val vitabu = listOf(Books("Kotlin in Action", listOf("dmitriy", "skolovia")),
    Books("Complete mathematicd", listOf("msechu","skolovia")))

val naturalNumber = generateSequence(0){it + 1}
val naturalUpto100 = naturalNumber.takeWhile { it<100 }
val file = File("/Users/svtk/.HiddenDir/a.txt")