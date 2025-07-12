package com.pmb.auth.domain.ekyc.capture_video.entity

data class CapturingVideoEntity(
    val isSuccess: Boolean
)

data class CapturingVideoParams(
    val uri: String?
)
