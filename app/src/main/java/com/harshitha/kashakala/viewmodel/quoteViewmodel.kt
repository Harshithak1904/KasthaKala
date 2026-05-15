package com.harshitha.kashakala.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshitha.kashakala.data.QuoteEntity
import com.harshitha.kashakala.data.QuoteRepository
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {
    val allQuotes: LiveData<List<QuoteEntity>> = repository.getAllQuotes()

    fun insertQuote(area: Double, designName: String, volume: Double, woodType: String, laborCost: Double) {
        val total = laborCost + (area * volume) // simple formula, adjust as needed
        viewModelScope.launch {
            repository.insert(
                QuoteEntity(
                    area = area,
                    designName = designName,
                    volume = volume,
                    woodType = woodType,
                    laborCost = laborCost,
                    totalCost = total
                )
            )
        }
    }

    fun deleteQuote(quote: QuoteEntity) {
        viewModelScope.launch { repository.delete(quote) }
    }
}
