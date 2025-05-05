package Base

object StrengthFunctions:
  val allOne: Array[Champion] => Map[Champion, Int] = champs =>
    champs.map((_, 1)).toMap

  val withIndex: Array[Champion] => Map[Champion, Int] =
    champs => champs.zipWithIndex.toMap

  def modX(x: Int): Array[Champion] => Map[Champion, Int] =
    ((champs) => champs.zipWithIndex.toMap.map((c, i) => c -> i % x))

  def custom(customVals: List[Int]): Array[Champion] => Map[Champion, Int] =
    ((champs) =>
      var strengths = customVals
      if (customVals.length < champs.length) {
        strengths =
          customVals ::: List.fill(champs.length - customVals.length)(1)
      }
      champs.zip(strengths).toMap
    )
