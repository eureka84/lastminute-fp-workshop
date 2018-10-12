package marsroverkata

import marsroverkata.Usecases._
import minitest.SimpleTestSuite

object RoverMoveBackwardsTests extends SimpleTestSuite {

  test("can move Backward facing SOUTH") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(0, 0), S, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveBackward)
    )

    assertEquals(nextRover, Rover(Position(9, 0), S, planet))
  }

  test("can move Backward facing EAST") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(0, 0), E, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveBackward)
    )

    assertEquals(nextRover, Rover(Position(0, 9), E, planet))
  }

  test("can move Backward facing NORTH") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(9, 0), N, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveBackward)
    )

    assertEquals(nextRover, Rover(Position(0, 0), N, planet))
  }

  test("can move Backward facing WEST") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(0, 9), W, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveBackward)
    )

    assertEquals(nextRover, Rover(Position(0, 0), W, planet))
  }
}
