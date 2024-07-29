package Base

trait TeamMaker {
  implicit def addOpt(lst: List[Champion]): Option[List[Champion]] =
    lst match
      case Nil => None
      case _   => Some(lst)

  def draftChamps(
      blueTeam: Team,
      redTeam: Team
  ): (Composition, Composition, List[Champion])
}

trait SimpleDraft extends TeamMaker {
  def draftChamps(
      blueTeam: Team,
      redTeam: Team
  ): (Composition, Composition, List[Champion]) =
    assert(blueTeam.length == redTeam.length)

    var blueComp: Composition = List()
    var redComp: Composition = List()
    for (x <- 0 until blueTeam.length) do {
      blueComp = blueTeam(x).chooseChampion(blueComp, redComp) :: blueComp
      redComp = redTeam(x).chooseChampion(redComp, blueComp) :: redComp
    }
    (blueComp, redComp, List())
}

trait DraftWithBans extends TeamMaker {
  def draftChamps(
      blueTeam: Team,
      redTeam: Team
  ): (Composition, Composition, List[Champion]) =
    assert(!blueTeam.isEmpty)
    assert(blueTeam.length == redTeam.length)

    var bans: List[Champion] = List(blueTeam(0).blueBan())
    bans = redTeam(0).redBan(bans) :: bans

    var blueComp: Composition = List()
    var redComp: Composition = List()
    for (x <- 0 until blueTeam.length) do {
      blueComp = blueTeam(x).chooseChampion(blueComp, redComp, bans) :: blueComp
      redComp = redTeam(x).chooseChampion(redComp, blueComp, bans) :: redComp
    }
    (blueComp, redComp, bans)
}
