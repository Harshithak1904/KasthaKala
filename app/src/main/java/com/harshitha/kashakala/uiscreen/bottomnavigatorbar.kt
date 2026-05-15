package com.harshitha.kashakala.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.ExitToApp

// ✅ Define screens with proper icons
sealed class Screen(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Catalog : Screen("catalog", "Catalog", Icons.Default.Home)
    object Estimator : Screen("estimator", "Estimator", Icons.Default.Calculate)
    object Quotes : Screen("quotes", "Quotes", Icons.Default.ListAlt)
    object Portfolio : Screen("portfolio", "Portfolio", Icons.Default.Work)
    object Logout : Screen("logout", "Logout", Icons.Default.ExitToApp)
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(Screen.Catalog, Screen.Estimator, Screen.Quotes, Screen.Portfolio, Screen.Logout)
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
