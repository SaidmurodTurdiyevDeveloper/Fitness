package uz.smt.fitness_domen.model

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/27/2023 6:43 PM for Fitnes.
 */
data class ReminderData(
    val id: String,
    val time: TimeData,
    val isRemindActive: Boolean,
    val isRemindOnMonday: Boolean,
    val isRemindOnTuesday: Boolean,
    val isRemindOnWednesday: Boolean,
    val isRemindOnThursday: Boolean,
    val isRemindOnFriday: Boolean,
    val isRemindOnSaturday: Boolean,
    val isRemindOnSunday: Boolean
)
