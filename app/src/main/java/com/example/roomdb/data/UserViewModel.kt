package com.example.roomdb.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel:ViewModel(){


    fun addUser(context: Context,user: User){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.addUser(user)
//        }
        UserRepository.addUser(context,user)
    }
    fun readAllData(context: Context):LiveData<List<User>>{
        return UserRepository.readData(context)
    }
    fun updateData(context: Context,user: User){
        UserRepository.updateData(context,user)
    }
    fun deleteData(context: Context,user: User){
        UserRepository.deleteData(context,user)
    }
    fun delete(context: Context,id: Int){
        return UserRepository.delete(context,id)
    }
    fun deleteAll(context: Context){
        return UserRepository.deleteAll(context)
    }
}