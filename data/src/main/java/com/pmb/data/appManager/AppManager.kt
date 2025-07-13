package com.pmb.data.appManager

import com.pmb.data.appManager.auth.AuthService
import com.pmb.data.appManager.card.CardService

interface AppManager {
    fun getAuthService(): AuthService
    fun getCardService(): CardService
}