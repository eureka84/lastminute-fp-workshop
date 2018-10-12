package marsroverkata

import marsroverkata.Usecases._
import minitest.SimpleTestSuite

object RoverRotationsTests extends SimpleTestSuite {

  test("can rotate right") {
    val position = Position(0, 0)
    val rover = Rover(position, N, Planet(2, 2))

    val nextRover: Rover = handleCommands(
      rover,
      List(TurnRight)
    )

    assert(nextRover == Rover(position, E, Planet(2, 2)))
  }

  test("can rotate right twice") {
    val position = Position(0, 0)
    val rover = Rover(position, N, Planet(2, 2))

    val nextRover: Rover = handleCommands(
      rover,
      List(TurnRight, TurnRight)
    )

    assert(nextRover == Rover(position, S, Planet(2, 2)))
  }

  test("can rotate left") {
    val position = Position(0, 0)
    val rover = Rover(position, W, Planet(2, 2))

    val nextRover: Rover = handleCommands(
      rover,
      List(TurnLeft)
    )

    assert(nextRover == Rover(position, S, Planet(2, 2)))
  }

  test("can rotate left twice") {
    val position = Position(0, 0)
    val rover = Rover(position, W, Planet(2, 2))

    val nextRover: Rover = handleCommands(
      rover,
      List(TurnLeft, TurnLeft)
    )

    assert(nextRover == Rover(position, E, Planet(2, 2)))
  }

}
