import Base.{Champion, GameConfig, Round, Side}
import PlayerTypes.{SimplePlayer, SimplePlayerMaker}
import MatchTypes.{RandomMatch, RandomMatchMaker}

class RandomTournamentTests extends munit.FunSuite {
  test("basic player test") {
    val champ = Champion("ayu", 1)
    val player = SimplePlayer(Vector(Champion("ayu", 1)))
    assertEquals(player.champions(0), champ)
  }
  test("basic config test") {
    val config = GameConfig(1, 1, SimplePlayerMaker, RandomMatchMaker)
    val champ = Champion("Amazon sailfin catfish", 0)
    assertEquals(config.ListOfChamps(0), champ)
    assertEquals(config.ListOfPlayers(0).champions(0), champ)
  }
  test("basic match test") {
    val config = GameConfig(2, 2, SimplePlayerMaker, RandomMatchMaker)
    val testMatch =
      RandomMatch(config.getPlayer(0), config.getPlayer(1))

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
    val config = GameConfig(3, 2, SimplePlayerMaker, RandomMatchMaker)
    val firstMatch = RandomMatch(config.getPlayer(0), config.getPlayer(1))
    val secondMatch = RandomMatch(config.getPlayer(0), config.getPlayer(2))

    assertEquals(secondMatch.history.winner, Side.Blueside)
  }

  test("simple round test") {
    val config = GameConfig(4, 2, SimplePlayerMaker, RandomMatchMaker)
    val round = Round(0, config)

    assertEquals(round.history.roundNum, 0)
    assertEquals(round.history.matches.length, 2)
  }

  test("high win likelyhood test") {
    val blue = Champion("nine", 9)
    val red = Champion("one", 1)
    ratioTest(blue, red, 0.8f, 1.0f)
  }

  test("low win likelyhood test") {
    val blue = Champion("one", 1)
    val red = Champion("nine", 9)
    ratioTest(blue, red, 0.0f, 0.2f)
  }

  test("equal win likelyhood test") {
    val blue = Champion("five", 5)
    val red = Champion("five", 5)
    ratioTest(blue, red, 0.4f, 0.6f)
  }

  def ratioTest(blue: Champion, red: Champion, low: Float, high: Float): Unit =
    val totalMatches = 1000f
    val config = GameConfig(3, 2, SimplePlayerMaker, RandomMatchMaker)
    val dummyMatch = RandomMatch(config.getPlayer(0), config.getPlayer(1))

    def blueWon(): Int =
      if dummyMatch.decideWinner(blue, red) == Side.Blueside
      then 1
      else 0

    def repeatPlay(blueWins: Int, matchesLeft: Float): Float =
      if matchesLeft == 0 then blueWins / totalMatches
      else
        repeatPlay(
          blueWins + blueWon(),
          matchesLeft - 1
        )

    val winPercert = repeatPlay(0, totalMatches)
    assert(winPercert > low)
    assert(winPercert < high)
}
