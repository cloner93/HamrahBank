package com.pmb.data.appManager

import com.pmb.data.appManager.auth.AuthService
import com.pmb.data.appManager.card.CardService
import com.pmb.data.appManager.deposit.DepositService
import javax.inject.Inject

class AppManagerImpl @Inject constructor(
    private val authService: AuthService,
    private val cardService: CardService,
    private val depositService: DepositService
) : AppManager {
    override fun getAuthService(): AuthService {
        return authService
    }

    override fun getCardService(): CardService {
        return cardService
    }

    override fun getDepositService(): DepositService {
        return depositService
    }
}