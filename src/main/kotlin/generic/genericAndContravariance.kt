package generic

import kotlin.reflect.KClass

//A class or interface can be covariant on one type parameter and contravariant on another
interface Function1<in P, out T>{
    operator fun invoke(p:P):T
}
//Another  Example Subtyping relationship
fun enumarate(t:(Cats)-> Number){}
fun Animal.getIndex():Int = 45
val result = enumarate (Animal::getIndex)

//A data copy function with invariant parameter types
fun <T>copyData(source:MutableList<T>, destination:MutableList<T>){
    for (item in source){
        destination.add(item)
    }
}
//A data copy function with two type parameters
////source's elements type should be destionation's element subtype
fun <T:R,R>copyDataTwoType(source: MutableList<T>, destination: MutableList<R>){
    for (item in source){
        destination.add(item)
    }
}
//A data copy function with out-projected type parameter
fun <T>copyDataOut(source:MutableList<out T>, destination: MutableList<T>){// No method with T in "in" position
    for (item in source){
        destination.add(item)
    }
}
//A data copy function with an in-projected type parameter
////Allow destination element type to be super type of source element type
fun <T>copyDataIn(source: MutableList<T>, destination: MutableList<in T>){
    for (item in source){
        destination.add(item)
    }
}
//Note: Use-site projectin can help widen the range of acceptable type
// Star projection: Using * instead of type arguments
/*
Note: MutableList<*> != MutableList<Any?>
MutableList<Any?> is a list contains element of any type-
MutableList<*> is a list contains of specific type which you don't know type it is.
* */

interface FieldValidator<in T>{
    fun validate(input:T):Boolean
}
object DefaultStringValidator:FieldValidator<String>{
    override fun validate(input: String): Boolean {
        return input.isNotEmpty()
    }
}
object DefaultIntValidator:FieldValidator<Int>{
    override fun validate(input:Int):Boolean = input >= 0
}
//Store Validators on same container on map
val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()

//Retrieving validator using explicity cast
val stringValidator = validators[String::class] as FieldValidator<String>

//Encapsulating access to the validator collection
object Validators{
    private val validator  = mutableMapOf<KClass<*>, FieldValidator<*>>()

    fun <T:Any> registerValidator(kClass: KClass<T>, fieldValidator: FieldValidator<T>){
        validator[kClass] = fieldValidator
    }

    operator fun <T:Any> get(kClass: KClass<T>):FieldValidator<T> =
        validator[kClass] as FieldValidator<T> ?: throw IllegalArgumentException("No validatoro for ${kClass.simpleName}")
}




fun main(){
    validators[String::class] = DefaultStringValidator
    validators[Int::class] = DefaultIntValidator
    println(stringValidator.validate("notl"))

}