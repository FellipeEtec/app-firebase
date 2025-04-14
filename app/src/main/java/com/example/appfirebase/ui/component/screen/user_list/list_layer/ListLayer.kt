package com.example.appfirebase.ui.component.screen.user_list.list_layer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appfirebase.data.model.User
import com.example.appfirebase.ui.component.button.PrimaryButton
import com.example.appfirebase.ui.component.button.SecondaryButton

@Composable
fun ListLayer(
    modifier: Modifier,
    isGettingUsers: Boolean,
    userList: List<User>,
    selectUser: (User) -> Unit,
    refreshList: () -> Unit,
    navigateToInsert: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Usuários Cadastrados",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )

        ListContainer(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.8f)
                .padding(vertical = 14.dp),
            isGettingUsers = isGettingUsers,
            userList = userList,
            selectUser = selectUser
        )

        SecondaryButton(
            onClick = refreshList,
            text = "Recarregar",
            modifier = Modifier.width(200.dp),
            enabled = !isGettingUsers
        )

        PrimaryButton(
            onClick = navigateToInsert,
            text = "Novo Usuário",
            modifier = Modifier.width(200.dp)
        )
    }
}
