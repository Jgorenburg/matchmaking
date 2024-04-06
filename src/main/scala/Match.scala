class Match(blueTeam: Player, redTeam: Player) {
  def runMatch(): MatchHistory =
    val blueChamp: Champion = blueTeam.chooseChampion(None)
    val redChamp: Champion = redTeam.chooseChampion(Some(blueChamp))
    val winner: Side = decideWinner(blueChamp, redChamp)
    updateRecords(blueChamp, redChamp, winner)
    MatchHistory((blueTeam, blueChamp), (redTeam, redChamp), winner)

  def decideWinner(blueChamp: Champion, redChamp: Champion): Side =
    if blueChamp.skill > redChamp.skill then Side.Blueside else Side.Redside

  def updateRecords(blueChamp: Champion, redChamp: Champion, winner: Side) =
    blueTeam.updateRecord(blueChamp, winner == Side.Blueside)
    redTeam.updateRecord(redChamp, winner == Side.Redside)

}
