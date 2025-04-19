package com.pmb.transfer.data.repository

import com.pmb.core.platform.Result
import com.pmb.transfer.data.source.local.Mock
import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.CardVerificationEntity
import com.pmb.transfer.domain.entity.PaymentType
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferConfirmEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.domain.param.AccountDetailParam
import com.pmb.transfer.domain.param.AccountFavoriteToggleParam
import com.pmb.transfer.domain.param.AccountRemoveFavoriteParam
import com.pmb.transfer.domain.repository.TransferRepository
import com.pmb.transfer.domain.use_case.TransferConfirmUseCase
import com.pmb.transfer.domain.use_case.TransferResendVerifyCardInfoUseCase
import com.pmb.transfer.domain.use_case.TransferVerifyCardInfoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
//    private val api: TransferApi,
//    private val mapper: ClientBankMapper,
//    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TransferRepository {
    var transactionClientBankEntities = Mock.transactionClientBanksEntity
    override suspend fun fetchAccountHistory(): Flow<Result<List<TransactionClientBankEntity>>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(transactionClientBankEntities))
        }

    override suspend fun fetchAccountFavorite(): Flow<Result<List<TransactionClientBankEntity>>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(transactionClientBankEntities))
        }

    override suspend fun fetchAccountDetail(params: AccountDetailParam): Flow<Result<TransactionClientBankEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(transactionClientBankEntities[0]))
        }

    override suspend fun removeAccountFavorite(params: AccountRemoveFavoriteParam): Flow<Result<List<TransactionClientBankEntity>>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(
                Result.Success(
                    transactionClientBankEntities.toMutableList()
                        .apply { remove(params.item) })
            )
        }

    override suspend fun toggleAccountFavorite(params: AccountFavoriteToggleParam): Flow<Result<List<TransactionClientBankEntity>>> =
        flow {
            val index = transactionClientBankEntities.indexOf(params.item)
            transactionClientBankEntities = transactionClientBankEntities.toMutableList()
                .apply { set(index, params.item.copy(favorite = params.newStatus)) }
            emit(Result.Loading)
            delay(1000)
            emit(Result.Success(transactionClientBankEntities))
        }

    override suspend fun searchAccounts(value: String): Flow<Result<List<TransactionClientBankEntity>>> =
        flow {
            emit(Result.Loading)
            delay(1000)
            emit(Result.Success(searchTransactionClientBanks(transactionClientBankEntities, value)))
        }

    override suspend fun fetchTransferMethods(): Flow<Result<List<TransferMethodEntity>>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(Mock.transferMethods))
        }

    override suspend fun transferConfirm(params: TransferConfirmUseCase.Params): Flow<Result<TransferConfirmEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            when (params.transferMethod) {
                PaymentType.CARD_TO_CARD -> emit(
                    Result.Success(
                        TransferConfirmEntity.CardVerificationRequired(
                            Mock.cardVerificationEntity
                        )
                    )
                )

                PaymentType.INTERNAL_SATNA,
                PaymentType.INTERNAL_PAYA,
                PaymentType.INTERNAL_BRIDGE,
                PaymentType.MELLAT_TO_MELLAT -> emit(
                    Result.Success(
                        TransferConfirmEntity.ReceiptConfirm(
                            Mock.transferReceiptSussesEntity
                        )
                    )
                )
            }
        }

    override suspend fun transferResendVerifyCardInfo(params: TransferResendVerifyCardInfoUseCase.Params): Flow<Result<CardVerificationEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(Mock.cardVerificationEntity))
        }

    override suspend fun transferVerifyCardInfo(params: TransferVerifyCardInfoUseCase.Params): Flow<Result<TransferReceiptEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(Mock.transferReceiptSussesEntity))
        }

    override suspend fun fetchSourceCartBank(userId: String): Flow<Result<List<CardBankEntity>>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(Mock.fakeCardBanks))
        }

    override suspend fun fetchSourceAccountBank(userId: String): Flow<Result<List<AccountBankEntity>>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(Mock.fakeAccountBanks))
        }

    override suspend fun fetchReasons(): Flow<Result<List<ReasonEntity>>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(Mock.fakeReason))
        }


    private fun searchTransactionClientBanks(
        list: List<TransactionClientBankEntity>,
        query: String
    ): List<TransactionClientBankEntity> {
        return list.filter { entity ->
            entity.clientBankEntity.name.contains(query) ||
                    entity.clientBankEntity.cardNumber.contains(query) ||
                    entity.clientBankEntity.accountNumber.contains(query) ||
                    entity.clientBankEntity.iban.contains(query)
        }
    }
}