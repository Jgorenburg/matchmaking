package Base

trait Match extends TeamMaker, Winner {
  def bluePlayer: Int
  def redPlayer: Int
  def config: GameConfig
  val history: MatchHistory =
    val teams =
      makeTeams(bluePlayer, redPlayer, config)
    val winner: Side = decideWinner(teams)
    updateRecords(teams, winner)
    MatchHistory(
      (config.getPlayer(bluePlayer), teams(0)),
      (config.getPlayer(redPlayer), teams(1)),
      winner
    )

  def updateRecords(
      teams: (Champion, Champion),
      winner: Side
  ) =
    val (blueChamp, redChamp) = teams
    config
      .getPlayer(bluePlayer)
      .updateRecord(blueChamp, redChamp, winner == Side.Blueside)
    config
      .getPlayer(redPlayer)
      .updateRecord(redChamp, blueChamp, winner == Side.Redside)

}
