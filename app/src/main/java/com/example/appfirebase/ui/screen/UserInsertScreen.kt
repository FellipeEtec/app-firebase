package com.example.appfirebase.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appfirebase.ui.ViewModelProvider
import com.example.appfirebase.ui.component.button.PrimaryButton
import com.example.appfirebase.ui.component.button.SecondaryButton
import com.example.appfirebase.ui.viewmodel.UserInsertViewModel
import com.example.appfirebase.util.PhoneVisualTransformation
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
object UserInsertScreen

@Composable
fun UserInsertScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    user: UserInsertViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    var submit by remember { mutableStateOf(false) }
    var success by remember { mutableStateOf<Boolean?>(null) }

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

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text =
                    if (!submit || success === null) ""
                    else if (success == true) "Cadastrado com sucesso"
                    else "Não foi possível cadastrar"
            )

            PrimaryButton(
                onClick = {
                    coroutineScope.launch {
                        success = user.save()
                    }

                    submit = true
                },
                text = "Cadastrar",
                modifier = Modifier.width(200.dp)
            )

            SecondaryButton(
                onClick = {
                    navController.popBackStack()
                },
                text = "Voltar",
                modifier = Modifier.width(200.dp)
            )
        }
    }
}
