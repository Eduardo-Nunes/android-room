package com.nunes.eduardo.roomview

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "word_table")
data class Word (

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @NonNull
        @ColumnInfo(name = "word_value")
        val mWord: String
)