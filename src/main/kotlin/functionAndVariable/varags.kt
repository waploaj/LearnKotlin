package functionAndVariable

fun getAverage(vararg input:Int):Float{
    var sum = 0.0f
    for (item in input){
        sum += item
    }
    return sum/input.size

}

fun <T>asList(vararg input:T):List<T>{
    val result = ArrayList<T>()
    for (item in input){
        result.add(item)
    }
    return result

}

open class Student(var name:String){
    init {

    }
    constructor(age:Int, id:Int,name: String):this(name){}
}

class Teacher:Student{
    constructor(id: Int, name: String):this(name){}
    constructor(nam:String):super(name = "")
}