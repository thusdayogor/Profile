package com.example.profile.db.repository

import androidx.lifecycle.LiveData
import com.example.profile.db.structure.ProfileModel
import com.example.profile.db.dao.ProfileDao

class ProfileRealization(private val profileDao: ProfileDao): ProfileRepository {


    override val allProfile: LiveData<List<ProfileModel>>
        get() = profileDao.getAllUsers()

    override suspend fun insertProfile(profileModel: ProfileModel, onSuccess:()->Unit) {
        profileDao.insert(profileModel)
    }

    override suspend fun deleteProfile(profileModel: ProfileModel, onSuccess:()->Unit) {
        profileDao.delete(profileModel)
    }

    override suspend fun updateProfile(profileModel: ProfileModel, onSuccess:()->Unit) {
        profileDao.update(profileModel)
    }
}