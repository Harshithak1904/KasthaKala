package com.harshitha.kashakala.uiscreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
    object Catalog : Screen("catalog", Icons.Default.Home, "Catalog")
    object Estimator : Screen("estimator", Icons.Filled.Calculate, "Estimator")
    object Quotes : Screen("quotes", Icons.AutoMirrored.Filled.List, "Quotes")
    object Portfolio : Screen("portfolio", Icons.Default.Person, "Portfolio")
}
