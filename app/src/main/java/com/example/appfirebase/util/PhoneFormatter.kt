package com.example.appfirebase.util

fun formatPhoneNumber(phoneNumber: String): String {
    if (phoneNumber.length != 11 || !phoneNumber.all { it.isDigit() }) {
        throw IllegalArgumentException("Input must be exactly 11 digits")
    }

    return "(${phoneNumber.substring(0, 2)}) ${phoneNumber.substring(2, 7)}-${phoneNumber.substring(7)}"
}
