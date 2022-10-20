package com.example.profile.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.profile.db.structure.ProfileModel

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(profileModel: ProfileModel)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(profileModel: ProfileModel)

    @Delete
    suspend fun delete(profileModel: ProfileModel)

    @Query("SELECT * from profile_table")
    fun getAllUsers():LiveData<List<ProfileModel>>
}