package MetaTypes

import Base.{Champion, Meta, MetaMaker, Playstyle}

class DefinedMeta(
    relationshipChart: Map[Playstyle.Value, Map[Playstyle.Value, Int]]
) extends MetaMaker {
  def makeMeta(seed: Map[Champion, Int]) = new Meta(seed, relationshipChart)
}
