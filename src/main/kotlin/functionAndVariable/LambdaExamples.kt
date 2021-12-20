package functionAndVariable

import java.io.File

//Searching throught collection manually

data class Persons(val name:String, val age:Int)

fun findTheOldest(person:List<Persons>){
    var maxAge = 0
    var oldestPerson:Persons? = null
    for (perso in person){
        if (perso.age > maxAge){
            maxAge = perso.age
            oldestPerson = perso
        }
    }
    println(oldestPerson)
}

val pipo = listOf<Persons>(Persons("asha",21), Persons("naima",21))

//Store lambda expression as variable

val sum = {x:Int, y:Int -> x+y}

// Calling lambda directly

// run {println(42)}

//Using function parameter in a lambda
fun messageWithPrefix(message:Collection<String>, prefix:String){
    message.forEach { println("$message $prefix") }
}

//Chnaging local variable from a lambda

fun countingPromblems(response:Collection<String>){
    var clientErrors = 0
    var serverErrors = 0
    response.forEach {
        if (it.startsWith("4")){
            clientErrors++
        }else if (it.startsWith("5")){
            serverErrors++
        }
    }
    println("$clientErrors client errors occurs, $serverErrors server errors occurs")
}

//capture mutable value: Implemanting details

class Ref<T>(var value:T)
val counter = Ref(0)
val temp = { counter.value++}

//Notes
//if lambda is used as an event handler the modification of local varibale would occuer onlw when lamda is excuted
//Example

fun buttonClicked(button: Button):Int{
    var clicked = 0
    button::click
    return clicked
}

// A bound member reference that you can call

val sizeofPipo = pipo::size
val ageofPipo = Persons::age

val majina = pipo.filter { it.age == it.age }.map(Persons::name)
val wazee = pipo.filter { it.age == pipo.maxByOrNull(Persons::age)?:0}

val vikongwe = pipo.maxByOrNull(ageofPipo)?.age?:0

val canBeInClub = {p:Watu -> p.age <= 22}

//Lazy collection operation:Sequences

//    pipo.asSequence()
//        .filter { it.age >12 }
//        .map(Persons::name)
//        .toList()

//Generating and using a sequence of file

fun File.isHiddenDirectory() = generateSequence(this){it.parentFile}.any { it.isHidden }

//Lambda with receiver

fun alphabet():String{
    val result =  StringBuilder()
    for (item in 'A'..'Z'){
        result.append(item)
    }
    println("Now i know alphabet")
    return result.toString()
}

fun alphabetWithReceiver():String{
    val stringbuilder = StringBuilder()
    return with(stringbuilder){
        for (i in 'a'..'z'){
            this.append(i)
        }
        println("Now i know alpha bet")
        this.toString()
    }
}

//Refactor the alphabet function

fun alphabeti() = with(StringBuilder()){
    for (letter in 'a'..'z'){
        append(letter)
    }
    println("Now i know alphabet")
    toString()
}
//IF THE RECEIVER OBJECT THAT YOU PASS ON WITH HAS THE SAME METHOD AS OBJECT YOU USE this to refer to its
//Difference btn apply and with receiver is
//apply return the object that passed on it as parameter
//with return the excutation on object that passed on it as parameter

fun alphaeti() = StringBuilder().apply {
    for (letter in 'a'..'z'){
        append(letter)
    }
    println("Now i know the alphabet")
}.toString()

//We can use buildString
//The arguments of buldString is a lambda with receiver

fun alphabte() = buildString {
    for (letter in 'a'.. 'z'){
        append(letter)
    }
    println("Now i know alphabet")
}