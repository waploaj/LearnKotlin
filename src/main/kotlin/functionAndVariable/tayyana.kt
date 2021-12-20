package functionAndVariable

fun main(args: Array<String>){
    val list = listOf("kotl", *args)
    println("mimi ni naima ")
    println(sum(4,5))
    println(getAverage(1,3,3,5,5))
    println(asList(1,34,5,5))
    println("12.345-6.A".split(". | -"))
    println("${Button().showOff()}")
    val userinfo = UserInfo("Alice")
    userinfo.address = "ubungo makongo juu"

    val p = Delegations("the hello")
    val dp = DelegationNew(p)
    println("${p.message()}, ${dp.value}")

    val x = OuterClass()

    println(Singleton.message +Singleton.showMessage())
    println(x.innerclass().nestedfunc())
    println(findTheOldest(pipo))
    println(pipo.maxByOrNull { it.age })
    println(pipo.maxByOrNull(Persons::age))
    println(pipo.filter { it.age>10 })

    run { println(42) };
    { kotlin.io.println(23)}()

    println(sizeofPipo())
    println(ageofPipo(Persons("aisha",29)))
    println(majina)
    println(wazee)
    println(vikongwe)
}

fun sum(vararg x:Int):Int = x.sum()

//make the function tidy

class User(val id:Int, val name:String, val address:String)

fun User.validateBeforeSave(){
    fun validate(value:String, fieldname:String){
        if (value.isEmpty()){
            throw  IllegalArgumentException("can't save user ${id} is empty ${fieldname}")
        }
        validate(name, "Name")
        validate(address, "Address")
    }
}