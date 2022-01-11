package higherOrderFunction

//Store lambda in a local variable
//In this declaration compiler infers that both sum and action has function type
val sum = {x:Int, y:Int -> x + y}
val action = { println(6174)}


//define explicitly variable type
// A Unit type can be omitted when declare regular function
//But a function type declaration always require explicitly return type
val sums:(Int, Int) -> Int = {x,y -> x + y}
val actions:() -> Unit = { println(42)}

//Just like any other function, the returning type of function type can be nullable
val canReturnNull:(Int, Int)->Int? = {x,y -> null}

//Define nullable of function type
var funOrNull:((Int, Int) -> Int)? = null

//Define a simple higher-order function
fun twoAndThree(operation:(Int, Int) -> Int){
    val result = operation(2,3)
    println("The result is $result")
}

//Implementing a simple version of filter function
fun String.filter(predict:(Char)->Boolean):String{
    val sb = StringBuilder()
    for (index in 0 until length){
        val element = get(index)
        if (predict(element)) sb.append(element)
    }
    return sb.toString()
}

//Default and null value for parameter with function type
//A function type is an implemenatation of interface with an invoke method
fun <T>Collection<T>.joinToString(
    separator:String = ",",
    prefix:String = "",
    postfix:String = "",
    transform:(T) -> String = {it.toString()}
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()){
        if (index > 0) result.append(separator)
        result.append(transform(element))

    }
    result.append(postfix)
    return result.toString()
}

//Using a nullable parameter of a function type
fun <T>Collection<T>.joinToStrings(
    separator: String = ",",
    prefix: String = "",
    postfix: String = "",
    transform: ((T) -> String)? = null
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()){
        if (index > 0 ) result.append(separator)
        val str = transform?.invoke(element)?:element.toString()
        result.append(str)
    }
    result.append(postfix)
    return result.toString()
}

//Define a function that return another function
enum class Delivery{STANDARD, EXPEDITED}
class Order(val itemcount:Int)

fun getShippingCostCalculator(delivery: Delivery):(Order) ->Double{
    if (delivery == Delivery.EXPEDITED){
        return {order -> 6 +2.1 * order.itemcount  }
    }
    return {order -> 1.2 * order.itemcount  }
}

//Using a function that return function in UI code
data class Person (
    val firstname:String,
    val lastname:String,
    val phoneNumber:String?
    )
class ContactListFilter(
    var prefix: String = " ",
    var onlyWithPhoneNumber:Boolean = false
){
    fun predict():(Person)->Boolean{
        val startwithprefix = {p:Person ->
            p.firstname.startsWith(prefix) || p.lastname.startsWith(prefix)
        }
        if (!onlyWithPhoneNumber){
            return startwithprefix
        }
        return {startwithprefix(it) && it.phoneNumber !=null}
    }
}

//Removing duplication through lambdas

//Define the site visit data
data class SiteVisit(
    val path:String,
    val duration:Double,
    val os:OS
)
enum class OS{WINDOWS,MAC, IOS, ANDROID}
val log = listOf<SiteVisit>(
    SiteVisit("/", 34.0, OS.WINDOWS),
    SiteVisit("/", 22.0, OS.MAC),
    SiteVisit("/login", 12.0, OS.WINDOWS),
    SiteVisit("/signup", 8.0, OS.IOS),
    SiteVisit("/", 16.3, OS.ANDROID)
)
//Analyse site visit data with hard-coded filter
val avergeWindowDuration = log
    .filter { it.os == OS.WINDOWS }
    .map (SiteVisit::duration)
    .average()
//Now suppose you need statistics for mac user, To remove duplication we extract platform as parameter
//Removing duplication with a regular function
fun List<SiteVisit>.averageDuration(os: OS)=
    filter { it.os == os }.map(SiteVisit::duration).average()
//This is not powerful imagine we wants statistics for the mobile platform and we have ios and Android
//Analyze site visit data with complex hard-coded filter
val averageMobileDuration= log
    .filter { it.os in setOf(OS.ANDROID,OS.IOS) }
    .map(SiteVisit::duration)
    .average()
//Removing duplication with high-order function
fun List<SiteVisit>.averageDurationFor(predicate:(SiteVisit) -> Boolean) =
    filter(predicate).map(SiteVisit::duration).average()

fun main(){
    twoAndThree(sums)
    sum(2,4)
    twoAndThree{x:Int,y:Int -> x/y}
    println("aba7bc".filter { it in 'a'..'z' })
    val letter = listOf("alpha", "omega")
    println(letter.joinToString(){it.lowercase()})

    val contact = listOf<Person>(Person("Naima","Mohammedi", "0712000000"),
        Person("Aisha", "Daudi", null)
    )
    val contactListFilter = ContactListFilter()
    with(contactListFilter){
        prefix = "Naim"
        onlyWithPhoneNumber = true
    }
    println(contact.filter(contactListFilter.predict()))
    println(log.averageDurationFor { it.os in setOf<OS>(OS.IOS, OS.ANDROID) })
    println(log.averageDurationFor { it.os == OS.IOS && it.path == "/signup" })


}
