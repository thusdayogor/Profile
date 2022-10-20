package com.example.profile.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.profile.authorization.manager.AuthorizationManager
import com.example.profile.validation.Validation


class LoginViewModel(application: Application): AndroidViewModel(application)
{
    private val context = application

    private val authorizationManager = AuthorizationManager(context)

    private val validation = Validation()

    fun initAdmin()
    {
        authorizationManager.initAdmin()
    }

    fun authorization(email:String,password:String):Boolean
    {
        return authorizationManager.authorization(email,password)
    }


    fun validDate(email:String, password:String):Boolean
    {
        if (validation.lengthValid(email) && (validation.lengthValid(password)))
        {
            return true
        }

        return false
    }

}