package com.harshitha.kashakala.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harshitha.kashakala.data.QuoteEntity
import com.harshitha.kashakala.viewmodel.QuoteViewModel

@Composable
fun QuotesScreen(quotes: List<QuoteEntity>, viewModel: QuoteViewModel) {
    var area by remember { mutableStateOf("") }
    var designName by remember { mutableStateOf("") }
    var volume by remember { mutableStateOf("") }
    var woodType by remember { mutableStateOf("") }
    var laborCost by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = area, onValueChange = { area = it }, label = { Text("Area (sq ft)") })
        OutlinedTextField(value = designName, onValueChange = { designName = it }, label = { Text("Design Name") })
        OutlinedTextField(value = volume, onValueChange = { volume = it }, label = { Text("Volume (cft)") })
        OutlinedTextField(value = woodType, onValueChange = { woodType = it }, label = { Text("Wood Type") })
        OutlinedTextField(value = laborCost, onValueChange = { laborCost = it }, label = { Text("Labor Cost") })

        Button(onClick = {
            val a = area.toDoubleOrNull() ?: 0.0
            val v = volume.toDoubleOrNull() ?: 0.0
            val l = laborCost.toDoubleOrNull() ?: 0.0
            if (designName.isNotBlank() && woodType.isNotBlank()) {
                viewModel.insertQuote(a, designName, v, woodType, l)
                area = ""; designName = ""; volume = ""; woodType = ""; laborCost = ""
            }
        }) {
            Text("Save Quote")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(quotes) { q ->
                Card(Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(Modifier.padding(8.dp)) {
                        Text("Design: ${q.designName}")
                        Text("Area: ${q.area} sq ft")
                        Text("Volume: ${q.volume} cft")
                        Text("Wood: ${q.woodType}")
                        Text("Labor: ₹${q.laborCost}")
                        Text("Total: ₹${q.totalCost}")
                    }
                }
            }
        }
    }
}
