package com.example.appfirebase.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.appfirebase.data.models.User

class UserInsertViewModel : ViewModel() {
    var state by mutableStateOf(User())
        private set

    fun updateUiState(newState: User) {
        state = newState
    }

    fun verifySaveable(): Boolean {
        return state.name.isNotBlank()
    }
}
