package com.pmb.core.composition

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.CoroutineScope

val LocalSnackbarHostState = staticCompositionLocalOf<SnackbarHostState> {
    error("SnackbarHostState not provided")
}

val LocalScreenScope = staticCompositionLocalOf<CoroutineScope> {
    error("No CoroutineScope provided")
}
