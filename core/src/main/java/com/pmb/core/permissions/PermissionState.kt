package com.pmb.core.permissions

sealed class PermissionState {
    data object Granted : PermissionState()
    data class Denied(val permissions: List<String>) : PermissionState()
    data object ShowRational : PermissionState()
}