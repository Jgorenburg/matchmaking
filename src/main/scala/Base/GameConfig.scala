package Base

class GameConfig(
    val numPlayers: Int,
    val numChamps: Int,
    val playerMaker: PlayerMaker,
    val matchMaker: MatchMaker,
    val gameMaker: GameMaker
):
  val listOfChamps: Array[Champion] =
    gameMaker.makeChamps(numChamps)
  val meta: Meta = gameMaker.makeMeta(listOfChamps)

  var listOfPlayers: Vector[Player] =
    (1 to numPlayers).toVector.map(i => playerMaker.makePlayer(listOfChamps))

  def makeMatch(blueTeam: List[Int], redTeam: List[Int]): Match =
    matchMaker.makeMatch(blueTeam.map(getPlayer), redTeam.map(getPlayer), meta)

  def getPlayer(pos: Int): Player = listOfPlayers(pos)
