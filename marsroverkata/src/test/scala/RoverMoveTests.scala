package marsroverkata

import marsroverkata.Usecases._
import minitest.SimpleTestSuite

object RoverMoveTests extends SimpleTestSuite {

  test("can move SOUTH") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(0, 0), SOUTH, planet)

    val nextRover = handleCommands(
      rover,
      List(Move, Move)
    )

    assertEquals(nextRover, Rover(Position(2, 0), SOUTH, planet))
  }

  test("can wrap up moving SOUTH") {
    val planet = Planet(3, 3)
    val rover  = Rover(Position(0, 0), SOUTH, planet)

    val nextRover = handleCommands(
      rover,
      List(Move, Move, Move)
    )

    assertEquals(nextRover, Rover(Position(0, 0), SOUTH, planet))
  }

  test("can move NORTH") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(9, 0), NORTH, planet)

    val nextRover = handleCommands(
      rover,
      List(Move, Move)
    )

    assertEquals(nextRover, Rover(Position(7, 0), NORTH, planet))
  }

  test("can wrap up moving NORTH") {
    val planet = Planet(3, 3)
    val rover  = Rover(Position(0, 0), NORTH, planet)

    val nextRover = handleCommands(
      rover,
      List(Move, Move)
    )

    assertEquals(nextRover, Rover(Position(1, 0), NORTH, planet))
  }

  test("can move EAST") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(0, 0), EAST, planet)

    val nextRover = handleCommands(
      rover,
      List(Move, Move)
    )

    assertEquals(nextRover, Rover(Position(0, 2), EAST, planet))
  }

  test("can wrap up moving EAST") {
    val planet = Planet(3, 3)
    val rover  = Rover(Position(0, 0), EAST, planet)

    val nextRover = handleCommands(
      rover,
      List(Move, Move, Move)
    )

    assertEquals(nextRover, Rover(Position(0, 0), EAST, planet))
  }

  test("can move WEST") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(0, 9), WEST, planet)

    val nextRover = handleCommands(
      rover,
      List(Move, Move)
    )

    assertEquals(nextRover, Rover(Position(0, 7), WEST, planet))
  }

  test("can wrap up moving WEST") {
    val planet = Planet(3, 3)
    val rover  = Rover(Position(0, 0), WEST, planet)

    val nextRover = handleCommands(
      rover,
      List(Move, Move, Move)
    )

    assertEquals(nextRover, Rover(Position(0, 0), WEST, planet))
  }

}
