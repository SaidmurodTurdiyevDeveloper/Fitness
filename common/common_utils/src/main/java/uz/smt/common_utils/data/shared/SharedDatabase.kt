package uz.smt.common_utils.data.shared

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/13/2023 4:11 PM for Fitnes.
 */
@Singleton
class SharedDatabase @Inject constructor(
    @ApplicationContext
    private var context: Context
) {
    private var data =
        context.getSharedPreferences("my_shared_DB_my_fitness", Context.MODE_PRIVATE)
    private var editor = data.edit()


    fun setBooleanData(myStringObjectData: String, data: Boolean) {
        editor.putBoolean(myStringObjectData, data)
        editor.apply()
    }

    fun getBooleanData(myStringObjectData: String): Boolean =
        this.data.getBoolean(myStringObjectData, false)

    fun setStringData(myStringObjectData: String, data: String) {
        editor.putString(myStringObjectData, data)
        editor.apply()
    }

    var firstEnter: Boolean
        get() = getBooleanData("first_enter_boolean")
        set(value) = setBooleanData("first_enter_boolean", value)
    var appLanguage: Int
        get() = getIntData("app_language_integer")
        set(value) = setIntData("app_language_integer", value)

    fun getStringData(myStringObjectData: String): String? =
        this.data.getString(myStringObjectData, "")

    fun setLongData(myLongObjectData: String, data: Long) {
        editor.putLong(myLongObjectData, data)
        editor.apply()
    }

    fun getLongData(myLongObjectData: String): Long = this.data.getLong(myLongObjectData, 0L)

    fun setIntData(myStringObjectData: String, data: Int) {
        editor.putInt(myStringObjectData, data)
        editor.apply()
    }

    fun getIntData(myStringObjectData: String): Int = this.data.getInt(myStringObjectData, -1)
}