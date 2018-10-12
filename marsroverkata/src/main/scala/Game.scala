package marsroverkata

import cats.effect.IO

import scala.io.StdIn._

class Game {

  import Usecases._

  def run(): Unit =
    runIO().unsafeRunSync()

  def runIO(): IO[Unit] =
    welcome()
      .flatMap(_ => readPlanet())
      .flatMap(_ => readObstacles())
      .flatMap(_ => readPosition().map(p => Rover(p, NORTH)))
      .flatMap(r => readCommands().map(cs => handleCommands(r, cs)))
      .flatMap(r => display(r))

}

object Usecases {
  import IO_Ops._

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

  case class Planet(width: Int, height: Int)
  case class Obstacle()
  case class Position(x: Int, y: Int)
  case class Rover(position: Position, direction: Direction, planet: Planet = Planet(5, 5))

  sealed trait Command
  case object TurnRight extends Command
  case object TurnLeft  extends Command
  case object Move      extends Command

  sealed trait Direction
  case object NORTH extends Direction
  case object EAST  extends Direction
  case object SOUTH extends Direction
  case object WEST  extends Direction

  def parsePlanet: String => Planet            = ???
  def parseObstacles: String => List[Obstacle] = ???
  def parsePosition: String => Position        = ???
  def parseCommands: String => List[Command]   = ???
  def display(r: Rover): IO[Unit]              = ???

  def handleCommands(r: Rover, cs: List[Command]): Rover =
    cs.foldLeft(r)(handleCommand)

  def handleCommand(r: Rover, c: Command): Rover = c match {
    case TurnRight => r.copy(direction = rotateRight(r.direction))
    case TurnLeft  => r.copy(direction = rotateLeft(r.direction))
    case Move      => r.copy(position = move(r))
  }

  def move(r: Rover): Position = r.direction match {
    case SOUTH => moveSouth(r.position, r.planet)
    case NORTH => moveNorth(r.position, r.planet)
    case EAST  => moveEast(r.position, r.planet)
    case WEST  => moveWest(r.position, r.planet)
  }

  def moveSouth(position: Position, planet: Planet): Position = {
    val newX = (position.x + 1) % planet.height
    position.copy(x = newX)
  }

  def moveNorth(position: Position, planet: Planet): Position = {
    val newX = if (position.x > 0) position.x - 1 else planet.height - 1
    position.copy(x = newX)
  }

  def moveEast(position: Position, planet: Planet): Position = {
    val newY = (position.y + 1) % planet.width
    position.copy(y = newY)
  }

  def moveWest(position: Position, planet: Planet): Position = {
    val newY = if (position.y > 0) position.y - 1 else planet.width - 1
    position.copy(y = newY)
  }

  def rotateRight(direction: Direction): Direction =
    direction match {
      case NORTH => EAST
      case EAST  => SOUTH
      case SOUTH => WEST
      case WEST  => NORTH
    }

  def rotateLeft(direction: Direction): Direction =
    direction match {
      case NORTH => WEST
      case WEST  => SOUTH
      case SOUTH => EAST
      case EAST  => NORTH
    }
}

object IO_Ops {

  def ask(question: String) =
    puts(question).flatMap(_ => reads())

  def puts(str: String): IO[Unit] = IO {
    println(str)
  }
  def reads(): IO[String] = IO {
    readLine()
  }
}
