package com.example.appfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.appfirebase.navigation.AppRoot
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
