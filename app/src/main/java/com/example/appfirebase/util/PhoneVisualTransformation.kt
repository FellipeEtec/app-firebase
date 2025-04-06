package com.example.appfirebase.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        // Safely handle any input length
        val trimmed = text.text.take(11)

        var out = if (trimmed.isNotEmpty()) "(" else ""

        for (i in trimmed.indices) {
            if (i == 2) out += ") "
            if (i == 7) out += "-"
            out += trimmed[i]
        }

        return TransformedText(AnnotatedString(out), object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return offset

                // Ensure we don't go beyond the input length
                val safeOffset = offset.coerceAtMost(trimmed.length)

                return when (safeOffset) {
                    in 1..2 -> safeOffset + 1  // Add 1 for opening parenthesis
                    in 3..7 -> safeOffset + 3  // Add 3 for both parentheses and a space
                    else -> safeOffset + 4     // Add 4 for both parentheses, space, and hyphen
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 0) return offset

                // Calculate the maximum possible transformed length
                val maxTransformedLength = trimmed.length + when {
                    trimmed.isEmpty() -> 0
                    trimmed.length <= 2 -> 1  // Just opening parenthesis
                    trimmed.length <= 7 -> 3  // Both parentheses and space
                    else -> 4  // Both parentheses, space, and hyphen
                }

                // Ensure we don't go beyond the transformed text length
                val safeOffset = offset.coerceAtMost(maxTransformedLength)

                return when (safeOffset) {
                    in 1..4 -> safeOffset - 1   // Subtract 1 for opening parenthesis
                    in 5..12 -> safeOffset - 3  // Subtract 3 for both parentheses and a space
                    else -> safeOffset - 4      // Subtract 4 for both parentheses, space, and hyphen
                }
            }
        })
    }
}
