package com.example.appfirebase.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appfirebase.data.model.User
import com.example.appfirebase.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow

class UserListViewModel(private val repository: UserRepository) : ViewModel() {
    var state: MutableStateFlow<List<User>>? = null
        private set

    init {
        state = repository
    }
}
