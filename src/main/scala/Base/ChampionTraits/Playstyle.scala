package Base.ChampionTraits
enum ChampType {
  case Assassin
  case Fighter
  case Paladin
  case Sniper
  case Wizard
}

enum Relationship {
  case Disadvantage
  case Advantage
  case Neutral
}

trait Playstyle(playstyle: ChampType) {

  // Assassin > Wizard,Sniper
  // Wizard > Sniper,Fighter
  // Sniper > Fighter,Paladin
  // Fighter > Assassin,Paladin
  // Paladin > Wizard,Assassin
  def GetRelationship(oppPlaystyle: ChampType): Relationship =
    if (playstyle == oppPlaystyle) return Relationship.Neutral
    playstyle match
      case ChampType.Assassin =>
        if (
          oppPlaystyle == ChampType.Wizard || oppPlaystyle == ChampType.Sniper
        )
          return Relationship.Advantage
        return Relationship.Disadvantage
      case ChampType.Fighter =>
        if (
          oppPlaystyle == ChampType.Assassin || oppPlaystyle == ChampType.Paladin
        )
          return Relationship.Advantage
        return Relationship.Disadvantage
      case ChampType.Paladin =>
        if (
          oppPlaystyle == ChampType.Assassin || oppPlaystyle == ChampType.Wizard
        )
          return Relationship.Advantage
        return Relationship.Disadvantage
      case ChampType.Sniper =>
        if (
          oppPlaystyle == ChampType.Fighter || oppPlaystyle == ChampType.Paladin
        )
          return Relationship.Advantage
        return Relationship.Disadvantage
      case _ => // Wizard
        if (
          oppPlaystyle == ChampType.Sniper || oppPlaystyle == ChampType.Fighter
        )
          return Relationship.Advantage
        return Relationship.Disadvantage

}
