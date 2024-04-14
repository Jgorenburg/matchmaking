// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html

class MySuite extends munit.FunSuite {
  test("example test that succeeds") {
    val obtained = 42
    val expected = 42
    assertEquals(obtained, expected)
  }

  test("basic player test") {
    val champ = Champion("ayu", 1)
    val player = Player(Vector(Champion("ayu", 1)))
    assertEquals(player.champions(0), champ)
  }

  test("basic config test") {
    val config = GameConfig(1, 1)
    val champ = Champion("Amazon sailfin catfish", 0)
    assertEquals(config.ListOfChamps(0), champ)
    assertEquals(config.ListOfPlayers(0).champions(0), champ)
  }

  test("basic match test") {
    val config = GameConfig(2, 2)
    val testMatch =
      Match(0, 1, config)

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
    val config = GameConfig(3, 2)
    val firstMatch = Match(0, 1, config)
    val secondMatch = Match(0, 2, config)

    assertEquals(secondMatch.history.winner, Side.Blueside)
  }

  test("simple round test") {
    val config = GameConfig(4, 2)
    val round = Round(0, config)

    assertEquals(round.history.getRoundNum(), 0)
    assertEquals(round.history.getMatches().length, 2)
  }
}
