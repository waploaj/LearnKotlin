import functionAndVariable.*


fun main(args: Array<String>) {

    println(Rectagular(9,9).isRectagular)
    Rectagular(12,14).isRectagular
    println(max(4,4))
    println(Cards.Diamond.color)

    for(card in Cards.values()){
        println("--")
        println("${card.ordinal} --"+card.name)
    }

    println(Cards.values() )
    println("----")
    println(Cards.valueOf("Diamond"))
    println("---")
    val today = Days.Sunday
    println(today.isWeekend)
    println(Days.today(today))
    println("")

println(mix(Color.Yellow,Color.Red))

println(Sum(Sum(Num(1), Num(2)),Num(3)))

    for (i in 1..100){
        println(fizzbuzz(i))
    }

    for (c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] =binary
    }

    for ((letter, binary) in binaryReps){
        println("${letter} -- ${binary}")
    }
    println(recognize('x'))

    println("kotlin" in "java".."scala")
    println("kotlin " in setOf("java", "scala"))

    val list = listOf(1,3,3)
    println(list.join())
    println("i love you tayanaa")

}

fun mix(c1:Color, c2:Color){
    when(setOf(c1,c2)){
        setOf(Color.Yellow,Color.Red) -> "Orage"
    }
}

fun mixed(c1:Color, c2:Color){
    when{
        c2 == Color.Red && c1 == Color.Yellow || c1 == Color.Green && c2 == Color.Yellow -> "Greeen"
    }
}

val binaryReps = HashMap<Char, String>()
