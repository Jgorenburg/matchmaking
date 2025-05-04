import Base.{
  Default,
  GameConfig,
  GameMaker,
  Match,
  Meta,
  Round,
  Side,
  SimpleDraft,
  singleChampComposition,
  singlePlayerTeam,
  SkillAndVarianceWinner
}
import PlayerTypes.{SimplePlayer, SimplePlayerMaker}
import MatchTypes.RandomMatchMaker
import MetaTypes.BasicMetaMaker
import GameTypes.BasicGame

class RandomTournamentTests extends munit.FunSuite {
  test("basic player test") {
    val champ = Default("ayu")
    val player = SimplePlayer("Amaryllis", Array(Default("ayu")))
    assertEquals(player.champions(0), champ)
  }
  test("basic config test") {
    val config =
      GameConfig(1, 1, 1, SimplePlayerMaker, RandomMatchMaker, BasicGame)
    val champ = Default("Amazon sailfin catfish")
    assertEquals(config.listOfChamps(0), champ)
    assertEquals(config.listOfPlayers(0).champions(0), champ)
  }
  test("basic match test") {
    val config =
      GameConfig(2, 2, 1, SimplePlayerMaker, RandomMatchMaker, BasicGame)
    val testMatch =
      new Match(config.getPlayer(0), config.getPlayer(1), config.meta)
        with SimpleDraft
        with SkillAndVarianceWinner

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
      GameConfig(3, 2, 1, SimplePlayerMaker, RandomMatchMaker, BasicGame)
    val firstMatch =
      new Match(config.getPlayer(0), config.getPlayer(1), config.meta)
        with SimpleDraft
        with SkillAndVarianceWinner
    val secondMatch =
      new Match(config.getPlayer(0), config.getPlayer(2), config.meta)
        with SimpleDraft
        with SkillAndVarianceWinner

    assertEquals(secondMatch.history.winner, Side.Blueside)
  }

  test("simple round test") {
    val config =
      GameConfig(4, 2, 1, SimplePlayerMaker, RandomMatchMaker, BasicGame)
    val round = Round(0, config)

    assertEquals(round.history.roundNum, 0)
    assertEquals(round.history.matches.length, 2)
  }

  test("high win likelyhood test") {
    ratioTest(9, 1)
  }

  test("low win likelyhood test") {
    ratioTest(1, 9)
  }

  test("equal win likelyhood test") {
    ratioTest(5, 5)
  }

  def ratioTest(
      blueStrength: Int,
      redStrength: Int
  ): Unit =
    val totalMatches = 1000f
    val blue = Default("blue")
    val red = Default("red")
    val meta =
      val maker = new BasicMetaMaker
      maker.makeMeta(Map(blue -> blueStrength, red -> redStrength))
    val bluePlayer, redPlayer =
      SimplePlayerMaker.makePlayer("Amaryllis", Array(blue, red))
    val dummyMatch =
      new Match(bluePlayer, redPlayer, meta)
        with SimpleDraft
        with SkillAndVarianceWinner

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
    val expected = blueStrength / (redStrength + blueStrength + 0.0f)
    assert(winPercert > expected - 0.1)
    assert(winPercert < expected + 0.1)
}
