package com.example.appfirebase.data.repository

import com.example.appfirebase.data.model.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class UserRepository {
    fun insertUser(user: User): Boolean {
        val db = Firebase.firestore

        return db.collection("user")
            .add(user).isSuccessful
    }
}
