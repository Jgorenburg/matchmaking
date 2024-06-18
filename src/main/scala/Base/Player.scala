package Base

trait Player extends MemoryHandling, WinRecord:

  def chooseChampion(oppChoice: Option[Array[Champion]]): Champion
  def chooseBlueChampion(): Champion
  def chooseRedChampion(champ: Champion): Champion

  override def toString(): String = super.toString().split("@")(1)

trait PlayerMaker:
  def makePlayer(champions: Array[Champion]): Player
