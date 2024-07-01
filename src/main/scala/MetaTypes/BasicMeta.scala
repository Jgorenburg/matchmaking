package MetaTypes

import Base.{Champion, Default, Meta, MetaMaker, Playstyle}

class BasicMetaMaker extends MetaMaker:
  def makeMeta(
      seed: Map[Champion, Int]
  ) =
    new Meta(seed, Map(Playstyle.Default -> Map(Playstyle.Default -> 0)))
