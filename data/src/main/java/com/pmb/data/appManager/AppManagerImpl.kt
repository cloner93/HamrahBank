package com.pmb.data.appManager

import com.pmb.data.appManager.auth.AuthService
import com.pmb.data.appManager.card.CardService
import javax.inject.Inject

class AppManagerImpl @Inject constructor(
    private val authService: AuthService,
    private val cardService: CardService
) : AppManager {
    override fun getAuthService(): AuthService {
        return authService
    }

    override fun getCardService(): CardService {
        return cardService
    }
}