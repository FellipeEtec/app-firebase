package com.example.appfirebase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.appfirebase.data.model.User
import com.example.appfirebase.data.repository.UserRepository

class UserInsertViewModel(private val repository: UserRepository) : ViewModel() {
    var state by mutableStateOf(User())
        private set

    fun updateUiState(newState: User) {
        state = newState
    }

    suspend fun save(): Boolean {
        if(repository.insertUser(state)) {
            state = User()
            return true
        }

        return false
    }
}
