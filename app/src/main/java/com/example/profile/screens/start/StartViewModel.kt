package com.example.profile.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.profile.Const.REP
import com.example.profile.db.ProfileDataBase
import com.example.profile.db.repository.ProfileRealization
import com.example.profile.db.structure.ProfileModel


class StartViewModel(application: Application):AndroidViewModel(application)
{
    private val context = application

    fun initDB()
    {
        val daoProfile = ProfileDataBase.getInstance(context).getProfileDao()
        REP = ProfileRealization(daoProfile)
    }

    fun getAllProfiles():LiveData<List<ProfileModel>>
    {
        return REP.allProfile
    }

}