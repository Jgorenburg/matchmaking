import Base.{GameConfig, Tournament}
import PlayerTypes.{SimplePlayerMaker, MatchupAwarePlayerMaker}
import MatchTypes.{RandomMatchMaker, RandomWithBansMatchmaker, SimpleMatchMaker}
import GameTypes.{BasicGame, MTG, RockPaperScissors}
import MatchTypes.SimpleWithBansMatchmaker

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
      MatchupAwarePlayerMaker,
      RandomMatchMaker,
      RockPaperScissors
    )
  val basicstyletourny = Tournament(basicstyleconfig, 1000)
  val mtg =
    new GameConfig(
      4,
      5,
      MatchupAwarePlayerMaker,
      RandomMatchMaker,
      MTG
    )
  val mtgtourney = Tournament(mtg, 1000)
  val simplebansconfig =
    new GameConfig(
      4,
      4,
      SimplePlayerMaker,
      SimpleWithBansMatchmaker,
      BasicGame
    )
  val simplebanstourny = Tournament(simplebansconfig, 1000)
  val printer = new Printer
  printer.writeToFile(simpletourny, "results/simple.txt")
  printer.writeToFile(randomtourny, "results/random.txt")
  printer.writeToFile(basicstyletourny, "results/rps.txt")
  printer.writeToFile(mtgtourney, "results/mtg.txt")
  printer.writeToFile(simplebanstourny, "results/bans.txt")
