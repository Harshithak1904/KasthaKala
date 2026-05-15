package com.harshitha.kashakala.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

@Composable
fun HomeScreen(navController: NavController) {
    val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(navController.context)

    LaunchedEffect(account) {
        if (account != null) {
            navController.navigate("kashtakalaApp") {
                popUpTo("home") { inclusive = true }
            }
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🎉 You are logged in!", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(24.dp))
            account?.let {
                it.photoUrl?.let { uri ->
                    Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentDescription = "Profile Photo",
                        modifier = Modifier.size(100.dp).padding(8.dp)
                    )
                }
                Text("Name: ${it.displayName}", style = MaterialTheme.typography.titleMedium)
                Text("Email: ${it.email}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
