package com.pmb.transfer.domain.repository

import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferConfirmEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.domain.param.AccountDetailParam
import com.pmb.transfer.domain.param.AccountFavoriteToggleParam
import com.pmb.transfer.domain.param.AccountRemoveFavoriteParam
import com.pmb.transfer.domain.use_case.TransferConfirmUseCase
import com.pmb.transfer.domain.use_case.TransferResendOtpUseCase
import com.pmb.transfer.domain.use_case.TransferSubmitOtpUseCase
import kotlinx.coroutines.flow.Flow

interface TransferRepository {
    suspend fun fetchAccountHistory(): Flow<Result<List<TransactionClientBankEntity>>>
    suspend fun fetchAccountFavorite(): Flow<Result<List<TransactionClientBankEntity>>>
    suspend fun fetchAccountDetail(params: AccountDetailParam): Flow<Result<TransactionClientBankEntity>>
    suspend fun removeAccountFavorite(params: AccountRemoveFavoriteParam): Flow<Result<List<TransactionClientBankEntity>>>
    suspend fun toggleAccountFavorite(params: AccountFavoriteToggleParam): Flow<Result<List<TransactionClientBankEntity>>>
    suspend fun searchAccounts(value: String): Flow<Result<List<TransactionClientBankEntity>>>
    suspend fun fetchTransferMethods(): Flow<Result<List<TransferMethodEntity>>>
    suspend fun transferConfirm(params: TransferConfirmUseCase.Params): Flow<Result<TransferConfirmEntity>>
    suspend fun transferResendOtp(params: TransferResendOtpUseCase.Params): Flow<Result<TransferConfirmEntity>>
    suspend fun transferSubmitOtp(params: TransferSubmitOtpUseCase.Params): Flow<Result<TransferReceiptEntity>>
    suspend fun fetchSourceCartBank(userId: String): Flow<Result<List<CardBankEntity>>>
    suspend fun fetchSourceAccountBank(userId: String): Flow<Result<List<AccountBankEntity>>>
    suspend fun fetchReasons(): Flow<Result<List<ReasonEntity>>>
}