package com.example.profile.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.profile.db.dao.ProfileDao
import com.example.profile.db.structure.ProfileModel


@Database(entities = [ProfileModel::class], version = 1)
abstract class ProfileDataBase :RoomDatabase() {

    abstract fun getProfileDao(): ProfileDao


    companion object {

        private var database: ProfileDataBase? = null


        @Synchronized
        fun getInstance(context: Context): ProfileDataBase
        {
            return if(database == null)
            {
                database = Room.databaseBuilder(context, ProfileDataBase::class.java,"db").build()
                database as ProfileDataBase
            }
            else
            {
                database as ProfileDataBase
            }
        }
    }



}