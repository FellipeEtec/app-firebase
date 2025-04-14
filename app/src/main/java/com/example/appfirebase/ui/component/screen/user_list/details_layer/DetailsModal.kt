package com.example.appfirebase.ui.component.screen.user_list.details_layer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appfirebase.data.model.User
import com.example.appfirebase.util.formatPhoneNumber

@Composable
fun DetailsModal(
    modifier: Modifier,
    selectedUser: User
) {
    val userPhoneNumber = remember {
        try {
            formatPhoneNumber(selectedUser.phone)
        } catch (e: IllegalArgumentException) {
            Log.e("UserDetailsModal", "User's phone number doesn't have 11 digits\n$e")
            "Nenhum"
        }
    }


    Column(
        modifier = modifier
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
            modifier = Modifier
                .padding(vertical = 12.dp)
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
            ModalItem(
                title = "Nome",
                content = selectedUser.firstName
            )

            ModalItem(
                title = "Sobrenome",
                content = selectedUser.lastName
            )

            ModalItem(
                title = "E-mail",
                content = selectedUser.email
            )

            ModalItem(
                title = "Telefone",
                content = userPhoneNumber
            )

            ModalItem(
                title = "Idade",
                content = "${selectedUser.age} anos"
            )
        }
    }
}