import Base.{Champion, GameConfig, Round, Side}
import PlayerTypes.{SimplePlayer, SimplePlayerMaker}
import MatchTypes.{SimpleMatch, SimpleMatchMaker}

class SimpleTournamentTests extends munit.FunSuite {
  test("basic player test") {
    val champ = Champion("ayu", 1)
    val player = SimplePlayer(Vector(Champion("ayu", 1)))
    assertEquals(player.champions(0), champ)
  }
  test("basic config test") {
    val config = GameConfig(1, 1, SimplePlayerMaker, SimpleMatchMaker)
    val champ = Champion("Amazon sailfin catfish", 0)
    assertEquals(config.ListOfChamps(0), champ)
    assertEquals(config.ListOfPlayers(0).champions(0), champ)
  }
  test("basic match test") {
    val config = GameConfig(2, 2, SimplePlayerMaker, SimpleMatchMaker)
    val testMatch =
      SimpleMatch(config.getPlayer(0), config.getPlayer(1))

    assertEquals(
      config.ListOfPlayers(0).getWinPercent(config.ListOfChamps(0)),
      0.0f
    )
    assertEquals(
      config.ListOfPlayers(0).getWinPercent(config.ListOfChamps(1)),
      1.0f
    )
    assertEquals(
      config.ListOfPlayers(1).getWinPercent(config.ListOfChamps(0)),
      0.0f
    )
    assertEquals(
      config.ListOfPlayers(1).getWinPercent(config.ListOfChamps(1)),
      1.0f
    )

  }

  test("player learning test") {
    val config = GameConfig(3, 2, SimplePlayerMaker, SimpleMatchMaker)
    val firstMatch = SimpleMatch(config.getPlayer(0), config.getPlayer(1))

    val secondMatch = SimpleMatch(config.getPlayer(0), config.getPlayer(2))

    assertEquals(secondMatch.history.winner, Side.Blueside)
  }

  test("simple round test") {
    val config = GameConfig(4, 2, SimplePlayerMaker, SimpleMatchMaker)
    val round = Round(0, config)

    assertEquals(round.history.roundNum, 0)
    assertEquals(round.history.matches.length, 2)
  }
}
