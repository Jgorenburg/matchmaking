package Base

class Record:
  var wins = 0
  var games = 0
  def updateRecord(won: Boolean) =
    games += 1
    if (won) wins += 1
  def getWinPercent: Float =
    if (games == 0)
      return 0.1
    return wins / games
