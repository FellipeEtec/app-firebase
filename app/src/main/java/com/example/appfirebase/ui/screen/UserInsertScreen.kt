package com.example.appfirebase.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appfirebase.ui.ViewModelProvider
import com.example.appfirebase.ui.viewmodel.UserInsertViewModel

@Composable
fun UserInsertScreen(
    modifier: Modifier = Modifier,
    user: UserInsertViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    Column(
        modifier = modifier.fillMaxSize().padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Cadastrar Cliente",
            fontSize = 24.sp
        )

        TextField(
            value = user.state.name,
            onValueChange = { user.updateUiState(user.state.copy(name = it)) },
            label = {
                Text("Nome")
            }
        )

        Button(
            onClick = {
            }
        ) {
            Text("Enviar")
        }
    }
}
