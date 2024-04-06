class Round(roundNum: Int, config: GameConfig) {
  val polePlayer = config.ListOfPlayers.take(1)
  val rest: Vector[Player] = config.ListOfPlayers.drop(1)
  val history = RoundHistory()

}
