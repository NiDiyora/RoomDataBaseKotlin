package com.example.roomdatabaseexample.Viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabaseexample.Repository.UserRepository
import com.example.roomdatabaseexample.data.User
import com.example.roomdatabaseexample.data.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAlldata: Flow<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getdatabase(application).userDao()
        repository = UserRepository(userDao)
        readAlldata = repository.readAlldata
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.adduser(user)
        }
    }


}