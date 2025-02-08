package com.pmb.core.permissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionDispatcher @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var singlePermissionLauncher: ActivityResultLauncher<String>? = null
    private var multiplePermissionLauncher: ActivityResultLauncher<Array<String>>? = null
    private var onSinglePermissionGranted: (() -> Unit)? = null
    private var onMultiplePermissionGranted: (() -> Unit)? = null
    private var onSinglePermissionDenied: (() -> Unit)? = null
    private var onMultiplePermissionDenied: ((List<String>) -> Unit)? = null
    fun initialize(
        singlePermissionLauncher: ActivityResultLauncher<String>? = null,
        multiplePermissionLauncher: ActivityResultLauncher<Array<String>>? = null
    ) {
        this.singlePermissionLauncher = singlePermissionLauncher
        this.multiplePermissionLauncher = multiplePermissionLauncher
    }

    fun requestMultiplePermission(
        permissions: Array<String>,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: (List<String>) -> Unit
    ) {
        this.onMultiplePermissionGranted = onPermissionGranted
        this.onMultiplePermissionDenied = onPermissionDenied
        val deniedPermissions = permissions.filter {
            ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
        }
        if (deniedPermissions.isEmpty()) {
            onPermissionGranted()
        } else {
            multiplePermissionLauncher?.launch(deniedPermissions.toTypedArray())
        }
    }

    fun requestSinglePermission(
        permission: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    ) {
        this.onSinglePermissionGranted = onPermissionGranted
        this.onSinglePermissionDenied = onPermissionDenied

        if (ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            onPermissionGranted()
        } else {
            singlePermissionLauncher?.launch(permission)
        }
    }

    fun onSinglePermissionResult(isGranted: Boolean) {
        if (isGranted) {
            onSinglePermissionGranted?.invoke()
        } else {
            onSinglePermissionDenied?.invoke()
        }
    }

    fun onMultiplePermissionResult(grantResults: Map<String, Boolean>) {
        val deniedPermission = grantResults.filter { !it.value }.keys
        if (deniedPermission.isEmpty()) {
            onMultiplePermissionGranted?.invoke()
        } else {
            onMultiplePermissionDenied?.invoke(deniedPermission.toList())
        }
    }
}