package base

trait GameConfig(numPlayers: Int, numChamps: Int):
  val ListOfChamps: Vector[Champion]
  var ListOfPlayers: Vector[Player]
  def makeMatch(bluePlayer: Int, redPlayer: Int): Match

  def getPlayer(pos: Int): Player = ListOfPlayers(pos)
