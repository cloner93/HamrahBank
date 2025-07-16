package com.pmb.data.serviceProvider.remote

import com.pmb.data.serviceProvider.remote.auth.AuthService
import com.pmb.data.serviceProvider.remote.card.CardService
import com.pmb.data.serviceProvider.remote.deposit.DepositService

interface RemoteServiceProvider {
    fun getAuthService(): AuthService
    fun getCardService(): CardService
    fun getDepositService(): DepositService
}