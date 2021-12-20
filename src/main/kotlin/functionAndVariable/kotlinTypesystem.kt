package functionAndVariable

import org.junit.Assert
import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException

//Elvis operator
fun strLength(str:String?):Int = str?.length?:0

//If statement
fun stringLenght(string: String?):Int =
    if (string != null) string.length else 0

//Safe call operator
fun printAllCaps(s:String?) {
    val allCaps:String? = s?.uppercase()
    println(allCaps)
}

//Use safe operator to deal with nullable properties

class Employee(val name:String, val manager:Employee?)

fun managerName(employee: Employee):String? = employee.manager?.name

val ceo = Employee("Da Boss", null)
val developer = Employee("Naima", ceo)

//Chaining througn multiple safe operator
class Address(val streetAddress:String, val country:String, val zipCodde:Int,
              val city:String)

class Company(val name: String, val address: Address?)
class Mtu(val name: String, val company: Company?)

fun Mtu.getCountryName():String = this.company?.address?.country?:"Unknown"

val mtu = Mtu("Naima", null)
val mtuMwengine = Mtu("Juma", Company("Tigo", Address("Manzese", "Tz", 53006,"Dar")))

//Using throw together with elvis operator

fun Mtu.getShippingLabel():String {
    val address = company?.address?:throw IllegalArgumentException("NO address")
    return with(address){
        streetAddress
        city
        country
     }

}

//Using safe cast to implement equals
class Me(val firstname:String, val lastname:String){
    override fun equals(other: Any?): Boolean {
        val otherObject = other as? Me ?: return false
        return otherObject.firstname == firstname && otherObject.lastname == lastname

    }
}

//Using a not-Null assertion
//If the value of parameter is null
//Kotlin would throw NullPOINTER EXCEPTION at run time
fun ignoreNotNull(s:String){
    val sNotNULL:String = s!!
    println(sNotNULL.length)

}

//Using a standard libray function let to call a function with non-null parameter
//if parameter is null nothing would happen
//the value of mail property is null the lambda code wouldnot be excuted

fun sendEmail(mail:String)= println("sent to $mail")
var mail:String? = "Yole@example.com"

fun main(){
    mail = null
    run { mail?.let { sendEmail(it) } }
    val read = readNumber(BufferedReader(StringReader("1\nabc\n42")))
    println(nullableValues(read))
    println(nullablevalue(read))

    val source:Collection<Int> = arrayListOf(1,24,3,4)
    val target:MutableCollection<Int> = arrayListOf(1,2)
    val list = listOf("a","b","c")

    println(copyElement(source, target))
    println(printInUppercase(list))
    println(letter.joinToString(""))

}

//Using assertion non-null to access nullablel property
class Myservice{
    fun performTask():String = "foo"
}

class Test{
    private var myservice:Myservice? = null

     fun setUp(){
        myservice = Myservice()
    }

     fun test(){
        Assert.assertEquals("foo", myservice!!.performTask())
    }
}


//Using late-initialize property
//late-init use var
class OurService{
    fun performSomeTask() = "buzz"
}

class OurTest{
    private lateinit var ourService: OurService

    fun setup(){
        ourService = OurService()
    }

    fun test(){
        Assert.assertEquals("buzz", ourService.performSomeTask())
    }
}

//Calling an extension function with nullable receiver
fun verifyUserInput(input:String){
    if (input.isNullOrBlank()){
        println("Input field required")
    }
}

//calling an extension function on nullable string
fun String?.isnullorblank():Boolean = this == null || this.isBlank()

//Accessing java class with no nullabity annotation
fun yellAt(person: NonNullDeclaraation){
    println(person.name?.uppercase()?:"")
}

// implemating java interface with different parameter nullability
//class KotlinImple:StringProcessor{
//    override fun process(value: String?) {
//        println(value?:"unkwon")
//    }
//}

//Collection with nullable value
//collection nullable as type arguments
fun readNumber(reader:BufferedReader):List<Int?>{
    val result = ArrayList<Int?>()
    for (line in reader.lineSequence()){
        try {
            var number = line.toInt()
            result.add(number)
        }catch (e:NumberFormatException){
            result.add(null)
        }
    }
    return result
}

//Working with collection of nullable value
fun nullableValues(reader:List<Int?>){
    var sumOfValidNumber = 0
    var sumOfInvalidNumber = 0
    for (line in reader){
        if (line != null){
            sumOfValidNumber++
        }else{
            sumOfInvalidNumber++
        }
    }
    println(sumOfInvalidNumber)
    println()
    println(sumOfValidNumber)
}

//Using kt standard libray filternotnull with nullable value in collection
fun nullablevalue(number:List<Int?>){
    val validnumber = number.filterNotNull()
    println(validnumber)
    println()
    println("${number.size -validnumber.size}")
}

//Using read-only and mutable collection interface
//this pattern is called defensive copy

fun <T> copyElement(source:Collection<T>, target:MutableCollection<T>){
    for (item in source){
        target.add(item)
    }
}


//Declare a read-only collection
//Then call a java method that modified the collection
fun printInUppercase(list: List<String>){
    println(CollectionStringUtil.uppercaseall(list))
    println(list.first())
}

val letter = Array<String>(26){i -> ('a' + 1).toString()}