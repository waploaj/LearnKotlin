package overloadingAndConvertion

//Using destructuring declearation to return multiple value
//One of the main use case of destructuring declearation is to return multiple value from function
//For data class compiler generate component function for every property declared in primary constructer
//For non data class implemantation manually
//class Point(val x:Int, val y:Int){
//    operator fun component1() = x
//    operator fun component2() = y
//}
data class NameSpace(val name:String, val ext:String)
fun splitFileName(fullname:String):NameSpace{
    val result = fullname.split(".", limit = 2)
    return NameSpace(result[0], result[1])
}

//Using destructuring declaratiion for iterate over map
fun printEnties(map:Map<String, String>){
    for ((key, value ) in map){ //destructuring declaration in a loop
        println("$key -> $value")
    }
}

fun main(){
    val (name, ext) = splitFileName("example.kt")
    println(name)
    println(ext)
    val map = mapOf<String, String>("Oracle" to "java", "Jetbrain" to "Kotlin")
    printEnties(map)
}