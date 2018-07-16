package com.nunes.eduardo.roomview

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class WordViewModel : AndroidViewModel{
    private var mRepository: WordRepository
    private var mAllWords: LiveData<List<Word>>

    constructor(application: Application) : super(application) {
        mRepository = WordRepository(application)
        mAllWords = mRepository.getAllWords()
    }

    fun insert(word: Word){
        mRepository.insert(word)
    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }
}