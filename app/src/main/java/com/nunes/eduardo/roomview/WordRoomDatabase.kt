package com.nunes.eduardo.roomview

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        private var INSTANCE: WordRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): WordRoomDatabase {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            WordRoomDatabase::class.java, "word_database")
                            .build()
                }
            }
            return INSTANCE as WordRoomDatabase
        }


    }


}