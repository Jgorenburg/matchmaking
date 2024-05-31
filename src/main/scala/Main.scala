import SimpleTournament.SimpleGameConfig
import base.Tournament

@main def hello(): Unit =
  val test = Tournament(SimpleGameConfig(10, 10), 15)
  val printer = new Printer
  printer.writeToFile(test, "results/cod.txt")

def msg = "I was compiled by Scala 3. :)"
