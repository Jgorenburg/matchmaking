import scala.util.{Using, Try}
import java.io.{BufferedWriter, File, FileWriter}
import Base.{
  GameConfig,
  MatchMaker,
  Player,
  Playstyle,
  PureSkillWinner,
  Tournament,
  TournamentHistory,
  RoundHistory,
  MatchHistory,
  Meta
}
//TODO: Add bans + tournament type to config
class Printer {
  def writeToFile(tourny: Tournament, filename: String): Unit =
    Using(BufferedWriter(FileWriter(File(filename), false))) { bufferedWriter =>
      writeConfig(
        bufferedWriter,
        "Simple",
        tourny.history.getNumRounds,
        tourny.config
      )
      writePlayers(
        bufferedWriter,
        tourny.config.listOfPlayers,
        tourny.config.meta
      )
      writeTournament(bufferedWriter, tourny.history)
    }

  def writeConfig(
      writer: BufferedWriter,
      tournamentType: String,
      numRounds: Int,
      config: GameConfig
  ): Unit =
    val pureSkill = config.matchMaker.pureSkill match
      case true  => "Y"
      case false => "N"
    val pokemonMeta = config.gameMaker.playstyles match
      case Array(Playstyle.Default) => "N"
      case _                        => "Y"

    var content =
      tournamentType + "\t" + numRounds + "\t" + config.listOfPlayers.length
        + "\t" + config.listOfChamps.length + "\t" + config.teamSize + "\t" + pureSkill + "\t" + pokemonMeta
    writer.write(content)

  def writeTournament(
      writer: BufferedWriter,
      thistory: TournamentHistory
  ): Unit =
    def writeRound(rhistory: RoundHistory): Unit =
      var line = ""
      def addMatch(mhistory: MatchHistory): String =
        val blueSide =
          mhistory.blueSide._1.mkString(", ") + "\t" + mhistory.blueSide._2
            .mkString(", ")
        val redSide =
          mhistory.redSide._1.mkString(", ") + "\t" + mhistory.redSide._2
            .mkString(", ")
        val bans =
          if mhistory.bans.isEmpty then ""
          else mhistory.bans.mkString(", ") + "\t"
        mhistory.winner
          .toString() + "\t" + blueSide + "\t" + redSide + "\t" + bans
      for matchHistory <- rhistory.matches do line += addMatch(matchHistory)
      writeLine(writer, line)
    for roundHistory <- thistory.rounds do writeRound(roundHistory)

  def writePlayers(
      writer: BufferedWriter,
      players: Vector[Player],
      meta: Meta
  ): Unit =
    def writePlayer(player: Player): Unit =
      var line = player.toString()
      val memlist =
        player.memory.toSeq.sortBy(mem => meta.champStrength(mem._1))
      for champ <- memlist do
        val champion = champ._1
        val record = champ._2
        line += "\t" + champion + "\t" + champion.playstyle + "\t" + meta
          .champStrength(champion) +
          "\t" + record.wins.toInt + "\t" + record.games.toInt
      writeLine(writer, line)
    for player <- players do writePlayer(player)

  def writeLine(writer: BufferedWriter, content: String): Unit =
    writer.newLine()
    writer.write(content)

}
