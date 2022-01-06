package overloadingAndConvertion

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import javax.swing.text.html.parser.Entity
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

//Implementing lazy initialization using a backing property
class Emails{}
fun loadEmails(person: Persons):List<Emails>{
    println("load emails for ${person.name}")
    return listOf(/*...*/)
}
//Here we are using backing property technique
//We have two properties _email to store the value and email to provide read access to it.
class Persons(val name:String){
    var _emails:List<Emails>? = null

    val email:List<Emails>
    get() {
            if (_emails == null){
                _emails = loadEmails(this)
            }
            return _emails!!
    }
}
//implementing lazy initilization by delegated property
class Erson(val name: String){
    val email by lazy { loadEmails(Persons("naima")) }// you can loadEmails(this)
}
//Helper class for using PropertyChangeSupport
//PropertyChangeSupport manage list of listeners and dispatches PropertyChangeEvent to them
/*To use it, normally  you store an instance of this class as field
in bean class and delegated property Change processing to it*/
open class PropertyChangeAware{
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener:PropertyChangeListener){
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener:PropertyChangeListener){
        changeSupport.removePropertyChangeListener(listener)
    }

}

//Manually implementing property change notification
class Mfanyakazi(val name: String, val age:Int, val salary:Int):PropertyChangeAware(){
    var umri:Int =  age
    set(newValue) {
        val oldValue = field  //the field identifier let you access the property backing field
        field = newValue
        changeSupport.firePropertyChange("umri", oldValue, newValue)//Notifies lister about property change
    }

    var mshara:Int = salary
    set(newValue) {
        val oldValue =  field
        field = newValue
        changeSupport.firePropertyChange("mshara", oldValue, newValue)
    }
}

//Implementing property change notification with helper class
class ObservableProperty(
    val proName:String,
    var proValue:Int,
    val changeSupport: PropertyChangeSupport
){
    fun getValue() = proValue
    fun setValue(newValue: Int){
        val oldValue = proValue
        proValue = newValue
        changeSupport.firePropertyChange(proName, oldValue, newValue)
    }
}
class Employee(
    val name: String,
    age: Int,
    salary: Int
):PropertyChangeAware(){
    val _age = ObservableProperty("age", age, changeSupport)
    var age:Int
    get() = _age.getValue()
    set(value) {_age.setValue(value)}

    val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary:Int
    get() = _salary.getValue()
    set(value) {_salary.setValue(value)}
}

//ObservableProperty as a property delegate
class OBservableProperty(
    var propValue:Int, val changeSupport: PropertyChangeSupport
){
    operator fun getValue(employee: Employees, prop:KProperty<*>) = propValue
    operator fun setValue(employee: Employees, prop: KProperty<*>, newValue: Int){
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

//Delegated properties for property change notification
class Employees(
    val name: String, age: Int, salary: Int
):PropertyChangeAware(){
    var age:Int by OBservableProperty(age, changeSupport)
    var salary:Int by OBservableProperty(salary, changeSupport)
}

//Using Delegates.observable to implement property change notification
//Instead of implementing observable property logic by hand you can use kt standlib
class Mtuu(
    val name: String, age: Int, salary: Int
):PropertyChangeAware(){

    private val observer = {
        prop:KProperty<*>, oldValue:Int, newValue:Int ->
        changeSupport.firePropertyChange(prop.name,oldValue,newValue)
    }
    var age:Int by Delegates.observable(age, observer)
    var salary:Int by Delegates.observable(salary, observer)
}

//Storing Property value in map
/*expando object is that object that has dynamically defined set of attribute associated with them*/
//exapndo object is where another common pattern of delegate properties come to play
//Define property that store its value in map
class Customer{
    private val _attribute = HashMap<String, String>()

    fun setAttribute(attributeName:String, value:String){
        _attribute[attributeName] = value
    }
    val name:String
    get() = _attribute["name"]!!
}

//Using Delegate property which store its value in map
/*This work because standlib define getValue and setValue extension function on map and
mutableMap interface.The name of property is automatically used as key to store the value
in the map
* */
class Customers{
    private val _attribute = HashMap<String, String>()

    fun setAttribute(attributeName: String, value: String){
        _attribute[attributeName] = value
    }
    val name:String by _attribute
}

//Accessing database columns using delegated property
//You can use Column property(Users.name) as a delegate for delegated property (name)
/*object Users:IdTable(){ // the object corresponding to the table in db
    val name = varchar("name", length=50).index()
    val age = interger("age")
}
class User(id:EntityID):Entity(id){//Each instance of user is corresponding to specific entity in db
    var jina:String by Users.name // the value of name is stored in db for that user
    var age:Int by Users.age
}*/

fun main(){
    val p = Mfanyakazi("Naima", 22, 2000)
    println(p.addPropertyChangeListener(PropertyChangeListener { event ->
        println("property ${event.propertyName} changed + from ${event.oldValue} to ${event.newValue}")
    }))

    val q = Employees("asha",22,3000)
   val s = q.addPropertyChangeListener(PropertyChangeListener { event -> println("${event.propertyName} has " +
            "old value ${event.oldValue} has new value ${event.newValue}") })
    println(s)

    val ps = Customer()
    val data = mapOf("name" to "Naima", "Company" to "Encipher")
    for ((attributeName, value) in data){
        ps.setAttribute(attributeName, value)
    }
    println(p.name)
}

