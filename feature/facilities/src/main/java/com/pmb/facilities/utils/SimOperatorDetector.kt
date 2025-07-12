package com.pmb.facilities.utils

object SimOperatorDetector {

    // Validate if a phone number belongs to a valid operator using Enum
    fun detectOperator(phone: String): SimCardOperator? {
        val normalizedPhone = phone.normalizeIranPhone()

        // If the number is invalid
        if (normalizedPhone.length != 11 || !normalizedPhone.startsWith("09")) {
            return null
        }

        // Take the first 4 digits (the prefix)
        val prefix = normalizedPhone.take(4)

        // Find the operator using the enum class
        return SimCardOperator.getOperatorByPrefix(prefix)
    }

    // Check if the phone number is a valid Iranian SIM card
    fun isValidIranSim(phone: String): Boolean {
        return detectOperator(phone) != null
    }

    fun String.normalizeIranPhone(): String {
        return when {
            this.startsWith("+98") -> this.replace("+98", "0")
            this.startsWith("98") -> this.replaceFirst("98", "0")
            else -> this
        }
    }
}