package com.example.appfirebase.ui.component.screen.user_list.list_layer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.appfirebase.data.model.User

@Composable
fun ListContainer(
    modifier: Modifier,
    isGettingUsers: Boolean,
    userList: List<User>,
    selectUser: (User) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isGettingUsers) {
            Text(
                text = "Carregando..."
            )
        }
        else {
            if (userList.isEmpty()) {
                Text(
                    text = "Não há usuários"
                )
            }
            else {
                UserList(
                    modifier = Modifier
                        .fillMaxSize(),
                    userList = userList,
                    selectUser = selectUser
                )
            }
        }
    }
}
