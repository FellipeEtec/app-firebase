package com.example.appfirebase.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appfirebase.ui.ViewModelProvider
import com.example.appfirebase.ui.viewmodel.UserListViewModel
import kotlinx.serialization.Serializable

@Serializable
object UserListScreen

@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    users: UserListViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(10) {
                Text("a")
            }
        }

        Button(
            onClick = {
                navController.navigate(UserInsertScreen)
            }
        ) {
            Text("Cadastrar Cliente")
        }
    }
}
