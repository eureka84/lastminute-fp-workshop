package exercises

import minitest._

/*
 * In a type system we deal with different kind of types.
 *
 * Type like Int, String, Date, Person, etc. are called
 * 'proper type'. In polymorphic function it is represented
 * by symbols like A, B, C, etc.
 * def sum[A](x: A, y: A): A = ???
 * These types have kind: *
 *
 * Instead types like List[A], Option[A], Box[A], etc.
 * are called 'type constructor', because they are open
 * until associated with a proper type like List[Int],
 * Option[String], Box[Person], etc. In the polymorphic
 * function definition nothing changes.
 * def concat[A](x: List[A], y: List[A]): List[A] = ???
 * These types have kind: * -> *
 * aka accepts a type and returns a type.
 *
 * In Scala and few others languages we can express
 * a function polymorphic over the type constructor.
 * def concat[F[_], A](x: F[A], y: F[A]): F[A] = ???
 * In these cases we talk about Higher-Order Kinds.
 * These types have kind: * -> * -> *
 * aka accepts a type constructor and returns
 * a type constructor.
 *
 * It's similar to the idea of High-Order Functions
 * aka function that accepts/returns other functions.
 */

object TypeclassHKTests extends SimpleTestSuite {

  /*
   * TODO: Define push and pop operations.
   *       Implements class instance.
   *       Implements MRUList.add.
   */

  trait Stack[F[_]] {
    def pop[A](items: F[A]): (Option[A], F[A])
    def push[A](value: A, items: F[A]): F[A]
  }

  implicit val listStack = new Stack[List] {
    override def push[A](value: A, items: List[A]): List[A]   = value :: items
    override def pop[A](items: List[A]): (Option[A], List[A]) =
      (items.take(1).headOption, items.tail)
  }

  object Stack {
    def apply[F[_]](implicit x: Stack[F]): Stack[F] = x
  }

  object MRUList {
    def add[F[_]: Stack, A](value: A, items: F[A]): F[A] =
      Stack[F].push(value, items)

    def take[F[_]: Stack, A](items: F[A]): (Option[A], F[A]) =
      Stack[F].pop(items)
  }

  test("add an element to the MRU list") {
    import MRUList._

//    ignore("implement missing functions")
    val items  = List(1)
    val result = add(2, items)
    assertEquals(result, List(2, 1))
  }

  test("remove an element to the MRU list") {
    import MRUList._

    //    ignore("implement missing functions")
    val items  = List(2, 1)
    val (optionalValue, newItems) = take(items)
    assertEquals(optionalValue, Some(2))
    assertEquals(newItems, List(1))
  }
}
