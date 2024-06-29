import Base.{GameConfig, Tournament}
import PlayerTypes.SimplePlayerMaker
import MatchTypes.{RandomMatchMaker, SimpleMatchMaker}
import GameTypes.BasicGame

@main def hello(): Unit =
  val simpleconfig =
    new GameConfig(10, 10, SimplePlayerMaker, SimpleMatchMaker, BasicGame)
  val simpletourny = Tournament(simpleconfig, 1000)
  val randomconfig =
    new GameConfig(10, 10, SimplePlayerMaker, RandomMatchMaker, BasicGame)
  val randomtourny = Tournament(randomconfig, 1000)
  val printer = new Printer
  printer.writeToFile(simpletourny, "results/salmon.txt")
  printer.writeToFile(randomtourny, "results/cod.txt")
