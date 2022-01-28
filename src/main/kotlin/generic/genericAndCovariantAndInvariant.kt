package generic

//MutableList is call invariant on the type parameter if, for any two different type A and B, -
//MutableList<A> isn't subtype or supertype of MutableList<B>.
//If A is subtype of B then List<A> is a subtype of List<B> such class or interface is called covariant.

//Declare a class to be covariant on a certain type of parameter
interface Producer<out T>{
    fun produce():T
}

//Defining an invariant collection-like class
open class Animal{
    fun feed(){}
}
class Herd<T:Animal>{
    val size:Int get() = TODO("implement get animal size")
    operator fun get(i:Int):T{TODO("Operator overload for getting animal")}
}
fun feedAll(animal: Herd<Animal>){
    for (i in 0 until animal.size){
        animal[i].feed()
    }
}
//Using an invariant collection-like class
class Cats:Animal(){  //cats is an animal
    fun cleanLitter(){}
}

fun takeCareOfCats(cats:Herd<Cats>){
    for (i in 0 until cats.size){
        cats[i].cleanLitter()
    }
    //feedAll(cats)  ---- Error type mismatch type is Herd<Cat> but Herd<Animal> is expected
}

//Using Covariant for collection-like class.
/*To make any class covariant is unsafe-
-Making covariant on certain type parameter constrains-
-It can be used on so called out-position
 -Means a class can produce the value of type T but not consume them.

Note: out keyword on type parameter of class require all method using T having T in only out position and not in positon

Note: parameter of private method are in neither the in nor out position.The variance rules protect a class-
from misuse by external clients and don't come to implementations of the class itself.
ex: class Herd<out T:Animal>(private val leadAnimal:T, vararg animal:T){}
 */
class HerdCovariant<out T:Animal>{
    val size:Int get() = TODO("Expression expected")
    operator fun get(i:Int):T {TODO("Return animal or subtype of animal ie covariant")}
}

fun feedAllCovariant(animal:HerdCovariant<Animal>){
    for (i in 0 until animal.size){
        animal[i].feed()
    }
}

fun takeCareofCat(cats: HerdCovariant<Cats>){
    for (i in 0 until cats.size){
        cats[i].cleanLitter()
    }
    feedAllCovariant(cats)
}