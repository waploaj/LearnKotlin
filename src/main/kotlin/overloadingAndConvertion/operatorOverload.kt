package overloadingAndConvertion

import java.awt.geom.Point2D
import java.math.BigDecimal

data class Point(val x:Int, val y:Int)
data class Point1(val x: Int, val y: Int)

//Overloading arithmetic operator through extension function
//overloading can be implemented through member function
//Kotlin operator doesn't automatically support commutativity
//you have to define another operator to overload
//kotlin doesn't define any bitwise operator
//And doesn't let you define for your types
//Instead they use regular function support infinix call
operator fun Point.plus(other:Point):Point{
    return Point(x + other.x, y + other.y)
}

//When you define dec and inc function to increment and decrement operator
//compile support the same semantics for pre- and post- increments for the regular number types
class UnaryOverload(var string: String){
    operator fun unaryMinus(){
        string = string.reversed()
    }
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE

//Defining operator with different operand
operator fun Point.times(s:Double):Point{
    return Point((x*s).toInt(),(y*s).toInt())
}

//Defining operator with different return type
operator fun Char.times(count:Int):String{
    return toString().repeat(count)
}

//Implementing equal
//=== can't be overloaded
//Its override because equality comparison is supported in all kotlin object
//Method implementing is defined in Any class
//Equal can't be implemented as extension,
//Because implementation is inherited from Any class would take the importance over extension
class Point2(val x: Int, val y: Int){
    override fun equals(other: Any?): Boolean {
        if (other === this)return true
        if (other !is Point2) return false
        return other.x == x && other.y == y
    }
}

//Implemanting CompareTo method
data class Person(val firstname:String, val lastname:String):Comparable<Person>{
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this,other,Person::firstname,Person::lastname)
    }

}


fun main(){
    val p = UnaryOverload("mambo")
    println(p.string)
    -p
    println(p.string)
    val j = Point(8,9)
    val point = Point2(2,5)
    val otherPoint = Point2(4,45)
    println(j * 3.8)
    println('a'.times(7))
    println(point == otherPoint)
    var bd = BigDecimal.ZERO
    println(++bd)
    println(bd++)
    val ps = Person("azzzz", "aaudi")
    val ps2 = Person("azzz","kasimu")
    val bel = mutableListOf<Person>(ps,ps2)
    println(ps > ps2)
    (bel.sort())
    println(bel)


}

