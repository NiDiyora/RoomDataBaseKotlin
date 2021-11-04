package com.example.roomdatabaseexample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun adduser(user: User)

    @Query("SELECT * FROM user_table")
    fun readAlldata(): kotlinx.coroutines.flow.Flow<List<User>>

//    @Query("SELECT * FROM user_table WHERE mobileNo =(:mo) and ")
//    fun loginUser()

}