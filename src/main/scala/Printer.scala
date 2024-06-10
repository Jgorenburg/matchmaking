import scala.util.{Using, Try}
import java.io.{BufferedWriter, File, FileWriter}
import Base.{
  GameConfig,
  Player,
  Tournament,
  TournamentHistory,
  RoundHistory,
  MatchHistory
}

class Printer {
  def writeToFile(tourny: Tournament, filename: String): Unit =
    Using(BufferedWriter(FileWriter(File(filename), false))) { bufferedWriter =>
      writeConfig(
        bufferedWriter,
        "Simple",
        tourny.history.getNumRounds(),
        tourny.config
      )
      writeTournament(bufferedWriter, tourny.history)
      writePlayers(bufferedWriter, tourny.config.ListOfPlayers)
    }

  def writeConfig(
      writer: BufferedWriter,
      tournamentType: String,
      numRounds: Int,
      config: GameConfig
  ): Unit =
    var content =
      tournamentType + "\t" + numRounds + "\t" + config.ListOfPlayers.length + "\t" + config.ListOfChamps.length + "\t1\tY\tN"
    writer.write(content)

  def writeTournament(
      writer: BufferedWriter,
      thistory: TournamentHistory
  ): Unit =
    def writeRound(rhistory: RoundHistory): Unit =
      var line = ""
      def addMatch(mhistory: MatchHistory): String =
        val blueSide =
          mhistory.blueSide._1.toString() + "\t" + mhistory.blueSide._2
            .toString()
        val redSide =
          mhistory.redSide._1.toString() + "\t" + mhistory.redSide._2.toString()
        mhistory.winner.toString() + "\t" + blueSide + "\t" + redSide + "\t"
      for matchHistory <- rhistory.matches do line += addMatch(matchHistory)
      writeLine(writer, line)
    for roundHistory <- thistory.rounds do writeRound(roundHistory)
  def writePlayers(writer: BufferedWriter, players: Vector[Player]): Unit =
    def writePlayer(player: Player): Unit =
      var line = player.toString()
      for champ <- player.memory do
        val record = champ._2
        line += "\t" + champ._1.toString() +
          "\t" + record.wins + "\t" + record.games
      writeLine(writer, line)
    for player <- players do writePlayer(player)

  def writeLine(writer: BufferedWriter, content: String): Unit =
    writer.newLine()
    writer.write(content)

}
