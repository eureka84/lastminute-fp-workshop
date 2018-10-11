package exercises

import minitest._

/*
 * In OOP model object that incapsulate data and expose behaviours.
 * This two concepts are brigs together thanks to class definitions.
 *
 * In FP data and behaviours are modelled with two different tools:
 * - Algebraic Data Type (ADT) to model data
 * - Function to model behaviours
 *
 * An ADT is an immutable data structure that compose other types.
 * There are two common kinds of composition strategy:
 * - Product type: put many types togheter. e.g. struct in C, POJO in JAVA.
 *                 It's called product because all the possible values of a Tuple[String, Int] is
 *                 the *product* of all possible String with all possible Int.
 *                 Useful to model indipendent data in AND e.g. a Person is composed by a name *and* an age.
 *
 * - Sum type:     model exclusive types e.g. union in C, enum in JAVA.
 *                 Sum types correspond to disjoint unions of sets.
 *                 It's called sum because all the possible values of a Either[String, Int] is
 *                 the *sum* of all possible String with all possible Int.
 *                 Useful to model dipendant data in OR e.g. the Light is on *or* off.
 *
 * We can mix ADT as we want, like a product type that compose a type with a sum type.
 */

object ModelData extends SimpleTestSuite {

  // Typical product type in Scala
  case class Person(name: String, age: Int)

  // Typical sum type in Scala
  sealed trait LightState
  case class On()  extends LightState
  case class Off() extends LightState

  /*
   * TODO: Model Scopa the italian card game, below the game description. :-)
   *       After modeling the domain implements the test following the description of ignores.
   *
   * DESCIPTION:
   *       It is played (let simplify) between two players with
   *       a standard Italian 40-card deck, divided into four suits: Cups, Golds, Swords, Clubs.
   *       The values on the cards range numerically from one through seven,
   *       plus three face cards in each suit: Knight (worth 8), Queent (worth 9) and King (worth 10).
   *       Each player receives three cards. The dealer will also place four cards face up on the table.
   *
   * ADD YOUR CODE HERE INSIDE THE OBJECT
   */

  sealed trait Seed
  case object Clubs extends Seed
  case object Cups extends Seed
  case object Diamonds extends Seed
  case object Swords extends Seed

  case class Card(value: Int, seed: Seed)
  case class Player(name: String, cards: List[Card])

  test("represent initial match state") {
    ignore("build the first player w/ 2 of Golds, 5 of Swords and 7 of Clubs")
    ignore("build the second player w/ 1 of Cups, 2 of Clubs and 9 of Golds")
    ignore("build the table w/ 4 of Clubs, 10 of Swords, 8 of Golds and 1 of Swords")
    ignore("build the deck w/ only 1 of Swords and 10 of Clubs")
    ignore("build the game")
    () // don't delete
  }
}
