package generic

import java.lang.NullPointerException
import java.util.*

//Declare generic extension properties
val <T>List<T>.penultimate:T
    get() = this[size - 2]

//Type parameter constrains
/*Lets you restrict type to be used as type parameter for a class or function
when you specify a type as an upper bond constraint for a type parameter of a generic type-
the corresponding type of specific instantiations of generic type must be either the specified type-
or it sub-type
* */
fun <T:Number>List<T>.sum():T = TODO()
//Declaring a function with type parameter constrains
fun<T:Comparable<T>>max(first:T, second:T):T{
    return if (first>second) first else second
}
//Declaring multiple constrains for type parameter
fun <T>ensureTrailingPeriod(seq:T)
    where T:CharSequence, T:Appendable{
        if (!seq.endsWith('.')){
            seq.append('.')
        }
    }

//Making parameter type non null
class Process<T>{
    fun process(value:T){
        value?.hashCode()
    }
}

val nullableProcess = Process<String?>()

//Using a type cast with generic type
//Note the cast won't fail if the class has correct base class and different type arguments
//because the type arguments isn't know at runtime when cast is performed
fun printSum(c:Collection<*>){
    val intList = c as? List<Int> ?: throw IllegalArgumentException("List was expected!")
    println(intList.sumOf { it })

}

//Using Type check with know type arguments
fun printsSum(c: Collection<Int>){
    if (c is List<Int>){
        c.sumOf { it }
    }
}

//Declare a function with a reified type arguments
//inline function their type argument can be reified
inline fun <reified T> isA(value:Any) = value is T

//Simplified implementation of filterIsInstance
inline fun <reified T>Iterable<*>.filterIsInstancE():List<T>{
    val destination = mutableListOf<T>()
    for (element in this){
        if (element is T){
            destination.add(element)
        }
    }
    return destination
}

//Replacing class reference with reified type parameter
inline fun <reified T>loadService(): ServiceLoader<T>? {
    return ServiceLoader.load(T::class.java)?: throw NullPointerException("null point exception")
}

//Restriction on reified type parameter
//How you can use reified type parameters
/*
-In type cast and check(is,!is, as, as?)
-To use kotlin reflection API (::Class)
-To get corresponding java.lang.class(::class.java)
-As type arguments to call other functions
* */
//What you can't do
/*
-To create new instance of class specified as type parameter
-Call methods of companion object of the type parameter class
-Use non-reified type parameter as type  arguments when call function with reified type parameter
-Mark the type parameter of class, properties or non-inline function as reified
* */

fun main(){
    val letters = ('a'..'z').toList()
    //calling generic function
    println(letters.slice<Char>(0..3).penultimate)
    println(letters.penultimate)

    println(max("Kotlin", "Java"))
    println(max(23,4))

    val helloWorld = StringBuffer("Helloo World")
    ensureTrailingPeriod(helloWorld)
    println(helloWorld)
    println(printSum(listOf(1,35,5)))

    //Using the filterIsInstance standard library function
    val items = listOf("one", 2, "three")
    println(items.filterIsInstance<Int>())

}