package com.example.appfirebase.ui.component.screen.user_list.details_layer

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ModalItem(
    title: String,
    content: String
) {
    Column {
        Text(
            text = title,
            fontSize = 14.sp
        )
        Text(
            text = content,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
