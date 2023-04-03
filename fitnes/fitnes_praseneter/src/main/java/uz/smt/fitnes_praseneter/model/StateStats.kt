package uz.smt.fitnes_praseneter.model

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/8/2023 4:00 PM for Fitnes.
 */
data class StateStats(
    val tabList:List<String>,
    val currentTab:Int,
    val currentData:List<DataStats>,

)
