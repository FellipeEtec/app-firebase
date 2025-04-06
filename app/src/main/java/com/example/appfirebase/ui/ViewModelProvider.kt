package com.example.appfirebase.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appfirebase.data.repository.UserRepository
import com.example.appfirebase.ui.viewmodel.UserInsertViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            UserInsertViewModel(UserRepository())
        }
    }
}
