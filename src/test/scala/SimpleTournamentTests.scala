import Base.{
  Default,
  GameConfig,
  Match,
  PureSkillWinner,
  Round,
  Side,
  SimpleDraft,
  singleChampComposition,
  singlePlayerTeam
}
import PlayerTypes.{SimplePlayer, SimplePlayerMaker}
import MatchTypes.SimpleMatchMaker
import MetaTypes.BasicMetaMaker
import GameTypes.BasicGame

class SimpleTournamentTests extends munit.FunSuite {
  test("basic player test") {
    val champ = Default("ayu")
    val player = SimplePlayer("Amaryllis", Array(Default("ayu")))
    assertEquals(player.champions(0), champ)
  }
  test("basic config test") {
    val config =
      GameConfig(1, 1, 1, SimplePlayerMaker, SimpleMatchMaker, BasicGame)
    val champ = Default("Amazon sailfin catfish")
    assertEquals(config.listOfChamps(0), champ)
    assertEquals(config.listOfPlayers(0).champions(0), champ)
  }
  test("basic match test") {
    val config =
      GameConfig(2, 2, 1, SimplePlayerMaker, SimpleMatchMaker, BasicGame)
    val bob = 1 + 1
    val testMatch =
      new Match(config.getPlayer(0), config.getPlayer(1), config.meta)
        with SimpleDraft
        with PureSkillWinner

    assertEquals(
      config.listOfPlayers(0).getWinPercent(config.listOfChamps(0)),
      0.0f
    )
    assertEquals(
      config.listOfPlayers(0).getWinPercent(config.listOfChamps(1)),
      1.0f
    )
    assertEquals(
      config.listOfPlayers(1).getWinPercent(config.listOfChamps(0)),
      0.0f
    )
    assertEquals(
      config.listOfPlayers(1).getWinPercent(config.listOfChamps(1)),
      1.0f
    )

  }

  test("player learning test") {
    val config =
      GameConfig(3, 2, 1, SimplePlayerMaker, SimpleMatchMaker, BasicGame)
    val firstMatch =
      new Match(config.getPlayer(0), config.getPlayer(1), config.meta)
        with SimpleDraft
        with PureSkillWinner

    val secondMatch =
      new Match(config.getPlayer(0), config.getPlayer(2), config.meta)
        with SimpleDraft
        with PureSkillWinner

    assertEquals(secondMatch.history.winner, Side.Blueside)
  }

  test("simple round test") {
    val config =
      GameConfig(4, 2, 1, SimplePlayerMaker, SimpleMatchMaker, BasicGame)
    val round = Round(0, config)

    assertEquals(round.history.roundNum, 0)
    assertEquals(round.history.matches.length, 2)
  }
}
