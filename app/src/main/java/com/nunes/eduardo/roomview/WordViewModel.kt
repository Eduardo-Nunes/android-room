package com.nunes.eduardo.roomview

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.runBlocking

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private var mRepository: WordRepository = WordRepository(application)
    private lateinit var mAllWords: LiveData<List<Word>>

    init {
        runBlocking {
            mAllWords = mRepository.getAllWords()
        }
    }

    fun insert(word: Word){
        mRepository.insert(word)
    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun clearWords() {
        runBlocking {
            mRepository.clearAll()
        }
    }
}