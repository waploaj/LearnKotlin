package annotationsAndreflection

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.sql.Time
import kotlin.reflect.KClass

//Annotation is just comments which can be used to
/*
-Embed instruction for jvm compiler
-Embed instruction for source code processing tools
-Embed metadata which can be read at runtime.

Note:
Annotation argument must be know at compiled time.
-To specify class as annotation parameter you put MyClass::class
-To specify annotation as annotation parameter you don't use @
-To specify array as an arguments you use arrayOf ie: Request-Mapping(path= arraryOf("/foo", "/bar")
* */

// To use property as annotation arguments you need to mark with CONST modifier.
//CONST modifier must be declared at top level of file or object and initialized with primitive value or String.
const val TIME_OUT = 100L
@Test(timeout = TIME_OUT)fun testMethod(){ TODO("implement the body of testMethod function.")}

//Annotation Target
//use-site Target
annotation class Getannotation
annotation class Setannotation
annotation class Setannotation2
annotation class DeserializeInterface(val targeClass:KClass<out Any>) //We use out because targetclass extend Any class
annotation class DeserializerInterceValue(val targetclass: KClass<out ValueSerializer<*>>)

class Person(
    @get:Getannotation val firstName:String,
    @set:[Setannotation Setannotation2] var lastName:String
){

}

//class HasTemporarty(){
//
//    @get:Rule
//    val folder =  TemporaryFolder() // the getter is annotated not property
//
//    @Test
//    fun testmethod(){
//        val createdFile = folder.newFile("annotation.txt")
//        val creadetFolder = folder.newFolder("subFolder")
//    }
//}
/*
The full list of supported use-site target
-Property java annotation can't be applied with this use-site target
-Field field genarated by property
-get property getter
-set property setter
-receiver Receiver parameter of an extension function or property
-param constructer parameter
-setparam property setter parameter
-getparam property getter parameter
-delegate Field stored delegated instance for delegated property
-file class contain top level function and property declare in file
* */
class Example(){
    @get:Deprecated( message = "hellow word",replaceWith = ReplaceWith("THIS IS DEPRECATED"))
    val name:String
    get() = "This is replaced"
}



//Classes as annotation parameter
interface Label{
    val name:String
}
data class Reggae(override val name: String, val age:Int):Label
data class Artist(
    val name: String,
    @DeserializeInterface(Reggae::class) val label: Label
)

//Generic class as annotation parameter
interface ValueSerializer<T>{
    fun toJsonValue(value:T):Any
    fun fromJsonValue(jsonValue:Any):T
}
data class Song(
    val gene:String,
    @DeserializerInterceValue(ValueSerializer::class) val time:Time
)

fun main(){
    val p = Example()
    println(Example::name)

    val q = Reggae("bobMarley", 77)
}