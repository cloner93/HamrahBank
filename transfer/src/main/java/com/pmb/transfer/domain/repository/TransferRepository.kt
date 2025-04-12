package com.pmb.transfer.domain.repository

import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferConfirmEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.param.AccountDetailParam
import com.pmb.transfer.domain.param.AccountFavoriteToggleParam
import com.pmb.transfer.domain.param.AccountRemoveFavoriteParam
import com.pmb.transfer.domain.use_case.TransferConfirmUseCase
import kotlinx.coroutines.flow.Flow

interface TransferRepository {
    suspend fun fetchAccountHistory(): Flow<Result<List<TransactionClientBankEntity>>>
    suspend fun fetchAccountFavorite(): Flow<Result<List<TransactionClientBankEntity>>>
    suspend fun fetchAccountDetail(params: AccountDetailParam): Flow<Result<TransactionClientBankEntity>>
    suspend fun removeAccountFavorite(params: AccountRemoveFavoriteParam): Flow<Result<List<TransactionClientBankEntity>>>
    suspend fun toggleAccountFavorite(params: AccountFavoriteToggleParam): Flow<Result<List<TransactionClientBankEntity>>>
    suspend fun searchAccounts(value: String): Flow<Result<List<TransactionClientBankEntity>>>
    suspend fun fetchTransferMethods(): Flow<Result<List<TransferMethodEntity>>>
    suspend fun transferConfirm(value: TransferConfirmUseCase.Params): Flow<Result<TransferConfirmEntity>>
}