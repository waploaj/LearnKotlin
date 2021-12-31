package overloadingAndConvertion

import java.time.LocalDate

//Implementing the get convention
//The parameter of get can be of any type
operator fun Point.get(index:Int):Int{
    return when(index){
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException("invalid coordinate $index ")
    }
}

//Implementing the set convention
data class MutablePoint(var x:Int, var y:Int){
    operator fun set(index: Int, value:Int){
        when(index){
            0 -> x = value
            1 -> y = value
            else -> throw IndexOutOfBoundsException("invalid coordinate $index")
        }
    }
}

//implementing the in convention
data class Rectangular(val upperLeft:Point, val lowerRight:Point)
operator fun Rectangular.contains(p:Point):Boolean{
    return p.x in upperLeft.x until lowerRight.x && p.y in upperLeft.y until lowerRight.y
}

//Implementing the rangeTo convention
//If class implement comparable element you don't need to define operator for your own class type
//the stdlib define the rangeTo for comparable element
//operator fun <T: Comparable<T>> T.rangeTo(that:T):ClosedRange<T>
data class Date(val date:Int, val month:Int, val year:Int):Comparable<Date>{
    override fun compareTo(other: Date): Int {
        return compareValuesBy(this, other,Date::date,Date::month, Date::year)
    }

}
//Implementing a date range iterator
@JvmName("iteratorLocalDate")
operator fun ClosedRange<LocalDate>.iterator():Iterator<LocalDate> =
    object : Iterator<LocalDate>{
        var current = start

        override fun hasNext(): Boolean {
           return current <= endInclusive
        }

        override fun next(): LocalDate {
          return  current.apply {
                current.plusDays(1)
            }
        }
    }
fun main(){
    val p = Point(1,1)
    println(p[0])
    val ps = MutablePoint(45,343)
    ps[0] = 0
    println(ps)
    val rt = Rectangular(p, Point(70,70))
    println(Point(10,10) in rt)
    val startYear = Date(1,1, 2021)
    val endYear = Date(31,12,2021)
    val dateRange =(startYear..  endYear)
    println(dateRange)
    val vacation = Date(23,4,2021)
    println(vacation in dateRange)
    val now = LocalDate.now()
    val newYear = LocalDate.ofYearDay(2021,1)
    val dayoff = newYear.minusDays(1)..newYear
    for (dayof in dayoff){}

}

