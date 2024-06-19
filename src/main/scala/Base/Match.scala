package Base

trait Match(
    val bluePlayer: Player,
    val redPlayer: Player,
    val meta: Meta
) extends TeamMaker,
      Winner {
  val history: MatchHistory =
    val teams =
      makeTeams(bluePlayer, redPlayer)
    val winner: Side = decideWinner(teams)
    updateRecords(teams, winner)
    MatchHistory(
      (bluePlayer, teams(0)),
      (redPlayer, teams(1)),
      winner
    )

  def updateRecords(
      teams: (Champion, Champion),
      winner: Side
  ) =
    val (blueChamp, redChamp) = teams
    bluePlayer.updateRecord(blueChamp, redChamp, winner == Side.Blueside)
    redPlayer.updateRecord(redChamp, blueChamp, winner == Side.Redside)

}

trait MatchMaker:
  def makeMatch(bluePlayer: Player, redPlayer: Player, meta: Meta): Match
