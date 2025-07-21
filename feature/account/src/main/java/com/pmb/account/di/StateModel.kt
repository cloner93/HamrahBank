package com.pmb.account.di

import com.pmb.account.presentation.balance.viewmodel.BalanceViewState
import com.pmb.account.presentation.cards.viewmodel.CardsViewState
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewState
import com.pmb.account.presentation.transactionReceipt.viewmodel.TransactionReceiptViewState
import com.pmb.account.presentation.transactions.detailedTransactionLIst.DetailedTransactionListViewState
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.TransactionsFilterViewState
import com.pmb.account.presentation.transactions.search.viewmodel.TransactionSearchViewState
import com.pmb.account.presentation.transactions.statement.viewmodel.DepositStatementViewState
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StateModule {

    @Provides
    @Singleton
    fun provideDepositsViewState(): DepositsViewState = DepositsViewState()

    @Provides
    @Singleton
    fun provideBalanceViewState(): BalanceViewState = BalanceViewState()

    @Provides
    @Singleton
    fun provideTransactionsViewState(): TransactionsViewState = TransactionsViewState()

    @Provides
    @Singleton
    fun provideTransactionFilterViewState(): TransactionsFilterViewState =
        TransactionsFilterViewState()

    @Provides
    @Singleton
    fun provideDepositStatementViewState(): DepositStatementViewState = DepositStatementViewState()

    @Provides
    @Singleton
    fun provideTransactionSearchViewState(): TransactionSearchViewState =
        TransactionSearchViewState()

    @Provides
    @Singleton
    fun provideCardsViewState(): CardsViewState = CardsViewState()

    @Provides
    @Singleton
    fun provideTransactionReceiptViewState(): TransactionReceiptViewState =
        TransactionReceiptViewState()

    @Provides
    @Singleton
    fun provideDetailedTransactionListViewState(): DetailedTransactionListViewState =
        DetailedTransactionListViewState()

}