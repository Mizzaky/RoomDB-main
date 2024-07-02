package com.example.roomdb.data

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository {

//    val readAllData: LiveData<List<User>> = userDao.readAllData()

    companion object{
        var database : UserDatabase ? = null
        private fun initializeDatabase(context: Context):UserDatabase{
            return UserDatabase.getDatabase(context)
        }
//        suspend fun addUser(user: User){
//
//        }
//        fun readData():ArrayList<User>{
//            return userDao.readAllData()
//        }
        fun readData(context: Context):LiveData<List<User>>{
            database = initializeDatabase(context)
            return database!!.userDao().readAllData()
        }
        fun addUser(context: Context,user: User){
            database = initializeDatabase(context)
            CoroutineScope(IO).launch {
                database!!.userDao().addUser(user)
            }
        }
        fun updateData(context: Context,user: User){
            database = initializeDatabase(context)
            CoroutineScope(IO).launch {
                database!!.userDao().updateData(user)
            }
        }

        //new feature added.. git test
        fun deleteData(context: Context,user: User){
           database = initializeDatabase(context)
            CoroutineScope(IO).launch {
                database!!.userDao().deleteData(user)
            }
        }
        fun delete(context: Context,id:Int){
            database = initializeDatabase(context)
            return database!!.userDao().delete(id)
        }
        fun deleteAll(context: Context){
            database = initializeDatabase(context)
            CoroutineScope(IO).launch {
                database!!.userDao().deleteAll()
            }
        }


    }
}