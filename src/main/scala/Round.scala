package base

import scala.util.Random

class Round(roundNum: Int, config: GameConfig) {
  val history: RoundHistory = runRound()

  def runRound(): RoundHistory =
    config.ListOfPlayers = Random.shuffle(config.ListOfPlayers)
    def runMatches(pos: Int, matches: RoundHistory): RoundHistory =
      if (config.ListOfPlayers.length - pos < 2) {
        return matches
      }
      val game: Match =
        config.makeMatch(pos, pos + 1)
      matches.addMatch(game.history)
      runMatches(pos + 2, matches)
    runMatches(0, RoundHistory(roundNum))
}
