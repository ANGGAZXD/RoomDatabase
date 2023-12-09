package com.example.tutorialcrud.data.dataaccessobject

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tutorialcrud.data.entity.User


//UserDataAccessObject digunakan untuk Query dari Entity User dari Package sebelah

@Dao

//Mengapa Interface?
//Karena tidak ada body yang akan ditampilkan.
interface UserDataAccessObject {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user WHERE uid = :uid")
    fun get(uid: Int) : User

    @Update
    fun update(user: User)
}