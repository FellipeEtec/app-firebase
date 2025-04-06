package com.example.appfirebase.data.repository

import com.example.appfirebase.data.model.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserRepository(
    db: FirebaseFirestore = Firebase.firestore
) : FirestoreRepository<User> {
    override val collection = db.collection("users")

    override suspend fun insert(doc: User): Boolean {
        if (!doc.verifySavable())
            return false

        return try {
            collection.add(doc).await()
            true
        } catch (_: Exception) {
            false
        }
    }
}
