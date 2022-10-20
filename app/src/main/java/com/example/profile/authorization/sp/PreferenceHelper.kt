package com.example.profile.authorization.sp

import android.content.Context
import android.content.SharedPreferences
import com.example.profile.R


class PreferenceHelper(context:Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(context.getString(R.string.tableName),
        Context.MODE_PRIVATE)
    private val editor = pref.edit()

    fun saveDate(row: String, value: String) {
        editor.putString(row, value)
        editor.apply()
    }

    fun getDate(row: String): String? {
        return pref.getString(row, null)
    }
}
