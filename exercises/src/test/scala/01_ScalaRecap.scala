package exercises

import exercises.answers.ScalaRecap.Banana
import minitest._

/*
 * Our most used Scala features:
 * - Case class
 * - Companion Object
 * - Apply function
 * - Type parameter
 * - Trait as interface/mixin
 * - Implicit parameter
 * - Extension class
 * - Pattern match
 */

object ScalaRecap extends SimpleTestSuite {

  /*
   * TODO: one test at a time,
   *       read `ignore()` description
   *       uncomment the code,
   *       and add the code to get a green test
   *
   * ADD YOUR CODE HERE INSIDE THE OBJECT
   */

  test("define case class") {
    val result = Person("foo", 56)
    assertEquals(result, Person("foo", 56))
  }

  test("define the case class's companion object") {
    val result = Person.create("foo, 56")
    assertEquals(result, Person("foo", 56))
  }

  test("case class apply") {
    val result = Person("foo", 56)("Ciao,")
    assertEquals(result, "Ciao, mi chiamo foo!")
  }

  test("case class update") {
    val p      = Person("foo", 56)
    val result = p.copy(age = p.age + 100)
    assertEquals(result.age, 156)
  }

  test("trait as interface") {
    assert(Apple().isInstanceOf[Fruit])
    assert(Banana().isInstanceOf[Fruit])

    assertEquals(Apple().stringify, "an apple")
    assertEquals(Banana().stringify, "a banana")
  }

  test("trait as mixin") {
    assertEquals(Apple().eatenBy("foo"), "foo ate an apple")
    assertEquals(Banana().eatenBy("bar"), "bar ate a banana")
  }

  test("type parameter") {
    val p      = Person("foo", 56)
    val result = p.eat(Apple())
    assertEquals(result, "foo ate an apple")
  }

  test("implicit parameter") {
//    ignore("add a function w/ an implicit parameter to the Person class")
    implicit val years = 30
    val p              = Person("foo", 56)
    val result         = p.makeYounger
    assertEquals(result.age, 26)
  }

  test("extension class") {
//    ignore("add a function to Person via implicit class extension")
    val p      = Person("foo", 56)
    val result = p.toMap
    assertEquals(result, Map("name" -> "foo", "age" -> "56"))
  }

  def isFake(person: Person): Boolean = person match {
    case Person("bar", _)          => true
    case Person("foo", _)          => true
    case Person(_, age) if age < 0 => true
    case _                         => false

  }

  test("pattern match") {
    assert(isFake(Person("foo", 10)))
    assert(isFake(Person("bar", 10)))
    assert(isFake(Person("matte", -10)))
    assert(!isFake(Person("matte", 10)))
  }
}
case class Person(name: String, age: Int) {
  def makeYounger(implicit years: Int): Person = Person(name, age - years)

  def eat[T <: Fruit](fruit: T): String = fruit.eatenBy(name)

  def apply(message: String): String = s"$message mi chiamo $name!"
}
object Person {
  def create(strValue: String): Person = {
    val parts = strValue.split(",")
    Person(parts(0), parts(1).trim.toInt)
  }
  implicit class RichPerson(p: Person) {
    def toMap: Map[String, String] =
      Map("name" -> p.name, "age" -> p.age.toString)
  }
}

sealed trait Fruit {
  def stringify: String = this match {
    case Apple()  => "an apple"
    case Banana() => "a banana"
  }
  def eatenBy(eater: String): String = s"$eater ate $stringify"
}
case class Apple()  extends Fruit
case class Banana() extends Fruit
