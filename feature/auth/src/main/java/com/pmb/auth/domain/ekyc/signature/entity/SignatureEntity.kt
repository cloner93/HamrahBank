package com.pmb.auth.domain.ekyc.signature.entity

data class SignatureEntity(
    val isSuccess: Boolean
)

data class SignatureParams(
    val uri: String?,
)