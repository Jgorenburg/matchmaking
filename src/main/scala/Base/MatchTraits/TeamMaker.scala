package Base

trait TeamMaker {
  implicit def addOpt(lst: List[Champion]): Option[List[Champion]] =
    lst match
      case Nil => None
      case _   => Some(lst)

  def draftChamps(
      blueTeam: Team,
      redTeam: Team
  ): (Composition, Composition)
}

trait SimpleTeamMaker extends TeamMaker {
  def draftChamps(
      blueTeam: Team,
      redTeam: Team
  ): (Composition, Composition) =
    val bluePlayer = blueTeam(0)
    val redPlayer = redTeam(0)
    val blueChamp: Champion =
      bluePlayer.chooseBlueChampion()
    val redChamp: Champion =
      redPlayer.chooseRedChampion(blueChamp)
    (blueChamp, redChamp)
}

trait BansAndSinglePlayer extends TeamMaker {
  def draftChamps(
      blueTeam: Team,
      redTeam: Team
  ): (Composition, Composition) =
    val bluePlayer = blueTeam(0)
    val redPlayer = redTeam(0)
    var bans: List[Champion] = List(bluePlayer.blueBan(None))
    bans = redPlayer.redBan(bans) :: bans
    val blueChamp: Champion =
      bluePlayer.chooseBlueChampion(bans)
    val redChamp: Champion =
      redPlayer.chooseRedChampion(blueChamp, bans)
    (blueChamp, redChamp)
}
