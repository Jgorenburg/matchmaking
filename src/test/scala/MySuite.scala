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

  test("Match.decideWinner test") {
    val testMatch = Match(null, null)
    val champ1 = Champion("Reticulated Corydoras", 0)
    val champ2 = Champion("Malawi golden cichlid", 1)
    assertEquals(testMatch.decideWinner(champ1, champ2), Side.Redside)
  }

  test("Match.updateRecords test") {
    val config = GameConfig(2, 2)
    val testMatch =
      Match(config.ListOfPlayers(0), config.ListOfPlayers(1))
    testMatch.updateRecords(
      config.ListOfChamps(0),
      config.ListOfChamps(1),
      Side.Redside
    )
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
}
