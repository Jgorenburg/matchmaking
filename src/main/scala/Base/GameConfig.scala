package Base

class GameConfig(
    val numPlayers: Int,
    val numChamps: Int,
    val teamSize: Int,
    val playerMaker: PlayerMaker,
    val matchMaker: MatchMaker,
    val gameMaker: GameMaker
):
  val listOfChamps: Array[Champion] =
    gameMaker.makeChamps(numChamps)
  val meta: Meta = gameMaker.makeMeta(listOfChamps)

  var listOfPlayers: Vector[Player] =
    (1 to numPlayers).toVector.map(i => playerMaker.makePlayer(listOfChamps))

  // def makeMatch(blueTeam: List[Int], redTeam: List[Int]): Match =
  //   matchMaker.makeMatch(blueTeam.map(getPlayer), redTeam.map(getPlayer), meta)

  def makeMatch(startPos: Int): Match =
    val blueTeam =
      for (pos <- startPos until startPos + teamSize) yield getPlayer(pos)
    val redTeam =
      for (pos <- startPos + teamSize until startPos + 2 * teamSize)
        yield getPlayer(pos)
    matchMaker.makeMatch(blueTeam.toList, redTeam.toList, meta)

  def getPlayer(pos: Int): Player = listOfPlayers(pos)
