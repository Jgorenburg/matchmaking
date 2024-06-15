import Base.{GameConfig, Tournament}
import PlayerTypes.SimplePlayerMaker
import MatchTypes.{RandomMatchMaker, SimpleMatchMaker}

@main def hello(): Unit =
  val simpleconfig = new GameConfig(10, 10, SimplePlayerMaker, SimpleMatchMaker)
  val simpletourny = Tournament(simpleconfig, 15)
  val randomconfig = new GameConfig(10, 10, SimplePlayerMaker, RandomMatchMaker)
  val randomtourny = Tournament(randomconfig, 15)
  val printer = new Printer
  printer.writeToFile(simpletourny, "results/salmon.txt")
  printer.writeToFile(randomtourny, "results/cod.txt")
