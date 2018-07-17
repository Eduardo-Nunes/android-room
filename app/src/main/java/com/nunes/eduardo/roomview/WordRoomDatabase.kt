package com.nunes.eduardo.roomview

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.arch.persistence.db.SupportSQLiteDatabase
import kotlinx.coroutines.experimental.launch

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @JvmStatic
        private var INSTANCE: WordRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): WordRoomDatabase {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            WordRoomDatabase::class.java, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                }
            }
            return INSTANCE as WordRoomDatabase
        }

        @JvmStatic
        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                synchronized(WordRoomDatabase::class.java) {
                    super.onOpen(db)
                    launch {
                        INSTANCE?.wordDao()?.deleteAllWords()
                    }
                }
            }
        }
    }
}