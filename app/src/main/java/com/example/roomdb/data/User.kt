package com.example.roomdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "user_table")
data class User(
    var firstName:String,
    var lastName:String,
    var age: Int
){
    @PrimaryKey(autoGenerate = true)
    var id:Int ?= null
}
