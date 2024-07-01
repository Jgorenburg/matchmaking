package Base

object Playstyle extends Enumeration:
  type Playstyle = Value
  val Default = Value("Default")
  def makeStyle = (s: String) => Value(s)
  def makeStyles = (as: Array[String]) => as.map(makeStyle)

abstract class Champion:
  val name: String
  val playstyle: Playstyle.Value
  override def toString() = name

case class Default(name: String) extends Champion:
  val playstyle = Playstyle.Default
case class Specialized(name: String, playstyle: Playstyle.Value)
    extends Champion
