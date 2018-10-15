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
      .flatMap(p => readObstacles().map(os => p.copy(obstacles = os)))
      .flatMap(p => readPosition().map(pos => Rover(pos, N, p)))
      .flatMap(r => readCommands().map(cs => handleCommands(r, cs)))
      .flatMap(r => display(r))

}
