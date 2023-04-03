package uz.smt.fitness_domen.model

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/27/2023 6:46 PM for Fitnes.
 */
data class Week(
    val name: String,
    val shortName: String,
    val firstCharacter: Char,
    val reportType: Report = Report.New,
    val day: String
)

