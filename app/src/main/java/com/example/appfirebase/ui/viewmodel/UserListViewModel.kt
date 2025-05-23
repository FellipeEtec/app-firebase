package com.example.appfirebase.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appfirebase.data.model.User
import com.example.appfirebase.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: UserRepository) : ViewModel() {
    private val _state = MutableStateFlow<List<User>>(emptyList())
    val state = _state.asStateFlow()
    var isGettingUsers = true

    fun getUsers() {
        isGettingUsers = true

        viewModelScope.launch {
            _state.value = repository.getAll()
            isGettingUsers = false
        }
    }

    init {
        repository.collection.addSnapshotListener { _, e ->
            if (e != null) {
                Log.w("UserListViewModel", "listen:error", e)
                return@addSnapshotListener
            }

            getUsers()
        }
    }
}
