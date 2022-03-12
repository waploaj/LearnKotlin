package annotationsAndreflection

import java.lang.StringBuilder
import kotlin.reflect.full.memberProperties

//Serilize an object

fun serializeString(string: Any):String = string.toString()
fun < T>serializeValue(value:Any):Char = TODO()
private fun StringBuilder.serializeObject(obj:Any){
    val kclass = obj::class
    val property = kclass.memberProperties

    property.joinToString(this, prefix = "{", postfix = "}") { prop ->
        serializeString(prop.name)
        append(":")


    }
}

