package Base

import scala.util.Random

class Round(roundNum: Int, config: GameConfig) {
  val history = RoundHistory(roundNum)
  val playersPerMatch = 2 * config.teamSize
  config.listOfPlayers = Random.shuffle(config.listOfPlayers)
  for (
    pos <- 0 until config.numPlayers by playersPerMatch
    if pos + playersPerMatch <= config.numPlayers
  ) do history.addMatch(config.makeMatch(pos))

}
//   def runMatches(pos: Int, matches: RoundHistory): RoundHistory =
// if (config.listOfPlayers.length - pos < 2) {
//   return matches
// }
// val game: Match =
//   config.makeMatch(pos)
// matches.addMatch(game.history)
// runMatches(pos + 2, matches)
