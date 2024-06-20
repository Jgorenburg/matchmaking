package Base

final case class MatchHistory(
    blueSide: (Player, Champion),
    redSide: (Player, Champion),
    winner: Side.Value
)
