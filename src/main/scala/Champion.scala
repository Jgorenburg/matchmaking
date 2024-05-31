package base

sealed trait Named { self: Champion =>
  override def toString() = self.name
}

case class Champion(name: String, skill: Int = 0) extends Named
