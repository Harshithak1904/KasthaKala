package com.harshitha.kashakala.data

import androidx.lifecycle.LiveData

class QuoteRepository(private val quoteDao: QuoteDao) {
    fun getAllQuotes(): LiveData<List<QuoteEntity>> = quoteDao.getAllQuotes()
    suspend fun insert(quote: QuoteEntity) = quoteDao.insert(quote)
    suspend fun delete(quote: QuoteEntity) = quoteDao.delete(quote)
}
