package com.ubaya.a160419082_newsapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(News::class,MyNews::class,User::class),version = 2)

abstract class NewsAppDatabase: RoomDatabase() {
    abstract fun newsDao():NewsDao
    abstract fun myNewsDao():MyNewsDao
    abstract fun userDao():UserDao

    companion object {
        @Volatile private var instance: NewsAppDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsAppDatabase::class.java,
                "news").build()

        operator fun invoke(context: Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}