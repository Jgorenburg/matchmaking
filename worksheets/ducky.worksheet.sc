import scala.compiletime.ops.boolean
import scala.collection.immutable.HashSet
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.ArraySeq
trait Showable:
  def show: String

trait HtmlShow(x: String) extends Showable:
  def showHtml = "<p>" + show + x + "</p>"

class Document(text: String, x: String) extends HtmlShow(x: String):
  def show = text

val s1: Document = Document("salmon", "trout")
s1.show
s1.showHtml
val s2: HtmlShow = s1
val s3: Showable = s1
s2.showHtml

class Tester(name: String):
  def sayHello() = println(name)

val test = Tester("salmon")
val cod = Tester("cod")
val listOfTesters = List(test, cod, Tester("trout"))
def compareTesters(t1: Tester, listOT: List[Tester]) =
  t1 == listOT(1)
compareTesters(cod, listOfTesters)

case class Champion(name: String, skill: Int)

val c1 = Champion("cod", 1)
c1.skill

val h1 = HashMap[String, Int]("cod" -> 3)
h1 += ("salmon" -> 2)

class t1(value: Int):
  var level: Int = 1
  def setVal(newVal: Int) = level = newVal

class holder(newVal: Int, t: t1):
  t.setVal(newVal)
  println(t.level)

val tt1 = t1(2)
tt1.level
val hold1 = holder(2, tt1)
tt1.level
val ingredient = ("Sugar", 25)

class Record():
  var wins = 0
  var games = 0
  def updateRecord(won: Boolean) =
    games += 1
    if (won) wins += 1

val r1 = Record()
r1.wins
r1.updateRecord(true)
r1.wins
r1.games
r1.wins
r1.updateRecord(false)
r1.games
r1.wins

val vec: Vector[Int] = Vector(1, 2, 3, 4, 6, 7)
val hm: HashMap[Int, String] =
  vec.foldRight(new HashMap())((x, y) => y += (x -> "a"))

val vhm = hm.contains(5)

def lambdaTests(): Int =
  def inner(x: Int): Int = return x + 1
  return inner(2)

val lt = lambdaTests()

def maxer(x: Int, y: Int): Int =
  if (x > y)
    return x
  else
    return y

val z = maxer
val w = z(2, 3)

val rhm: String =
  hm.foldRight("")((x, y) => x(1))

val memory: HashMap[Champion, Record] =
  new HashMap()

def chooseChampion(oppChoice: Option[Champion]): Champion =
  def champFilter(curChamp: Champion): Boolean =
    oppChoice match
      case Some(champ) =>
        return memory.contains(champ) && champ == curChamp
      case _ => return true
  val b: Champion =
    memory.foldRight(new Champion("a", 1))((newChoice, curChamp) =>
      newChoice(0)
    )
  return b
