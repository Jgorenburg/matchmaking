package Base

trait GameConfig:
  type TypeOfPlayer <: Player

  def numPlayers: Int
  def numChamps: Int
  def ListOfChamps: Vector[Champion]
  var ListOfPlayers: Vector[TypeOfPlayer]
  def makeMatch(bluePlayer: Int, redPlayer: Int): Match

  def getPlayer(pos: Int): TypeOfPlayer = ListOfPlayers(
    pos
  )
