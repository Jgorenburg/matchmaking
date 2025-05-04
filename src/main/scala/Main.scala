import Base.{GameConfig, Tournament}
import PlayerTypes.{SimplePlayerMaker, MatchupAwarePlayerMaker}
import MatchTypes.{RandomMatchMaker, RandomWithBansMatchmaker, SimpleMatchMaker}
import GameTypes.{BasicGame, MTG, RockPaperScissors}
import MatchTypes.SimpleWithBansMatchmaker

import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit =
    print(args)
    if (args.length > 0) {
      try
        args(0).toInt match
          case 1 => simpleGame()
          case 2 => randomGame()
          case 3 => rpsGame()
          case 4 => mtgGame()
          case 5 => bansGame()
          case 6 => teamGame()
          case 7 => manyGame()
          case 8 =>
            simpleGame()
            randomGame()
            rpsGame()
            mtgGame()
            bansGame()
            teamGame()
            manyGame()
          case _ => println("Invalid selection. Please try again.")
      catch
        case _: java.lang.NumberFormatException =>
          println("Invalid arg: " + args(0) + " is not a number")
      return
    }

    val options = List(
      "1. Simple",
      "2. Random",
      "3. RPS",
      "4. MTG",
      "5. Simple with Bans",
      "6. Teams",
      "7. Many Players",
      "8. All",
      "9. Exit"
    )

    var running: Boolean = true

    while (running) {

      displayMenu(options)
      val selection = getUserChoice(options.size)

      selection match {
        case 1 => simpleGame()
        case 2 => randomGame()
        case 3 => rpsGame()
        case 4 => mtgGame()
        case 5 => bansGame()
        case 6 => teamGame()
        case 7 => manyGame()
        case 8 =>
          simpleGame()
          randomGame()
          rpsGame()
          mtgGame()
          bansGame()
          teamGame()
          manyGame()
        case 9 =>
          println("Exiting program. Goodbye!")
          running = false
        case _ => println("Invalid selection. Please try again.")
      }
    }

  def simpleGame(): Unit = {
    val config =
      new GameConfig(10, 10, 1, SimplePlayerMaker, SimpleMatchMaker, BasicGame)
    runTournament(
      config,
      "results/simple.txt"
    )
  }

  def randomGame(): Unit = {
    runTournament(
      new GameConfig(10, 10, 1, SimplePlayerMaker, RandomMatchMaker, BasicGame),
      "results/random.txt"
    )
  }

  def rpsGame(): Unit = {
    runTournament(
      new GameConfig(
        2,
        3,
        1,
        MatchupAwarePlayerMaker,
        RandomMatchMaker,
        RockPaperScissors
      ),
      "results/rps.txt"
    )
  }

  def mtgGame(): Unit = {
    runTournament(
      new GameConfig(
        4,
        5,
        1,
        MatchupAwarePlayerMaker,
        RandomMatchMaker,
        MTG
      ),
      "results/mtg.txt"
    )
  }

  def bansGame(): Unit = {
    runTournament(
      new GameConfig(
        4,
        4,
        1,
        SimplePlayerMaker,
        SimpleWithBansMatchmaker,
        BasicGame
      ),
      "results/bans.txt"
    )
  }

  def teamGame(): Unit = {
    runTournament(
      new GameConfig(
        10,
        10,
        2,
        SimplePlayerMaker,
        SimpleWithBansMatchmaker,
        BasicGame
      ),
      "results/teams.txt"
    )
  }

  def manyGame(): Unit = {
    runTournament(
      new GameConfig(
        1000,
        10,
        1,
        SimplePlayerMaker,
        SimpleMatchMaker,
        BasicGame
      ),
      "results/many.txt"
    )
  }

  def runTournament(config: GameConfig, outputFile: String): Unit = {
    val tourny = Tournament(config, 1000)
    val printer = new Printer
    printer.writeToFile(tourny, outputFile)
  }

  def displayMenu(options: List[String]): Unit = {
    println("\n===== MENU =====")
    options.foreach(println)
    println("===============")
    print("Enter your choice (1-" + options.size + "): ")
  }

  def getUserChoice(maxOption: Int): Int = {
    try {
      val input = StdIn.readLine().trim
      val choice = input.toInt

      if (choice < 1 || choice > maxOption) {
        println(s"Please enter a number between 1 and $maxOption.")
        getUserChoice(maxOption) // Recursively ask again
      } else {
        choice
      }
    } catch {
      case _: NumberFormatException =>
        println("Please enter a valid number.")
        getUserChoice(maxOption) // Recursively ask again
    }
  }
}
