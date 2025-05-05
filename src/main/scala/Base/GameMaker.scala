package Base

trait GameMaker:
  def playstyles: Array[Playstyle.Value]
  def makeChamps(
      numChamps: Int
  ): Array[Champion]
  def metaMaker: MetaMaker
  def makeMeta(
      listOfChamps: Array[Champion]
  ): Meta
  def makeMeta(
      listOfChamps: Array[Champion],
      stregnthFunct: Array[Champion] => Map[Champion, Int]
  ): Meta =
    metaMaker.makeMeta(
      listOfChamps,
      stregnthFunct
    )
