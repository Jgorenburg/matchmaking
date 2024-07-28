package Base

import scala.util.Random

class Round(roundNum: Int, config: GameConfig) {
  val history: RoundHistory =
    config.listOfPlayers = Random.shuffle(config.listOfPlayers)
    def runMatches(pos: Int, matches: RoundHistory): RoundHistory =
      if (config.listOfPlayers.length - pos < 2) {
        return matches
      }
      val game: Match =
        config.makeMatch(List(pos), List(pos + 1))
      matches.addMatch(game.history)
      runMatches(pos + 2, matches)
    runMatches(0, RoundHistory(roundNum))
}
