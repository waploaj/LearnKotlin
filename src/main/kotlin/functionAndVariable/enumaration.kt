package functionAndVariable

enum class Cards(val color:String) {

    Diamond("red"),
    Heart("black");
}

enum class Color{
    Red,
    Green,
    Yellow,
    Violet,
    Blue,
    Indigo

}

enum class Days(val isWeekend:Boolean = false){
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday(true),
    Sunday(true);

    companion object {
        fun today(obj:Days):Boolean{
            return obj.name.compareTo("Saturday") == 0 || obj.name.compareTo("Sunday") == 0
        }
    }

    fun todays(net:Days){

        println(net.todays(Sunday))
    }
}