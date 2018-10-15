package marsroverkata

import cats.effect.IO
import marsroverkata.Data._
import marsroverkata.DataParsers.{parseCommands, parseObstacles, parsePlanet, parsePosition}
import marsroverkata.IO_Ops.{ask, puts}

object GameInteractions {

  def welcome(): IO[Unit] =
    puts("Welcome to the Mars Rover Kata!")

  def readPlanet(): IO[Planet] =
    ask("What is the size of the planet?").map(parsePlanet)

  def readObstacles(): IO[List[Obstacle]] =
    ask("Where are the obstacles?").map(parseObstacles)

  def readPosition(): IO[Position] =
    ask("What is the position of the rover?").map(parsePosition)

  def readCommands(): IO[List[Command]] =
    ask("Waiting commands...").map(parseCommands)

  def display(result: (Boolean, Rover)): IO[Unit] = {
    val (hasHitObstacle, r) = result
    val prefix = if (hasHitObstacle) "O:" else ""
    puts(s"$prefix${r.direction}:${r.position.x},${r.position.y}")
  }

}
