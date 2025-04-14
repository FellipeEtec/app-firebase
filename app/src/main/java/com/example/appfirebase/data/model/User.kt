package com.example.appfirebase.data.model

import com.google.firebase.firestore.DocumentId
import kotlin.ranges.contains

data class User(
    @DocumentId
    val documentId: String = "",

    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phone: String = "",
    var age: Int? = null
) : FirestoreModel {
    override fun verifySavable(): Boolean {
        return (
            this.firstName.isNotBlank() &&
            this.lastName.isNotBlank() &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(this.email).matches() &&
            this.phone.filter { it.isDigit() }.length == 11 &&
            this.age in 13..120
        )
    }
}
