package marsroverkata

import marsroverkata.Data._

object GamePlay {

  def handleCommands(r: Rover, cs: List[Command]): (Boolean, Rover) = cs match {
    case c :: rest => handleCommand(r, c).fold((true, r))(r => handleCommands(r, rest))
    case Nil => (false, r)
  }

  def handleCommand(r: Rover, c: Command): Option[Rover] = c match {
    case TurnRight     => Some(r.copy(direction = rotateRight(r.direction)))
    case TurnLeft      => Some(r.copy(direction = rotateLeft(r.direction)))
    case MoveForward   => moveForward(r).map(p => r.copy(position = p))
    case MoveBackward  => moveBackward(r).map(p => r.copy(position = p))
    case UnknownCommand => Some(r)
  }

  def moveForward(r: Rover): Option[Position] = r.direction match {
    case S => moveSouth(r.position, r.planet)
    case N => moveNorth(r.position, r.planet)
    case E => moveEast(r.position, r.planet)
    case W => moveWest(r.position, r.planet)
  }

  def moveBackward(r: Rover): Option[Position] = r.direction match {
    case S => moveNorth(r.position, r.planet)
    case N => moveSouth(r.position, r.planet)
    case E => moveWest(r.position, r.planet)
    case W => moveEast(r.position, r.planet)
  }

  def moveSouth(position: Position, planet: Planet): Option[Position] = {
    val newX = (position.x + 1) % planet.height
    testHitObstacles(planet, position.copy(x = newX))
  }

  def moveNorth(position: Position, planet: Planet): Option[Position] = {
    val newX = if (position.x > 0) position.x - 1 else planet.height - 1
    testHitObstacles(planet, position.copy(x = newX))
  }

  def moveEast(position: Position, planet: Planet): Option[Position] = {
    val newY = (position.y + 1) % planet.width
    testHitObstacles(planet, position.copy(y = newY))
  }

  def moveWest(position: Position, planet: Planet): Option[Position] = {
    val newY = if (position.y > 0) position.y - 1 else planet.width - 1
    testHitObstacles(planet, position.copy(y = newY))
  }

  private def testHitObstacles(planet: Planet, candidate: Position): Option[Position] = {
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
}
