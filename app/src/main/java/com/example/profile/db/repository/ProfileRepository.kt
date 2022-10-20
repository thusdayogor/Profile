package com.example.profile.db.repository

import androidx.lifecycle.LiveData
import com.example.profile.db.structure.ProfileModel

interface ProfileRepository {

    val allProfile:LiveData<List<ProfileModel>>
    suspend fun insertProfile(profileModel: ProfileModel, onSuccess:() -> Unit)
    suspend fun deleteProfile(profileModel: ProfileModel, onSuccess:() -> Unit)
    suspend fun updateProfile(profileModel: ProfileModel, onSuccess:() -> Unit)
}