package base

trait Match(bluePlayer: Int, redPlayer: Int, config: GameConfig) {
  val history = runMatch()

  def runMatch(): MatchHistory
  def decideWinner(blueChamp: Champion, redChamp: Champion): Side

  def updateRecords(
      blueChamp: Champion,
      redChamp: Champion,
      winner: Side
  ) =
    config
      .getPlayer(bluePlayer)
      .updateRecord(blueChamp, redChamp, winner == Side.Blueside)
    config
      .getPlayer(redPlayer)
      .updateRecord(redChamp, blueChamp, winner == Side.Redside)

}
