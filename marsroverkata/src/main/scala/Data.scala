package marsroverkata

object Data {
  case class Planet(width: Int, height: Int, obstacles: List[Obstacle] = Nil)
  type Obstacle = Position
  case class Position(x: Int, y: Int)
  case class Rover(position: Position, direction: Direction, planet: Planet)

  sealed trait Command
  case object TurnRight     extends Command
  case object TurnLeft      extends Command
  case object MoveForward   extends Command
  case object MoveBackward  extends Command
  case object UnknownCommand extends Command

  sealed trait Direction
  case object N extends Direction
  case object E extends Direction
  case object S extends Direction
  case object W extends Direction
}
