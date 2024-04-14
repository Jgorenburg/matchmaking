import scala.util.Random
class Round(roundNum: Int, config: GameConfig) {
  val history = runRound()

  def runRound(): RoundHistory =
    config.ListOfPlayers = Random.shuffle(config.ListOfPlayers)
    def runMatches(pos: Int, matches: RoundHistory): RoundHistory =
      if (config.ListOfPlayers.length - pos < 2) {
        return matches
      }
      val game: Match =
        Match(config.ListOfPlayers(pos), config.ListOfPlayers(pos + 1))
      matches.addMatch(game.history)
      return runMatches(pos + 2, matches)
    return runMatches(0, RoundHistory(roundNum))

}
