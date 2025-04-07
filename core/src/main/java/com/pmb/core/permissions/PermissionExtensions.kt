package com.pmb.core.permissions

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun PermissionDispatcher.requestSinglePermissionInCompose(
    permission: String,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        onSinglePermissionResult(isGranted)
    }
    LaunchedEffect(Unit) {
        initialize(permissionLauncher)
    }
    LaunchedEffect(Unit) {
        requestSinglePermission(
            permission = permission,
            onPermissionGranted = onPermissionGranted,
            onPermissionDenied = onPermissionDenied
        )
    }
}

@Composable
fun PermissionDispatcher.requestMultiplePermission(
    permissions: Array<String>,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: (List<String>) -> Unit
) {
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { isGranted ->
        onMultiplePermissionResult(isGranted)
    }
    LaunchedEffect(Unit) {
        initialize(multiplePermissionLauncher = permissionLauncher)
    }
    LaunchedEffect(Unit) {
        requestMultiplePermission(
            permissions = permissions,
            onPermissionGranted = onPermissionGranted,
            onPermissionDenied = onPermissionDenied
        )
    }
}