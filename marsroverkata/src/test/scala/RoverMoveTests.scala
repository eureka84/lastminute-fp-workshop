package marsroverkata

import marsroverkata.Usecases._
import minitest.SimpleTestSuite

object RoverMoveTests extends SimpleTestSuite {

  test("can move SOUTH"){
    val rover = Rover(Position(0,0), SOUTH)

    val nextRover = handleCommands(
      rover,
      List(Move)
    )

    assert(nextRover == Rover(Position(1, 0), SOUTH))
  }

  test("can wrap up moving SOUTH") {
    val rover = Rover(Position(0,0), SOUTH, Planet(2,2))

    val nextRover = handleCommands(
      rover,
      List(Move)
    )

    assert(nextRover == Rover(Position(1, 0), SOUTH))
  }

}
