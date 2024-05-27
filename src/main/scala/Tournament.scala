class Tournament(numPlayers: Int, numChamps: Int, numRounds: Int) {
  val config = GameConfig(numPlayers, numChamps)
  val history = runTournament()
  def runTournament(): TournamentHistory =
    def runRounds(roundNum: Int, rounds: TournamentHistory): TournamentHistory =
      if (roundNum >= numRounds) {
        return rounds
      }
      val round = Round(roundNum, config)
      rounds.addRound(round.history)
      return runRounds(roundNum + 1, rounds)
    return runRounds(0, TournamentHistory())
}