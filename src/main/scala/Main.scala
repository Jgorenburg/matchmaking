import SimpleTournament.SimpleGameConfig
import base.Tournament

@main def hello(): Unit =
  val test = Tournament(SimpleGameConfig(10, 10), 15)
  println(test.history)
