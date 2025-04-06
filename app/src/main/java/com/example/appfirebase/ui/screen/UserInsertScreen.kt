package com.example.appfirebase.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appfirebase.ui.ViewModelProvider
import com.example.appfirebase.ui.viewmodel.UserInsertViewModel
import com.example.appfirebase.util.PhoneVisualTransformation
import kotlinx.coroutines.launch

@Composable
fun UserInsertScreen(
    modifier: Modifier = Modifier,
    user: UserInsertViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Cadastrar Cliente",
            fontSize = 24.sp
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = user.state.firstName,
                onValueChange = { user.updateUiState(user.state.copy(firstName = it)) },
                label = {
                    Text("Nome")
                }
            )
            OutlinedTextField(
                value = user.state.lastName,
                onValueChange = { user.updateUiState(user.state.copy(lastName = it)) },
                label = {
                    Text("Sobrenome")
                }
            )
            OutlinedTextField(
                value = user.state.email,
                onValueChange = { user.updateUiState(user.state.copy(email = it)) },
                label = {
                    Text("E-mail")
                }
            )
            OutlinedTextField(
                value = user.state.phone,
                onValueChange = {
                    if (it.length <= 11)
                        user.updateUiState(user.state.copy(phone = it))
                },
                label = {
                    Text("Telefone")
                },
                visualTransformation = PhoneVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = user.state.age?.toString() ?: "",
                onValueChange = {
                    if (it.isEmpty())
                        user.updateUiState(user.state.copy(age = null))
                    else {
                        try {
                            user.updateUiState(user.state.copy(age = it.toInt()))
                        } catch (_: Exception) {}
                    }
                },
                label = {
                    Text("Idade")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    user.save()
                }
            }
        ) {
            Text("Cadastrar")
        }
    }
}
