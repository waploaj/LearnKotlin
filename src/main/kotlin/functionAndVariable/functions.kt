@file:JvmName("function")
package functionAndVariable

import java.util.*

fun max(a: Int, b: Int):Boolean = if (a > b)  true else false

interface Expr
class Num(val num:Int):Expr
class Sum(val left:Expr , val right:Expr):Expr

fun eval( e:Expr):Int{
    if (e is Num){
        val n = e as Num
        return e.num
    }
    if (e is Sum){
        return eval(e.left) + eval(e.right)
    }
    throw IllegalArgumentException("no such a case")
}

fun fizzbuzz(i:Int){
    when{
        i%15 ==0 -> "fizzbuzz"
        i%3 == 0 -> "buzz"
        i%5 == 0 -> "fizz"

        else -> i
    }

}

fun recognize(c:Char){
    when(c){
        in 'A'..'Z' -> "it a letter"
        in '0' ..'9' -> "its a digit"
        else-> "i don't know"
    }
}

fun <T>Collection<T>.joinToString(separator: String = ";",
                                prefix: String = "",
                                postfix: String = ""):String{
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()){
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()

}

fun String.lasther():Char = this.get(length - 1)

fun Collection<Int>.join(
        separator:String =",",
        prefix: String = "",
        postfix: String = ""
                    ) = joinToString(separator = "..-")



