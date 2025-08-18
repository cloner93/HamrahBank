package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable


@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Person(
    val firstName: String?,
    val lastName: String?
)