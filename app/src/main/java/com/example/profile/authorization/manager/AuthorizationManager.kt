package com.example.profile.authorization.manager

import android.content.Context
import com.example.profile.R
import com.example.profile.authorization.sp.PreferenceHelper
import java.security.MessageDigest

class AuthorizationManager(context: Context)
{

    private val appContext = context
    private val preferenceHelper = PreferenceHelper(context)


    fun initAdmin()
    {
        val loginRow = appContext.getString(R.string.login)

        if (preferenceHelper.getDate(loginRow)!=null)
            return

        val admin = appContext.getString(R.string.admin)
        val passwordRow = appContext.getString(R.string.password)

        preferenceHelper.saveDate(loginRow,admin)
        preferenceHelper.saveDate(passwordRow,getHashString(admin))
    }

    fun authorization(login:String, password:String):Boolean
    {
        val prefLogin = preferenceHelper.getDate(appContext.getString(R.string.login))

        if(prefLogin != login)
            return false

        val prefPassword = preferenceHelper.getDate(appContext.getString(R.string.password))
        val hashCurPassword = getHashString(password)

        if(prefPassword != hashCurPassword)
            return false

        return true
    }


    private fun getHashString(input: String): String {
        return MessageDigest
            .getInstance(appContext.getString(R.string.SHA256))
            .digest(input.toByteArray())
            .fold("") { str, it -> str + "%02x".format(it) }
    }
}