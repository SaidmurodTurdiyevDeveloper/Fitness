package uz.smt.common_utils.navigator

import android.app.Activity
import uz.smt.common_utils.activity.Activities

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/7/2023 9:34 AM for Fitnes.
 */
interface Navigator {
    fun navigate(activity: Activity)

    interface Provider{
        fun getActivities(activities: Activities): Navigator
    }
}