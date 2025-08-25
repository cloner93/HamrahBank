package com.pmb.shared

/**
 * Main entry point for the shared SDK
 * This class provides access to all shared functionality across platforms
 */
class SharedSDK {
    
    companion object {
        const val VERSION = "1.0.0"
        
        fun initialize() {
            // Initialize shared components
            println("Shared SDK initialized - Version: $VERSION")
        }
        
        fun getPlatformName(): String = when {
            isAndroid() -> "Android"
            isIOS() -> "iOS"
            isDesktop() -> "Desktop"
            else -> "Unknown"
        }
    }
}

// Platform detection functions - these will be implemented in platform-specific source sets
expect fun isAndroid(): Boolean
expect fun isIOS(): Boolean
expect fun isDesktop(): Boolean