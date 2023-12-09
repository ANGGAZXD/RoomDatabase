package com.example.tutorialcrud.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tutorialcrud.data.dataaccessobject.UserDataAccessObject
import com.example.tutorialcrud.data.entity.User

@Database(entities = [User::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    //mengapa abstract class?
    //karena akan diwariskan ke kelas lain
    abstract fun userDataAccessObject(): UserDataAccessObject
    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if(instance== null){
                instance = Room.databaseBuilder(context, AppDatabase:: class.java, "app-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}