import SimpleTournament.SimpleGameConfig
import base.Tournament

@main def hello(): Unit =
  val config = SimpleGameConfig(10, 10)
  val test = Tournament(config, 15)
  val printer = new Printer
  printer.writeToFile(test, "results/salmon.txt")
