package MetaTypes

import Base.{Champion, Default, Meta, MetaMaker, Playstyle}

object BasicMetaMaker extends MetaMaker:
  def makeMeta(
      seed: Map[Champion, Int],
      playstyles: Array[Playstyle.Value] = Array()
  ) =
    new Meta(seed, Map(Playstyle.Default -> Map(Playstyle.Default -> 0)))
