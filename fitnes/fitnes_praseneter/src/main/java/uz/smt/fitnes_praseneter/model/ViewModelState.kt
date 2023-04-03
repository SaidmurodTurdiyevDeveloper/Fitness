package uz.smt.fitnes_praseneter.model

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/7/2023 9:25 PM for Fitnes.
 */
data class ViewModelState<T>(
    val loading: Boolean = false,
    val error: String? = null,
    val success: T? = null
) {
   suspend fun successCopy(
        block:suspend (T?)-> T?
    ): ViewModelState<T> {
       val newData=block.invoke(success)
        return copy(
            success = newData
        )
    }
}