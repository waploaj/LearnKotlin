package functionAndVariable

import java.io.File

class Outer{
    inner class Inner{
        fun getReference() = this@Outer
    }
}

// Smart cast using sealed class
//seald class can not contain subclasses
sealed class Expsr{
    class Num(val num:Int): Expsr()
    class Sum(val left:Expsr, val right:Expsr):Expsr()
}

fun evalu(e:Expsr):Int =
    when(e){
        is Expsr.Num -> e.num
        is Expsr.Sum -> evalu(e.left) + evalu(e.right)
    }

class Secretive private constructor()

class PrivateUser(override var email: String):Users
class SubscribeUser(override val email:String):Users{
    override var nickname: String
        get() = email.substringBefore('@')
        set(value) {}
}

//Access backing field from getter or setter

class UserInfo(val name:String){
    var address:String = "Unspecified"
    set(value:String) {
        println("Address was changed from ${address}:" +
                "$field -> ${value.trimIndent()}")
    }
}

//Declare properties with private setter

class LengthCounter{
    var counter:Int = 0
    private set

    fun addWord(word:String) {counter += word.length}
}

//Data class and immutability when copy
data class Clients(val name: String, val postAddress:String){

    fun copys(name: String, postAddress: String) = Clients(this.name, this.postAddress)
}

val client = Clients("BOB", "123")
val newdata = client.copy(postAddress = "8787")

//class delegation by

class CountingSet<T>(val innerset:MutableCollection<T> = HashSet<T>()):MutableCollection<T> by innerset {

    var objectcount = 0
    override fun add(element: T): Boolean {
        objectcount++
        return innerset.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectcount += elements.size
        return innerset.addAll(elements)
    }

}

class Delegations(y:String):delegation {
    override val value: String
        get() = TODO("Not yet implemented")

    override fun message() {
        println("the quick brown fox jump over the lazy dog")
    }

}

class DelegationNew(y:delegation):delegation by y{
    override val value: String
        get() = "the quick brown"
}

//Object declaration singleton

object Singleton{

    var message:String = "this is the message"

    init {
        println("this is initilization ${this}")
    }

    fun showMessage(){
        println("this is message ${message}")
    }

    fun setMessages(messages:String){
        this.message = messages
    }
}

//Implemating comparator with nested object

data class Person(val name: String){
    object NameComparator:Comparator<Person>{
        override fun compare(o1: Person?, o2: Person?): Int {
            val compareTo = o1!!.name.compareTo(o2!!.name)
            return compareTo
        }
    }
}

object CasesensitiveFileComparator:Comparator<File>{
    override fun compare(o1: File?, o2: File?): Int {
        return o1!!.path.compareTo(o2!!.path, ignoreCase = true)
    }
}

//inner class and nested class
class OuterClass{
    var s1 = "this is member of outer class"
    inner class innerclass{
        fun nestedfunc():String{
            var s2 = this@OuterClass.s1
            println(s2)
            return s2
        }
    }
}