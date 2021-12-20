package functionAndVariable

class Rectagular(val height:Int, val width:Int){
    val isRectagular:Boolean
        get() {
           return height == width
        }
}