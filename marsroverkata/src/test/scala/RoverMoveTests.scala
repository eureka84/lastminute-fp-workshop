package marsroverkata

import marsroverkata.Usecases._
import minitest.SimpleTestSuite

object RoverMoveTests extends SimpleTestSuite {

  test("can move SOUTH") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(0, 0), S, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveForward, MoveForward)
    )

    assertEquals(nextRover, Rover(Position(2, 0), S, planet))
  }

  test("can wrap up moving SOUTH") {
    val planet = Planet(3, 3)
    val rover  = Rover(Position(0, 0), S, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveForward, MoveForward, MoveForward)
    )

    assertEquals(nextRover, Rover(Position(0, 0), S, planet))
  }

  test("can move NORTH") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(9, 0), N, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveForward, MoveForward)
    )

    assertEquals(nextRover, Rover(Position(7, 0), N, planet))
  }

  test("can wrap up moving NORTH") {
    val planet = Planet(3, 3)
    val rover  = Rover(Position(0, 0), N, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveForward, MoveForward)
    )

    assertEquals(nextRover, Rover(Position(1, 0), N, planet))
  }

  test("can move EAST") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(0, 0), E, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveForward, MoveForward)
    )

    assertEquals(nextRover, Rover(Position(0, 2), E, planet))
  }

  test("can wrap up moving EAST") {
    val planet = Planet(3, 3)
    val rover  = Rover(Position(0, 0), E, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveForward, MoveForward, MoveForward)
    )

    assertEquals(nextRover, Rover(Position(0, 0), E, planet))
  }

  test("can move WEST") {
    val planet = Planet(10, 10)
    val rover  = Rover(Position(0, 9), W, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveForward, MoveForward)
    )

    assertEquals(nextRover, Rover(Position(0, 7), W, planet))
  }

  test("can wrap up moving WEST") {
    val planet = Planet(3, 3)
    val rover  = Rover(Position(0, 0), W, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveForward, MoveForward, MoveForward)
    )

    assertEquals(nextRover, Rover(Position(0, 0), W, planet))
  }

}
