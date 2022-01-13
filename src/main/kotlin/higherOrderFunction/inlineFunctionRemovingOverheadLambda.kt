package higherOrderFunction

import java.io.BufferedReader
import java.io.FileReader
import java.util.concurrent.locks.Lock

//Lambda is compiled to anonymous class
//And if lambda capture some variable a new object is created on every invocation
//This introduces runtime overhead causing the implementation using lambda to be less efficiently -
// than function executes the same code directly.
//inline function tells the compiler to inline the inline function to place where it's called to avoid memory reallocation

//Define an inline function
inline fun <T>synchronized(lock:Lock, action:() -> T):T{
    lock.lock()
    try {
        return action()
    }
    finally {
        lock.unlock()
    }
}

//Note that it's possible to call an inline function and pass the parameter of function type from a variable
class LockOwner(val lock: Lock){
    fun runUnderLock(body:()->Unit){
        synchronized(lock,body)
    }
}

//If you have function that expect two or more lambda as an arguments you may choose to inline some of them
inline fun foo(inlide:()->Unit, noinline non_inline:()->Unit){
    //...
}

//Inline collection operation
//Filter a collection using lambda
data class Peson(val name:String, val age:Int)
val peope = listOf<Peson>(Peson("Naima", age = 22), Peson("hadee",1))
//Filter function is standard Libray declare as inline

val result = mutableListOf<Peson>()
//Using the use function for resource managements
//use Function is an extension function called on closable resource
//The function calls a lambda and ensure that resource is closed
//Regardless of whether lambda complete or throws an exception
fun readFirstLineFromFile(path:String):String{
    BufferedReader(FileReader(path)).use { br ->
        return br.readLine()
    }
}

//Returning from lambdas:return with label
//A local return from lambda is similar to break expression in a for loop
//It stop the execution of lambda and continue the execution from which lambda was invoked
//to distinguish the local return from non-local one you use label and refer this label from return
fun lookForNaima(people:List<Peson>){
    people.forEach label@ {
        if (it.name == "Naima") return@label
    }
    println("Naima is somewhere else")
}

//Using a function name as a return label
fun lookForHadee(people: List<Peson>){
    people.forEach {
        if (it.name =="Hadee") return@forEach
    }
    println("Hadee is somewhere else")
}

//Anonymous function:local return by default
//Using return in an anonymous function
fun lookForBobMarley(people: List<Peson>) {
    people.forEach(fun(people) {
        if (people.name == "BobMarley") return
        println("${people.name} is not BobMarley")
    })
    //Using anonymous function with expression body
    people.filter(fun(people) = people.age <= 22)
}
//Using anonymous function with filter
fun lookForYounger(people: List<Peson>){
    people.filter(fun(people):Boolean{
       return people.age < 12
    })
}


fun main(){
    peope.filter { it.age<= 12 }
    //Filter a collection manually
    for (person in peope){
        if (person.age <=12) result.add(person)
    }
    println(result)
    println(lookForBobMarley(people = peope))
}
