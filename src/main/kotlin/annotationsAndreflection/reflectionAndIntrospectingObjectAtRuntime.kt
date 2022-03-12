package annotationsAndreflection

import functionAndVariable.counter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.KFunction2
import kotlin.reflect.full.createInstance


//Reflection is put a way to access method and properties dynamically at runtime.
//To reduce runtime library size on platform where it matter such as Android kotlin reflection API is packed to differe-
//.jar file, kotlin-reflect.jar, which isn't added to dependecy of new project by default

//KClass is counterpart of java.lang.class you can use it and enumerates and access all decaration contained in classed
class Persons(val name:String, val age:Int){
    // TODO: 12/02/2022 implement member
    fun play(){
        println("play the song")
    }
    fun pause(){
        println("pause the song")
    }
}
//Note:
//The list of all member of class is Kcallable instance
//Kcallable is superinterface for function and properties.
//It declare the call method which allow to call the corresponding function or getter of the property

fun foo(x:Int) = println(x)
fun sum(x:Int, y:Int) = x + y

//Kproperty
var counter = 0
val kProperty = ::counter




fun main(){
    val kclass = Persons("aisha", 22).javaClass.kotlin //Return an instance of Kclass<Persons>
    println(kclass.memberProperties.forEach{ println(it.name)})
    println(kclass.constructors.forEach { println(it.name) })
    println(Persons::class.isData)
    println(Persons::class.typeParameters)
    println(Persons::class.isFun)

    val kfunction = ::foo
//Note:if you call kfunction.call with incorrect argument it will throw a runtime exception
// the more specific method to call the expression KFunction<Int, Unit> which contains the argument and return type.
    println(kfunction.call(43))
    println(kfunction.name)
    println(kfunction.typeParameters)

    val kfunction2:KFunction2<Int, Int,Int> = ::sum
//Note for type safe we use invoke if we know the argument and  return type to avoid runtime exception
//call method accept any argument which lead to runtime exception when the argument passed != argument declared
    println(kfunction2.invoke(1,2) + kfunction2.invoke(4,3))
    println(kfunction2.call(1,4))

//You can invoke call method on Kproperty instance as well.
//    kProperty.setter.call(3)
    println(counter)
    //output = counter =3

//a member property is presented by an instance of Kproperty1 which has one argument get method.
//To access its value you must provide an object instance for which you need its value
// Also to note you can use reflectiono to access variable of top level or in class but not local variable of function.
//Kproperty is generic.  memberProperty has type Kproperty<Persons, Int> where the first parameter is type of receiver-
//and second is the type of property.
    val person = Persons("kasimu", 47)
    val memberPropery = Persons::age
    println(memberPropery.get(person))
    println(memberPropery.name)
    println(memberPropery.javaClass.kotlin)
    val myclass = person::class
    val obj = myclass.createInstance()
    println(obj.play())
    println(obj.pause())


}