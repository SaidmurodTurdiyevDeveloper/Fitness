package uz.smt.fitness_domen.model

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/29/2023 9:33 PM for Fitnes.
 */
data class ReportWeek(
    val monday: Week = Week(
        name = "Monday",
        shortName = "Mon",
        firstCharacter = 'M',
        day = "1",
        reportType = Report.Current
    ),
    val tuesday: Week = Week(
        name = "Tuesday",
        shortName = "Tue",
        firstCharacter = 'T',
        day = "2"
    ),
    val wednesday: Week = Week(
        name = "Wednesday",
        shortName = "Wed",
        firstCharacter = 'W',
        day = "3"
    ),
    val thursday: Week = Week(
        name = "Thursday",
        shortName = "Thu",
        firstCharacter = 'T',
        day = "4"
    ),
    val friday: Week = Week(
        name = "Friday",
        shortName = "Fri",
        firstCharacter = 'F',
        day = "5"
    ),
    val saturday: Week = Week(
        name = "Saturday",
        shortName = "Sat",
        firstCharacter = 'S',
        day = "6"
    ),
    val sunday: Week = Week(
        name = "Sunday",
        shortName = "Sun",
        firstCharacter = 'S',
        day = "0",
        reportType = Report.Complete
    )
)


enum class Report {
    Complete, Current, New
}
