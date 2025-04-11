package com.example.appfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.example.appfirebase.navigation.AppRoot
import com.example.appfirebase.ui.screen.UserInsertScreen
import com.example.appfirebase.ui.theme.AppFirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            AppFirebaseTheme {
                AppRoot()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AppFirebaseTheme {
        AppRoot()
    }
}
