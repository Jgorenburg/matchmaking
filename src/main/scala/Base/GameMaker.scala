package Base

trait GameMaker:
  def playstyles: Array[Playstyle.Value]
  def makeChamps(
      numChamps: Int
  ): Array[Champion]
  def metaMaker: MetaMaker
  def makeMeta(
      listOfChamps: Array[Champion]
  ) =
    metaMaker.makeMeta(listOfChamps, playstyles)
