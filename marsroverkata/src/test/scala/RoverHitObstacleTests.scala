package marsroverkata

import marsroverkata.Usecases._
import minitest.SimpleTestSuite

object RoverHitObstacleTests extends SimpleTestSuite {

  test("hit an Obstacle") {
    val planet = Planet(5, 5, List(Position(2, 3)))
    val rover  = Rover(Position(2, 0), E, planet)

    val nextRover = handleCommands(
      rover,
      List(MoveForward, MoveForward, MoveForward)
    )

    assertEquals(nextRover, Rover(Position(2, 2), E, planet))
  }

}
