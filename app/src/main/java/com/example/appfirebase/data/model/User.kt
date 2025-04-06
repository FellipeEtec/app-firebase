package com.example.appfirebase.data.model

import kotlin.ranges.contains

data class User(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phone: String = "",
    var age: Int? = null
)

fun User.verifySavable(): Boolean {
    return (
        this.firstName.isNotBlank() &&
        this.lastName.isNotBlank() &&
        android.util.Patterns.EMAIL_ADDRESS.matcher(this.email).matches() &&
        this.phone.filter { it.isDigit() }.length in 10..11 &&
        this.age in 13..100
    )
}
