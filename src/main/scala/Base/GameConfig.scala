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

  def makeMatch(bluePlayer: Int, redPlayer: Int): Match =
    matchMaker.makeMatch(getPlayer(bluePlayer), getPlayer(redPlayer), meta)

  def getPlayer(pos: Int): Player = listOfPlayers(pos)
