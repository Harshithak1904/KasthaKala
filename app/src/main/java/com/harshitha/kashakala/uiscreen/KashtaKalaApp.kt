package com.harshitha.kashakala.uiscreen

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Room
import com.harshitha.kashakala.data.AppDatabase
import com.harshitha.kashakala.data.QuoteRepository
import com.harshitha.kashakala.viewmodel.QuoteViewModel
import com.harshitha.kashakala.ui.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun KashtaKalaApp(rootNavController: NavController) {
    val navController = rememberNavController()

    // ✅ Setup Room DB + Repository
    val context = rootNavController.context
    val db = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "kashtakala_db"
        ).build()
    }
    val repository = remember { QuoteRepository(db.quoteDao()) }

    // ✅ Create ViewModel with repository
    val quoteViewModel: QuoteViewModel = viewModel(
        factory = viewModelFactory {
            initializer { QuoteViewModel(repository) }
        }
    )

    // ✅ Define bottom nav items directly
    data class NavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

    val items = listOf(
        NavItem("catalog", "Catalog", Icons.Default.Home),
        NavItem("estimator", "Estimate", Icons.Default.Calculate),
        NavItem("quotes", "Quotes", Icons.Default.List),
        NavItem("portfolio", "Portfolio", Icons.Default.Work),
        NavItem("logout", "Logout", Icons.Default.ExitToApp)
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (screen.route == "estimator") {
                                // Default estimator route if opened directly
                                navController.navigate("estimator/Demo/10/10/10/Teak")
                            } else {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "catalog",
            modifier = Modifier.padding(innerPadding)
        ) {
            // ✅ Catalog with images
            composable("catalog") { DesignCatalogScreen(navController) }

            // ✅ Estimator with dynamic params
            composable("estimator/{name}/{length}/{width}/{height}/{woodType}") { backStackEntry ->
                EstimatorScreen(viewModel = quoteViewModel, backStackEntry = backStackEntry)
            }

            // ✅ Quotes tab
            composable("quotes") {
                val quotes = quoteViewModel.allQuotes.observeAsState(emptyList())
                QuotesScreen(quotes = quotes.value, viewModel = quoteViewModel)
            }

            // ✅ Portfolio tab
            composable("portfolio") { PortfolioScreen() }

            // ✅ Logout tab
            composable("logout") {
                rootNavController.navigate("kashtakalaApp") {
                    popUpTo("kashtakalaApp") { inclusive = true }
                }
            }
        }
    }
}
