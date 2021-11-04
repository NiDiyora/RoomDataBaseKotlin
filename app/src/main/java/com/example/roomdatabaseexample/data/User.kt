package com.example.roomdatabaseexample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val image: ByteArray?,
    val firstname:String,
    val lastname:String,
    val age:String,
    val mobileNo:String,

    )
