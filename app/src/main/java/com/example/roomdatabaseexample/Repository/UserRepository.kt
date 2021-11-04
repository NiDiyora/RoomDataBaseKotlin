package com.example.roomdatabaseexample.Repository

import com.example.roomdatabaseexample.data.User
import com.example.roomdatabaseexample.data.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val readAlldata: Flow<List<User>> = userDao.readAlldata()


    suspend fun adduser(user: User) {
        userDao.adduser(user)
    }
}