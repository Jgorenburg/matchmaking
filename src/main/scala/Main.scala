import SimpleTournament.SimpleGameConfig
import SimpleRandomTournament.RandomGameConfig
import Base.Tournament

@main def hello(): Unit =
  val simpleconfig = SimpleGameConfig(10, 10)
  val simpletourny = Tournament(simpleconfig, 15)
  val randomconfig = RandomGameConfig(10, 10)
  val randomtourny = Tournament(randomconfig, 15)
  val printer = new Printer
  printer.writeToFile(simpletourny, "results/salmon.txt")
  printer.writeToFile(randomtourny, "results/cod.txt")
