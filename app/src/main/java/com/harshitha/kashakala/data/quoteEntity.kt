package com.harshitha.kashakala.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val area: Double,
    val designName: String,
    val volume: Double,
    val woodType: String,
    val laborCost: Double,
    val totalCost: Double
)
