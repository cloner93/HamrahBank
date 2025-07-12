package com.pmb.data.appManager

import com.pmb.data.appManager.auth.AuthService

interface AppManager {
    fun getAuthService(): AuthService
}