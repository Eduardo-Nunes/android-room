package com.nunes.eduardo.roomview

import android.app.Application
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class WordRepository(application: Application){
    private var mWordDao: WordDao
    private var mAllWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        this.mWordDao = db.wordDao()

/*
runBlocking {
mAllWords = getAllWords()
}
*/

        this.mAllWords = getAllWords()
    }

    /*
suspend fun getAllWords(): LiveData<List<Word>>{
return withContext(DefaultDispatcher) {
mWordDao.getAllWords()
}
}
*/
    fun getAllWords(): LiveData<List<Word>> {
        return async {
            mWordDao.getAllWords()
        }.getCompleted()
    }

    fun insert(word: Word) {
        launch {
            mWordDao.insertword(word)
        }
    }


}