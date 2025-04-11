package com.example.appfirebase.navigation

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.composable
import com.example.appfirebase.ui.screen.UserInsertScreen
import com.example.appfirebase.ui.screen.UserListScreen

@Composable
fun AppRoot() {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            },
        containerColor = Color.White
    ) { innerPadding ->
        AppNavigation { navController ->
            composable<UserListScreen> {
                UserListScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController
                )
            }
            composable<UserInsertScreen> {
                UserInsertScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }
    }
}
