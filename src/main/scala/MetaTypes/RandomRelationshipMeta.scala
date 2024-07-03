package MetaTypes

import Base.{Champion, Meta, MetaMaker, Playstyle}
import scala.io.Source
import java.io.File
import scala.util.Random
import scala.collection.mutable.Map as MMap

class RandomRelationshipMeta(playstyles: Array[Playstyle.Value])
    extends MetaMaker:
  def makeMeta(seed: Map[Champion, Int]) =
    val relationships =
      var rels = MMap[Playstyle.Value, Map[Playstyle.Value, Int]]()
      for (i <- 0 to playstyles.size by -1; j <- 0 to i by -1) {
        val firstStyle = playstyles(i)
        val secondStyle = playstyles(j)
        if (i == j) {
          rels += firstStyle -> Map(secondStyle -> 0)
        } else {
          val relationship = Random.between(-2, 3)
          // one will be 0, other will be 0-2
          rels(firstStyle) += secondStyle -> List(relationship, 0).max
          rels(secondStyle) += firstStyle -> List(-relationship, 0).max
        }
      }
      Map() ++ rels.toMap
    new Meta(seed, relationships)

//   // Default behavior is random relationships between -2 and 2
//   val relationships: Map[(Class, Class), Int] =
//     var rels = Map[(Class, Class), Int]()
//     for (i <- 0 until listOfClasses.size; j <- 0 to i) {
//       val firstClass = listOfClasses(i)
//       val secondClass = listOfClasses(j)
//       if (firstClass == secondClass) {
//         rels += (firstClass, secondClass) -> 0
//       } else {
//         val relationship = Random.between(-2, 3)
//         rels += (firstClass, secondClass) -> relationship
//         rels += (secondClass, firstClass) -> -relationship
//       }
//     }
//     rels

//   def getRelationship(firstClass: Class, secondClass: Class): Int =
//     relationships(
//       (firstClass, secondClass)
//     )
// }
