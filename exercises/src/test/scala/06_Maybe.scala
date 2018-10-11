package exercises

import minitest._

/*
 * Functions can't always return a value.
 * In this scenario they are called: partial functions.
 * We can convert them into total functions
 * with the introduction of effects.
 *
 *  f:  InType => Effect[OutType]
 */

object MaybeTests extends SimpleTestSuite {

  /*
   * TODO: remove all nulls
   */

  sealed trait Effect[+T]
  case class Success[T](v: T) extends Effect[T]
  case object Failure extends Effect[Nothing]

  case class Qty(value: Int)

  def toQty(value: String): Effect[Qty] =
    if (value.matches("^[0-9]+$")) Success(Qty(value.toInt))
    else Failure

  test("valid qty") {
    assertEquals(toQty("100"), Success(Qty(100)))
  }

  test("invalid qty") {
    assertEquals(toQty("asd"), Failure)
    assertEquals(toQty("1 0 0"), Failure)
    assertEquals(toQty(""), Failure)
    assertEquals(toQty("-10"), Failure)
  }
}
