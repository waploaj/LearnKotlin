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

fun main(){
    twoAndThree(sums)
    sum(2,4)
    twoAndThree{x:Int,y:Int -> x/y}
    println("aba7bc".filter { it in 'a'..'z' })
    val letter = listOf("alpha", "omega")
    println(letter.joinToString(){it.lowercase()})


}
