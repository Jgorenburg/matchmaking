import Base.{
  Champion,
  GameConfig,
  GameMaker,
  MatchMaker,
  PlayerMaker,
  StrengthFunctions,
  Tournament
}
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
          case _ => println("Invalid selection. Must select between 1 and 8")
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
      "9. Custom",
      "10. Exit"
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
          val tournySize = customTournamentSize()
          val champpool = customChamppoolSize()
          val teamsize = customTeamSize()
          val playerType = customPlayerMaker()
          val matchType = customMatchMaker()
          val gameType = customGameMaker()
          val strengthFunct = customStrengthFunct(tournySize, champpool)
          customGame(
            tournySize,
            champpool,
            teamsize,
            playerType,
            matchType,
            gameType,
            strengthFunct,
            "results/custom.txt"
          )
        case 10 =>
          println("Exiting program. Goodbye!")
          running = false
        case _ => println("Invalid selection. Please try again.")
      }
    }

  def customGame(
      numPlayers: Int,
      numChamps: Int,
      teamSize: Int,
      playerType: PlayerMaker,
      matchType: MatchMaker,
      gameType: GameMaker,
      strengthFunct: Array[Champion] => Map[Champion, Int],
      outputFile: String
  ): Unit = {
    val config =
      new GameConfig(
        numPlayers,
        numChamps,
        teamSize,
        playerType,
        matchType,
        gameType,
        Some(strengthFunct)
      )
    runTournament(
      config,
      outputFile
    )
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

  def customTournamentSize(): Int = {
    println("Tournament Size:")
    getUserChoice(1000)
  }

  def customChamppoolSize(): Int = {
    println("Number of Champions:")
    getUserChoice(1000)
  }

  def customTeamSize(): Int = {
    println("Team Size:")
    getUserChoice(5)
  }

  def customPlayerMaker(): PlayerMaker = {
    val options = List(
      "1. Simple Player",
      "2. Matchup Aware Player"
    )
    displayMenu(options)
    getUserChoice(options.length) match
      case 1 => SimplePlayerMaker
      case 2 => MatchupAwarePlayerMaker
  }

  def customMatchMaker(): MatchMaker = {
    val options = List(
      "1. Simple Match",
      "2. Random Match",
      "3. Simple Match with Bans",
      "4. Random Match with Bans"
    )
    displayMenu(options)
    getUserChoice(options.length) match
      case 1 => SimpleMatchMaker
      case 2 => RandomMatchMaker
      case 3 => SimpleWithBansMatchmaker
      case 4 => RandomWithBansMatchmaker
  }

  def customGameMaker(): GameMaker = {
    val options = List(
      "1. Basic Game",
      "2. MTG Game",
      "3. RPS Game"
    )
    displayMenu(options)
    getUserChoice(options.length) match
      case 1 => BasicGame
      case 2 => MTG
      case 3 => RockPaperScissors
  }

  def customStrengthFunct(
      numPlayers: Int,
      numChamps: Int
  ): Array[Champion] => Map[Champion, Int] = {
    val options = List(
      "1. All Equal",
      "2. Zip With Index",
      "3. Mod X",
      "4. Custom Vals"
    )
    displayMenu(options)
    getUserChoice(options.length) match
      case 1 => StrengthFunctions.allOne
      case 2 => StrengthFunctions.withIndex
      case 3 => StrengthFunctions.modX(getUserChoice(numPlayers))
      case 4 => StrengthFunctions.custom(getUserList(numChamps))
  }

  def displayMenu(options: List[String]): Unit = {
    println("\n===== MENU =====")
    options.foreach(println)
    println("===============")
    print("Enter your choice (1-" + options.size + "): ")
  }

  def getUserList(numElems: Int): List[Int] = {
    println(s"\nPlease enter numbers between 1 and 1000.")
    List.fill(numElems)(getUserChoice(1000))
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
