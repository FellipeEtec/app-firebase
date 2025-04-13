package com.example.appfirebase.ui.component.screen.user_list.details_layer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.appfirebase.data.model.User

@Composable
fun DetailsLayer(
    modifier: Modifier,
    selectedUser: User,
    showUserDetails: Boolean,
) {
    AnimatedVisibility(
        visible = showUserDetails,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Surface(
            modifier = modifier,
            color = Color(0x40101010)
        ) { }

        Column(
            modifier = Modifier
                .fillMaxSize(),
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
                DetailsModal(
                    modifier = Modifier
                        .padding(18.dp),
                    selectedUser = selectedUser
                )
            }
        }
    }
}
