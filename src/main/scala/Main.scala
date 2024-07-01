import Base.{GameConfig, Tournament}
import PlayerTypes.SimplePlayerMaker
import MatchTypes.{RandomMatchMaker, SimpleMatchMaker}
import GameTypes.{BasicGame, RockPaperScissors}

@main def hello(): Unit =
  val simpleconfig =
    new GameConfig(10, 10, SimplePlayerMaker, SimpleMatchMaker, BasicGame)
  val simpletourny = Tournament(simpleconfig, 1000)
  val randomconfig =
    new GameConfig(10, 10, SimplePlayerMaker, RandomMatchMaker, BasicGame)
  val randomtourny = Tournament(randomconfig, 1000)
  val basicstyleconfig =
    new GameConfig(
      2,
      3,
      SimplePlayerMaker,
      SimpleMatchMaker,
      RockPaperScissors
    )
  val basicstyletourny = Tournament(basicstyleconfig, 1000)
  val printer = new Printer
  printer.writeToFile(simpletourny, "results/salmon.txt")
  printer.writeToFile(randomtourny, "results/cod.txt")
  printer.writeToFile(basicstyletourny, "results/trout.txt")
