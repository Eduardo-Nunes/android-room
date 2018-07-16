package com.nunes.eduardo.roomview

import android.app.Application
import android.arch.lifecycle.LiveData
import android.content.Context

class WordRepository(application: Application){
    private lateinit var mWordDao: WordDao
    private lateinit var mAllWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        this.mWordDao = db.wordDao()
        this.mAllWords = mWordDao.getAllWords()
    }
}