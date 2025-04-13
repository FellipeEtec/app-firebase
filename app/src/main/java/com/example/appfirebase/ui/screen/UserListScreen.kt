package com.example.appfirebase.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appfirebase.data.model.User
import com.example.appfirebase.ui.ViewModelProvider
import com.example.appfirebase.ui.component.screen.user_list.details_layer.DetailsLayer
import com.example.appfirebase.ui.component.screen.user_list.list_layer.ListLayer
import com.example.appfirebase.ui.viewmodel.UserListViewModel
import kotlinx.serialization.Serializable

@Serializable
object UserListScreen

@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userListViewModel: UserListViewModel =
        viewModel(
            factory = ViewModelProvider.Factory
        )
) {
    var showUserDetails by remember {
        mutableStateOf(false)
    }
    var selectedUser: User by remember {
        mutableStateOf(User())
    }

    val userList = userListViewModel.state.collectAsState().value

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        ListLayer(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            isGettingUsers = userListViewModel.isGettingUsers,
            userList = userList,
            selectUser = { user ->
                selectedUser = user
                showUserDetails = true
            },
            refreshList = {
                userListViewModel.getUsers()
            },
            navigateToInsert = {
                navController.navigate(UserInsertScreen)
            }
        )

        DetailsLayer(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    onClick = {
                        showUserDetails = false
                    },
                    interactionSource = null,
                    indication = null
                ),
            selectedUser = selectedUser,
            showUserDetails = showUserDetails
        )
    }
}
