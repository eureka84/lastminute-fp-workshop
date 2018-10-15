package marsroverkata

import cats.effect.IO

import scala.io.StdIn.readLine

object IO_Ops {

  def ask(question: String): IO[String] =
    puts(question).flatMap(_ => reads())

  def puts(str: String): IO[Unit] = IO {
    println(str)
  }
  def reads(): IO[String] = IO {
    readLine()
  }
}
