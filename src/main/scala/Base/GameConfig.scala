package Base

trait GameConfig(numPlayers: Int, numChamps: Int):
  type TypeOfPlayer <: Player

  val ListOfChamps: Vector[Champion]
  var ListOfPlayers: Vector[TypeOfPlayer]
  def makeMatch(bluePlayer: Int, redPlayer: Int): Match

  def getPlayer(pos: Int): TypeOfPlayer = ListOfPlayers(
    pos
  )
