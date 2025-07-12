package com.pmb.data.appManager

import com.pmb.data.appManager.auth.AuthService
import javax.inject.Inject

class AppManagerImpl @Inject constructor(
    private val authService: AuthService
) : AppManager {
    override fun getAuthService(): AuthService {
        return authService
    }
}