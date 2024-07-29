package Base

trait Match(
    val blueTeam: Team,
    val redTeam: Team,
    val meta: Meta
) extends TeamMaker,
      Winner {
  val history: MatchHistory =
    val (blueComp, redComp, bans) =
      draftChamps(blueTeam, redTeam)
    val winner: Side.Value = decideWinner(blueComp, redComp)
    updateRecords(blueComp, redComp, winner)
    MatchHistory(
      (blueTeam, blueComp),
      (redTeam, redComp),
      winner,
      bans
    )

  def updateRecords(
      blueComp: Composition,
      redComp: Composition,
      winner: Side.Value
  ) =
    blueTeam.map(_.updateRecord(blueComp, redComp, winner == Side.Blueside))
    redTeam.map(_.updateRecord(redComp, blueComp, winner == Side.Redside))

}

trait MatchMaker:
  val pureSkill: Boolean
  def makeMatch(blueTeam: Team, redTeam: Team, meta: Meta): Match
