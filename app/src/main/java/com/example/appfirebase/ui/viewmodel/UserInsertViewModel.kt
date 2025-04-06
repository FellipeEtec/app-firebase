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

    fun verifySavable(): Boolean {
        return (
                state.firstName.isNotBlank() &&
                state.lastName.isNotBlank() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(state.email).matches() &&
                state.phone.filter { it.isDigit() }.length in 10..11 &&
                state.age in 13..100
        )
    }

    fun save(): Boolean {
        if (!verifySavable())
            return false

        val result = repository.insertUser(state)

        state = User()

        return result
    }
}
