package com.example.profile.db.structure

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "profile_table")
class ProfileModel (

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,

    @ColumnInfo
    var surname:String = "",

    @ColumnInfo
    var name:String = "",

    @ColumnInfo
    var patronymic:String = "",

    @ColumnInfo
    var date:String = "",

    @ColumnInfo
    var email:String = "",

    @ColumnInfo
    var phone:String = "",

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var photo: ByteArray? = null
) : Serializable