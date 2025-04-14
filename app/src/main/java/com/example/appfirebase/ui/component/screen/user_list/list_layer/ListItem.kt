package com.example.appfirebase.ui.component.screen.user_list.list_layer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appfirebase.data.model.User
import com.example.appfirebase.ui.theme.Design

@Composable
fun ListItem(
    onClick: () -> Unit,
    modifier: Modifier,
    user: User
) {
    Button(
        onClick = onClick,
        modifier = modifier,
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
            color = Design.Color.backgroundNeutral,
            contentColor = Design.Color.foregroundNeutral
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
