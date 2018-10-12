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
      .flatMap(p => readObstacles().map(os => p.copy(obstacles = os)))
      .flatMap(p => readPosition().map(pos => Rover(pos, N, p)))
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

  def display(r: Rover): IO[Unit] = puts(format(r))

  def format(r: Rover): String =
    s"${r.direction}:${r.position.x},${r.position.y}"

  def parsePlanet(s: String): Planet = {
    val width :: height :: Nil = s.split("x").toList
    Planet(width.toInt, height.toInt)
  }

  def parseObstacles(s: String): List[Obstacle] =
    if (s.isEmpty) Nil
    else s.split("/").map(parsePosition).toList

  def parsePosition(s: String): Position = {
    val x :: y :: Nil = s.split(",").toList
    Position(x.toInt, y.toInt)
  }

  def parseCommands(s: String): List[Command] = s.map(parseCommand).toList

  def parseCommand(c: Char): Command = c match {
    case 'l' => TurnLeft
    case 'r' => TurnRight
    case 'f' => MoveForward
    case 'b' => MoveBackward
    case _   => UnkownCommand
  }

  def handleCommands(r: Rover, cs: List[Command]): Rover = cs match {
    case c :: rest => handleCommand(r, c).fold(r)(handleCommands(_, rest))
    case Nil => r
  }


  def handleCommand(r: Rover, c: Command): Option[Rover] = c match {
    case TurnRight     => Some(r.copy(direction = rotateRight(r.direction)))
    case TurnLeft      => Some(r.copy(direction = rotateLeft(r.direction)))
    case MoveForward   => moveForward(r).map(p=> r.copy(position = p))
    case MoveBackward  => moveBackward(r).map(p=> r.copy(position = p))
    case UnkownCommand => Some(r)
  }

  def moveForward(r: Rover): Option[Position] = {
    val position = r.direction match {
      case S => moveSouth(r.position, r.planet)
      case N => moveNorth(r.position, r.planet)
      case E => moveEast(r.position, r.planet)
      case W => moveWest(r.position, r.planet)
    }
    tryToMove(r.planet, position)
  }

  def moveBackward(r: Rover): Option[Position] = {
    val position = r.direction match {
      case S => moveNorth(r.position, r.planet)
      case N => moveSouth(r.position, r.planet)
      case E => moveWest(r.position, r.planet)
      case W => moveEast(r.position, r.planet)
    }
    tryToMove(r.planet, position)
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

  private def tryToMove(planet: Planet, candidate: Position): Option[Position] = {
    if (planet.obstacles.contains(candidate)) None
    else Some(candidate)
  }

  def rotateRight(direction: Direction): Direction =
    direction match {
      case N => E
      case E => S
      case S => W
      case W => N
    }

  def rotateLeft(direction: Direction): Direction =
    direction match {
      case N => W
      case W => S
      case S => E
      case E => N
    }

  case class Planet(width: Int, height: Int, obstacles: List[Obstacle] = Nil)
  type Obstacle = Position
  case class Position(x: Int, y: Int)
  case class Rover(position: Position, direction: Direction, planet: Planet)

  sealed trait Command
  case object TurnRight     extends Command
  case object TurnLeft      extends Command
  case object MoveForward   extends Command
  case object MoveBackward  extends Command
  case object UnkownCommand extends Command

  sealed trait Direction
  case object N extends Direction
  case object E extends Direction
  case object S extends Direction
  case object W extends Direction

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
