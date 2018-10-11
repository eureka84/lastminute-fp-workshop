package exercises

import minitest._

/*
 * ADT models data while Function models behaviour.
 * A function is simply something that accepts an input value
 * and produces an output value.
 * In more academic terms it connects a Domain to a Co-Domain.
 * Functions are described/documented by its type definition.
 *
 *  f:  InType => OutType
 */

object FunctionsTests extends SimpleTestSuite {

  /*
   * TODO: implements functions maked with `???`
   */

  val asString: Double => String = in => in.toString

  val parseString: String => Int = in => in.toInt

  val reciprocal: Int => Double = in => 1.0 / in

  val reciprocalString: String => String =
    parseString andThen reciprocal andThen asString


  test("from string to string throught reciprocal") {
    assertEquals(reciprocalString("42"), "0.023809523809523808")
  }
}
