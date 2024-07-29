package Base

trait Match(
    val blueTeam: Team,
    val redTeam: Team,
    val meta: Meta
) extends TeamMaker,
      Winner {
  val history: MatchHistory =
    val teamComps =
      draftChamps(blueTeam, redTeam)
    val winner: Side.Value = decideWinner(teamComps)
    updateRecords(teamComps, winner)
    MatchHistory(
      (blueTeam, teamComps(0)),
      (redTeam, teamComps(1)),
      winner
    )

  def updateRecords(
      teamComps: (Composition, Composition),
      winner: Side.Value
  ) =
    val (blueComp, redComp) = teamComps
    blueTeam.map(_.updateRecord(blueComp, redComp, winner == Side.Blueside))
    redTeam.map(_.updateRecord(redComp, blueComp, winner == Side.Redside))

}

trait MatchMaker:
  def makeMatch(blueTeam: Team, redTeam: Team, meta: Meta): Match
