package functionAndVariable

interface Clickable{
    fun click()
    fun showOff() = println("am clickable!")
}

interface Focusable{
    fun showOff() = println("am focusable")
}

class Button:Clickable,Focusable{
    override fun click() {
        TODO("Not yet implemented")
    }

    override fun showOff() {
        println("am button").also { it.also { super<Clickable>.showOff()  } }
        super<Focusable>.showOff()
    }

    fun onClick(function: () -> Unit) {

    }

}

open class RichButton:Clickable{
    override fun click() {
        TODO("Not yet implemented")
    }

     open fun clickMe(){
        println("am super class")}

}

class SubrichButton: RichButton() {
    override fun clickMe() {
        println("am subclass")
    }

}

internal open class TalkativeButton:Focusable{
    private fun yell() = println("hey")
    protected fun whispher() = println("let talk")
}

internal class Tests: TalkativeButton(){

    fun TalkativeButton.givespeech(){
        whispher()
    }
}

interface Users{
    val email:String
    val nickname:String
        get() = email.substringBefore("@")

}

interface delegation{
    val value:String
    fun message()
}