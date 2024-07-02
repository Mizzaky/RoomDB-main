package com.example.roomdb.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert(onConflict =OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<User>>

    @Update
    suspend fun updateData(user: User)

    @Delete
    suspend fun deleteData(user: User)

    @Query("DELETE FROM user_table WHERE id = :id")
    fun delete(id: Int)

    @Query("Delete FROM user_table")
    suspend fun deleteAll()
}