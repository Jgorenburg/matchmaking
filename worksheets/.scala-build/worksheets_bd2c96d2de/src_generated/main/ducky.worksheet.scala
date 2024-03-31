

final class ducky$u002Eworksheet$_ {
def args = ducky$u002Eworksheet_sc.args$
def scriptPath = """ducky.worksheet.sc"""
/*<script>*/
import scala.io.Source
val filename = "Fishies.txt"
val file = scala.io.Source.fromFile("src/main/resources/dummy")

val url = getClass.getResource("<dummy>") 
for i <- 1 to 6 do println(i)

val file2 = getClass.getResource("fishies.txt")
val readmeText : Iterator[String] = Source.fromResource("fishies.txt").getLines

/*</script>*/ /*<generated>*//*</generated>*/
}

object ducky$u002Eworksheet_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new ducky$u002Eworksheet$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export ducky$u002Eworksheet_sc.script as `ducky.worksheet`

