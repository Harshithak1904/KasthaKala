package com.harshitha.kashakala.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun DesignCatalogScreen(navController: NavController) {
    val designs = listOf(
        Design("Minimalist Sofa", "Teak", "https://picsum.photos/400/300?sofa"),
        Design("Wooden Bench", "Rosewood", "https://picsum.photos/400/300?bench"),
        Design("Modern Coffee Table", "Plywood", "https://picsum.photos/400/300?table"),
        Design("Classic Cupboard", "Mango Wood", "https://picsum.photos/400/300?cupboard")
    )

    Column(Modifier.padding(8.dp)) {
        designs.forEach { design ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                onClick = {
                    navController.navigate(
                        "estimator/${design.name}/10/10/10/${design.woodType}"
                    )
                }
            ) {
                Column {
                    Image(
                        painter = rememberAsyncImagePainter(design.imageUrl),
                        contentDescription = design.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = design.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black   // ✅ visible black text
                    )
                    Text(
                        text = "Wood: ${design.woodType}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black   // ✅ visible black text
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

data class Design(val name: String, val woodType: String, val imageUrl: String)
