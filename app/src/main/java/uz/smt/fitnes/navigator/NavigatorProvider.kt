package uz.smt.fitnes.navigator

import uz.smt.common_utils.activity.Activities
import uz.smt.common_utils.navigator.Navigator
import uz.smt.fitness_presenter.GoToFitnessActivity

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/7/2023 4:06 PM for Fitnes.
 */
class NavigatorProvider : Navigator.Provider {

    override fun getActivities(activities: Activities): Navigator {
        return when (activities) {
            Activities.FitnessActivity -> {
                GoToFitnessActivity
            }
        }
    }
}