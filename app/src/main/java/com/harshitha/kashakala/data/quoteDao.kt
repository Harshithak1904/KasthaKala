package com.harshitha.kashakala.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuoteDao {
    @Insert
    suspend fun insert(quote: QuoteEntity)

    @Delete
    suspend fun delete(quote: QuoteEntity)

    @Query("SELECT * FROM quotes ORDER BY id DESC")
    fun getAllQuotes(): LiveData<List<QuoteEntity>>
}
