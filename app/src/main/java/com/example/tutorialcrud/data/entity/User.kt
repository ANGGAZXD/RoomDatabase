package com.example.tutorialcrud.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//Entity setara dengan table
//Sama aja dengan membuat table User di DataBase
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,

    @ColumnInfo(name = "full_name")
    var fullName: String?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "phone")
    var phone: String?
)
