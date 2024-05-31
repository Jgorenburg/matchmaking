import SimpleTournament.{SimpleGameConfig, SimpleMatch, SimplePlayer}
import base.{Champion, Round, Side}

class SimpleTournamentTests extends munit.FunSuite {
  test("basic player test") {
    val champ = Champion("ayu", 1)
    val player = SimplePlayer(Vector(Champion("ayu", 1)))
    assertEquals(player.champions(0), champ)
  }
  test("basic config test") {
    val config = SimpleGameConfig(1, 1)
    val champ = Champion("Amazon sailfin catfish", 0)
    assertEquals(config.ListOfChamps(0), champ)
    assertEquals(config.ListOfPlayers(0).champions(0), champ)
  }
  test("basic match test") {
    val config = SimpleGameConfig(2, 2)
    val testMatch =
      SimpleMatch(0, 1, config)

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
    val config = SimpleGameConfig(3, 2)
    val firstMatch = SimpleMatch(0, 1, config)
    val secondMatch = SimpleMatch(0, 2, config)

    assertEquals(secondMatch.history.winner, Side.Blueside)
  }

  test("simple round test") {
    val config = SimpleGameConfig(4, 2)
    val round = Round(0, config)

    assertEquals(round.history.getRoundNum(), 0)
    assertEquals(round.history.getMatches().length, 2)
  }
}
