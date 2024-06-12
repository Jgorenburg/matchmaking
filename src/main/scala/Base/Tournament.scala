package Base

class Tournament(
    val config: GameConfig,
    numRounds: Int
) {
  val history: TournamentHistory =
    def runRounds(roundNum: Int, rounds: TournamentHistory): TournamentHistory =
      if (roundNum >= numRounds) {
        return rounds
      }
      val round = Round(roundNum, config)
      rounds.addRound(round.history)
      runRounds(roundNum + 1, rounds)
    runRounds(0, TournamentHistory())
}
