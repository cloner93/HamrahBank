package com.pmb.auth.domain.ekyc.captureVideo.entity

data class CapturingVideoEntity(
    val isSuccess: Boolean
)

data class CapturingVideoParams(
    val uri: String?
)
