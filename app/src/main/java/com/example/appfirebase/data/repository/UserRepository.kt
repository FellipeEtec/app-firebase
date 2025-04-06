package com.example.appfirebase.data.repository

import com.example.appfirebase.data.model.User
import com.example.appfirebase.data.model.verifySavable
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserRepository {
    suspend fun insertUser(user: User): Boolean {
        if (!user.verifySavable())
            return false

        val db = Firebase.firestore

        return try {
            db.collection("user").add(user).await()
            true
        } catch (_: Exception) {
            false
        }
    }
}
