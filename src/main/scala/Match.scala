import scala.util.Random
class Match(bluePlayer: Int, redPlayer: Int, config: GameConfig) {
  val history = runMatch()

  def runMatch(): MatchHistory =
    val blueChamp: Champion =
      config.getPlayer(bluePlayer).chooseChampion(None)
    val redChamp: Champion =
      config.getPlayer(redPlayer).chooseChampion(Some(blueChamp))
    val winner: Side = decideWinner(blueChamp, redChamp)
    updateRecords(blueChamp, redChamp, winner, config)
    return MatchHistory(
      (config.getPlayer(bluePlayer), blueChamp),
      (config.getPlayer(redPlayer), redChamp),
      winner
    )

  def decideWinner(blueChamp: Champion, redChamp: Champion): Side =
    if blueChamp.skill > Random.nextInt(blueChamp.skill + redChamp.skill)
    then Side.Blueside
    else Side.Redside

  def updateRecords(
      blueChamp: Champion,
      redChamp: Champion,
      winner: Side,
      config: GameConfig
  ) =
    config
      .getPlayer(bluePlayer)
      .updateRecord(blueChamp, redChamp, winner == Side.Blueside)
    config
      .getPlayer(redPlayer)
      .updateRecord(redChamp, blueChamp, winner == Side.Redside)

}
