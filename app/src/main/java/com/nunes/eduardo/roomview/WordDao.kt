package com.nunes.eduardo.roomview

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface WordDao {

    @Insert( onConflict = REPLACE)
    fun insertword(word: Word)

    @Update
    fun updateWord(word: Word)

    @Delete
    fun deleteWord(word: Word)

    @Query("DELETE from word_table")
    fun deleteAllWords()

    @Query("SELECT * from word_table ORDER BY word_value ASC")
    fun getAllWords(): List<Word>
}