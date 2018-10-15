package marsroverkata

import cats.effect.IO
import marsroverkata.Data._
import marsroverkata.GameInteractions._
import marsroverkata.GamePlay._

class Game {

  def run(): Unit =
    runIO().unsafeRunSync()

  def runIO(): IO[Unit] =
    welcome()
      .flatMap(_ => readPlanet())
      .flatMap(planet => readObstacles().map(os => planet.copy(obstacles = os)))
      .flatMap(planet => readPosition().map(pos => Rover(pos, N, planet)))
      .flatMap(rover => readCommands().map(cs => handleCommands(rover, cs)))
      .flatMap(result => display(result))

}