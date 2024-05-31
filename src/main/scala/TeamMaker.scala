package base

trait TeamMaker {
  def makeTeams(
      bluePlayer: Int,
      redPlayer: Int,
      config: GameConfig
  ): (Champion, Champion)
}

trait SimpleTeamMaker extends TeamMaker {
  def makeTeams(
      bluePlayer: Int,
      redPlayer: Int,
      config: GameConfig
  ): (Champion, Champion) =
    val blueChamp: Champion =
      config.getPlayer(bluePlayer).chooseBlueChampion()
    val redChamp: Champion =
      config.getPlayer(redPlayer).chooseRedChampion(blueChamp)
    (blueChamp, redChamp)
}
