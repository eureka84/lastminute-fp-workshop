package marsroverkata

import marsroverkata.Data._

object DataParsers {

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
    case _   => UnknownCommand
  }

}
