package Base

class Meta(listOfChamps: Array[Champion]):
  def this(definedMeta: Map[Champion, Int]) =
    this(Array[Champion]())
    champStrength = definedMeta

  var champStrength: Map[Champion, Int] =
    listOfChamps.zipWithIndex.toMap
