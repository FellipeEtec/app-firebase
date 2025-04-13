package com.example.appfirebase.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appfirebase.data.model.User
import com.example.appfirebase.ui.ViewModelProvider
import com.example.appfirebase.ui.viewmodel.UserListViewModel
import com.example.appfirebase.util.formatPhoneNumber
import kotlinx.serialization.Serializable

@Serializable
object UserListScreen

@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userListViewModel: UserListViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    var showUserDetails by remember { mutableStateOf(false) }
    var selectedUser: User by remember { mutableStateOf(User()) }

    val users = userListViewModel.state.collectAsState().value

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Usuários Cadastrados",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(0.75f)
                    .padding(vertical = 14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (userListViewModel.loading) {
                    Text("Carregando...")
                }
                else {
                    if (users.isEmpty()) {
                        Text("Não há usuários")
                    }
                    else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 14.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(users) { user ->
                                Button(
                                    onClick = {
                                        selectedUser = user
                                        showUserDetails = true
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent
                                    ),
                                    elevation = ButtonDefaults.buttonElevation(2.dp),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Surface(
                                        modifier = Modifier.fillMaxSize(),
                                        shape = RoundedCornerShape(8.dp),
                                        color = Color(0xFFF6F6F6),
                                        contentColor = Color.Black
                                    ) {
                                        Text(
                                            text = "${user.firstName} ${user.lastName}",
                                            modifier = Modifier
                                                .padding(
                                                    vertical = 14.dp,
                                                    horizontal = 20.dp
                                                ),
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Button(
                onClick = {
                    userListViewModel.getUsers()
                },
                enabled = !userListViewModel.loading,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF6F6F6),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Recarregar a lista",
                    modifier = Modifier.padding(horizontal = 24.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Button(
                onClick = {
                    navController.navigate(UserInsertScreen)
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Cadastrar Usuário",
                    modifier = Modifier.padding(horizontal = 24.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        AnimatedVisibility(
            visible = showUserDetails,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        onClick = {
                            showUserDetails = false
                        },
                        interactionSource = null,
                        indication = null
                    ),
                color = Color(0x40101010)
            ) { }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .animateEnterExit(
                            enter = slideInVertically(),
                            exit = slideOutVertically()
                        ),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White,
                    shadowElevation = 16.dp
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {
                        Text(
                            text = "Detalhes do Usuário",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            fontSize = 26.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp)
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color(0xFFF6F6F6),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(
                                    vertical = 16.dp,
                                    horizontal = 24.dp
                                ),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // First name
                            Column {
                                Text(
                                    text = "Nome",
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = selectedUser.firstName,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            // Last name
                            Column {
                                Text(
                                    text = "Sobrenome",
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = selectedUser.lastName,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            // E-mail
                            Column {
                                Text(
                                    text = "E-mail",
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = selectedUser.email,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            // Phone
                            Column {
                                Text(
                                    text = "Telefone",
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = formatPhoneNumber(selectedUser.phone),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            // Age
                            Column {
                                Text(
                                    text = "Idade",
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "${selectedUser.age} anos",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
