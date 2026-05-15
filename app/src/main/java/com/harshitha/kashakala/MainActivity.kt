package com.harshitha.kashakala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.harshitha.kashakala.uiscreen.KashtaKalaApp
import com.harshitha.kashakala.ui.theme.KashakalaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KashakalaTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    // ✅ Navigation setup (no login)
                    NavHost(navController = navController, startDestination = "kashtakalaApp") {
                        composable("kashtakalaApp") {
                            KashtaKalaApp(navController)
                        }
                    }
                }
            }
        }
    }
}
