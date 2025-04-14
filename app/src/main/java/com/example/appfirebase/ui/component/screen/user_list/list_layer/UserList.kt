package com.example.appfirebase.ui.component.screen.user_list.list_layer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appfirebase.data.model.User

@Composable
fun UserList(
    modifier: Modifier,
    userList: List<User>,
    selectUser: (User) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(userList) { user ->
            ListItem(
                onClick = {
                    selectUser(user)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                user = user
            )
        }
    }
}
