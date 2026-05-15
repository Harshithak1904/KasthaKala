package com.harshitha.kashakala.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.harshitha.kashakala.viewmodel.QuoteViewModel

@Composable
fun EstimatorScreen(
    viewModel: QuoteViewModel,
    backStackEntry: NavBackStackEntry
) {
    val designName = backStackEntry.arguments?.getString("name") ?: ""
    val length = backStackEntry.arguments?.getString("length")?.toFloatOrNull() ?: 0f
    val width = backStackEntry.arguments?.getString("width")?.toFloatOrNull() ?: 0f
    val height = backStackEntry.arguments?.getString("height")?.toFloatOrNull() ?: 0f
    val woodType = backStackEntry.arguments?.getString("woodType") ?: ""

    var laborCost by remember { mutableStateOf("") }
    var totalCost by remember { mutableStateOf<Double?>(null) }

    val area = length * width
    val volume = length * width * height

    Column(Modifier.padding(16.dp)) {
        Text("Design: $designName", style = MaterialTheme.typography.titleMedium)
        Text("Dimensions: $length x $width x $height")
        Text("Area: $area sq units")
        Text("Volume: $volume cubic units")
        Text("Wood: $woodType")

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = laborCost,
            onValueChange = { laborCost = it },
            label = { Text("Labor Cost") }
        )

        Spacer(Modifier.height(12.dp))

        Button(onClick = {
            val l = laborCost.toDoubleOrNull() ?: 0.0
            val total = l + (area * height)
            totalCost = total
            viewModel.insertQuote(area.toDouble(), designName, volume.toDouble(), woodType, l)
        }) {
            Text("Generate Quote")
        }

        Spacer(Modifier.height(16.dp))

        totalCost?.let {
            Text("Estimated Total Cost: ₹$it", style = MaterialTheme.typography.titleMedium)
        }
    }
}
