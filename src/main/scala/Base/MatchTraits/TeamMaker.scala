package Base

trait TeamMaker {
  def makeTeams(
      bluePlayer: Player,
      redPlayer: Player
  ): (Champion, Champion)
}

trait SimpleTeamMaker extends TeamMaker {
  def makeTeams(
      bluePlayer: Player,
      redPlayer: Player
  ): (Champion, Champion) =
    val blueChamp: Champion =
      bluePlayer.chooseBlueChampion()
    val redChamp: Champion =
      redPlayer.chooseRedChampion(blueChamp)
    (blueChamp, redChamp)
}
