package Base

class Record:
  var wins = 0.0f
  var games = 0.0f
  def updateRecord(won: Boolean) =
    games += 1
    if (won) wins += 1
  def getWinPercent: Float =
    if (games == 0)
      return 0.5
    return wins / games
