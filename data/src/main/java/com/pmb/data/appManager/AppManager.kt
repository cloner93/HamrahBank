package com.pmb.data.appManager

import com.pmb.data.appManager.auth.AuthService
import com.pmb.data.appManager.card.CardService
import com.pmb.data.appManager.persistence.PersistenceService
import com.pmb.data.appManager.deposit.DepositService

interface AppManager {
    fun getAuthService(): AuthService
    fun getCardService(): CardService
    fun getDepositService(): DepositService
    fun getDataStoreService(): PersistenceService
}