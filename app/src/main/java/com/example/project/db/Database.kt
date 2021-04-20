package com.example.project.data.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project.dao.BaseDao


@Database(entities = [Users::class,Items::class,Transactions::class,Cards::class],version= 2)

abstract class DataBase: RoomDatabase() {

    abstract fun BaseDao():BaseDao

    companion object {
        @Volatile
        private var databaseInstance: DataBase? = null

        fun getDatabaseInstance(mContext: Context): DataBase =
            databaseInstance ?: synchronized(this) {
                databaseInstance ?: buildDatabaseInstance(mContext).also {
                    databaseInstance = it
                }
            }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext, DataBase::class.java, "ryfhfddf")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

    }
}