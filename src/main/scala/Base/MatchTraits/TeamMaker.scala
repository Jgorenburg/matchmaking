package Base

trait TeamMaker {
  implicit def addOpt(lst: List[Champion]): Option[List[Champion]] =
    lst match
      case Nil => None
      case _   => Some(lst)

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

trait BansAndSinglePlayer extends TeamMaker {
  def makeTeams(
      bluePlayer: Player,
      redPlayer: Player
  ): (Champion, Champion) =
    var bans: List[Champion] = List(bluePlayer.blueBan(None))
    bans = redPlayer.redBan(bans) :: bans
    val blueChamp: Champion =
      bluePlayer.chooseBlueChampion(bans)
    val redChamp: Champion =
      redPlayer.chooseRedChampion(blueChamp, bans)
    (blueChamp, redChamp)
}
