package com.pmb.data.serviceProvider.remote

import com.pmb.data.serviceProvider.remote.auth.AuthService
import com.pmb.data.serviceProvider.remote.card.CardService
import com.pmb.data.serviceProvider.remote.deposit.DepositService
import com.pmb.data.serviceProvider.remote.transaction.TransactionService
import com.pmb.data.serviceProvider.remote.favorite.FavoriteService
import javax.inject.Inject

class RemoteServiceProviderImpl @Inject constructor(
    private val authService: AuthService,
    private val cardService: CardService,
    private val depositService: DepositService,
    private val transactionService: TransactionService,
    private val favoriteService: FavoriteService
) : RemoteServiceProvider {
    override fun getAuthService(): AuthService {
        return authService
    }

    override fun getCardService(): CardService {
        return cardService
    }

    override fun getDepositService(): DepositService {
        return depositService
    }

    override fun getTransactionService(): TransactionService {
        return transactionService
    }

    override fun getFavoriteService(): FavoriteService {
        return favoriteService
    }
}