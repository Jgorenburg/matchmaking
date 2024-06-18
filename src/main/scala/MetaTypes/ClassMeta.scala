package MetaTypes

import Base.Meta
import Base.Champion
import scala.io.Source
import java.io.File
import scala.util.Random

case class Class(name: String)

class ClassMeta(numClasses: Int, listOfChamps: Array[Champion])
    extends Meta(listOfChamps) {
  val listOfClasses: Array[Class] =
    val listOfClassNames = {
      val src = Source.fromFile(
        new File(getClass.getClassLoader.getResource("classes.txt").getPath)
      )
      val lines = src.getLines.take(numClasses).toArray
      src.close
      lines
    }
    listOfClassNames.map(new Class(_))

  // Default behavior is random relationships between -2 and 2
  val relationships: Map[(Class, Class), Int] =
    var rels = Map[(Class, Class), Int]()
    for (i <- 0 until listOfClasses.size; j <- 0 to i) {
      val firstClass = listOfClasses(i)
      val secondClass = listOfClasses(j)
      if (firstClass == secondClass) {
        rels += (firstClass, secondClass) -> 0
      } else {
        val relationship = Random.between(-2, 3)
        rels += (firstClass, secondClass) -> relationship
        rels += (secondClass, firstClass) -> -relationship
      }
    }
    rels

  def getRelationship(firstClass: Class, secondClass: Class): Int =
    relationships(
      (firstClass, secondClass)
    )
}
