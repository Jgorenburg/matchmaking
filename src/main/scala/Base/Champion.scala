package Base

sealed trait Named { self: Champion =>
  override def toString() = self.name
}

case class Champion(name: String) extends Named
