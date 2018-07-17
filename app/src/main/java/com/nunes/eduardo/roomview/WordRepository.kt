package com.nunes.eduardo.roomview

import android.app.Application
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.*

class WordRepository(application: Application){
    private var mWordDao: WordDao
    private lateinit var mAllWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        this.mWordDao = db.wordDao()

        runBlocking {
            mAllWords = getAllWords()
        }

    }

    suspend fun getAllWords(): LiveData<List<Word>> {
        return withContext(DefaultDispatcher) {
            mWordDao.getAllWords()
        }
    }

    fun insert(word: Word) {
        launch {
            mWordDao.insertword(word)
        }
    }

    fun clearAll(){
        launch {
            mWordDao.deleteAllWords()
        }
    }

}