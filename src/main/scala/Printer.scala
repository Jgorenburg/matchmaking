import scala.util.{Using, Try}
import java.io.{BufferedWriter, File, FileWriter}
import Base.*

class Printer {
  // def printTournament(tourny: Tournament): Unit =

  def writeFile(filename: String, content: String): Try[Unit] =
    Using(BufferedWriter(FileWriter(File(filename), true))) { bufferedWriter =>
      bufferedWriter.write(content)
    }

  def writeConfig(
      writer: BufferedWriter,
      tournamentType: String,
      numRounds: Int,
      config: GameConfig
  ): Unit =
    var content =
      tournamentType + "\t" + numRounds + "\t" + config.numPlayers + "\t"

  def addPiece(piece: String, line: String): String =
    line + "\t" + piece

  def writeLine(writer: BufferedWriter, content: String): Unit =
    writer.newLine()
    writer.write(content)

}
