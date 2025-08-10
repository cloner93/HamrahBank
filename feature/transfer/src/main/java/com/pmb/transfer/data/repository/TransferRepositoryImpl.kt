package com.pmb.transfer.data.repository

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.transfer.data.model.body.DestinationInfoRequest
import com.pmb.transfer.data.model.body.TransferAmountRequest
import com.pmb.transfer.data.model.body.TransferReasonRequest
import com.pmb.transfer.data.model.body.TransferRequest
import com.pmb.transfer.data.source.local.Mock
import com.pmb.transfer.data.source.remote.TransferServiceImpl
import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.entity.AccountStatus
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.CardVerificationEntity
import com.pmb.transfer.domain.entity.ClientBankEntity
import com.pmb.transfer.domain.entity.PaymentType
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.domain.entity.ReceiptStatus
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferConfirmEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.domain.param.AccountDetailParam
import com.pmb.transfer.domain.param.AccountFavoriteToggleParam
import com.pmb.transfer.domain.param.AccountRemoveFavoriteParam
import com.pmb.transfer.domain.repository.TransferRepository
import com.pmb.transfer.domain.use_case.TransferAmountUseCase
import com.pmb.transfer.domain.use_case.TransferConfirmUseCase
import com.pmb.transfer.domain.use_case.TransferReasonUseCase
import com.pmb.transfer.domain.use_case.TransferResendVerifyCardInfoUseCase
import com.pmb.transfer.domain.use_case.TransferVerifyCardInfoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@Suppress("MISSING_DEPENDENCY_CLASS_IN_EXPRESSION_TYPE")
class TransferRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider,
    private val service: TransferServiceImpl,
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

    override suspend fun fetchAccountDetail(params: AccountDetailParam): Flow<Result<TransactionClientBankEntity>> {
        return service.getDestinationInfo(DestinationInfoRequest(destination = params.value))
            .mapApiResult {
                TransactionClientBankEntity(
                    clientBankEntity = ClientBankEntity(
                        name = it.second.destInfo ?: "",
                        cardNumber = params.value,
                        accountNumber = params.value,
                        iban = params.value,
                    ), type = params.type
                )
            }
    }

    override suspend fun removeAccountFavorite(params: AccountRemoveFavoriteParam): Flow<Result<List<TransactionClientBankEntity>>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(
                Result.Success(
                    transactionClientBankEntities.toMutableList().apply { remove(params.item) })
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

    override suspend fun fetchTransferMethods(): Flow<Result<List<TransferMethodEntity>>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(Result.Success(Mock.transferMethods))
    }

    override suspend fun transferConfirm(params: TransferConfirmUseCase.Params): Flow<Result<TransferConfirmEntity>> =
        service.transfer(
            TransferRequest(
                transferType = params.transferMethod.paymentType.id,
                source = params.sourceNumber,
                destination = params.destinationNumber,
                amount = params.amount.toLong(),
                payerId = params.depositId.trim().toLongOrNull() ?: 0L,
                customerId = params.customerId.toLong(),
                commissionFee = params.transferMethod.fee.toLong(),
                reasonRowNo = params.reasonId ?: 0,
                desc = params.desc ?: "",
                destInfo = params.destinationInfo,
                saveFavorite = params.favoriteDestination
            )
        ).mapApiResult {
            TransferConfirmEntity.ReceiptConfirm(
                receipt = TransferReceiptEntity(
                    amount = params.amount,
                    status = ReceiptStatus.SUCCESS,
                    paymentType = params.transferMethod.paymentType,
                    trackingNumber = it.second.transNo,
                )
            )
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
        remoteServiceProvider.getDepositService().getDepositList().mapApiResult {
            it.second.map { deposit ->
                AccountBankEntity(
                    id = deposit.accountId,
                    accountNumber = deposit.accountNumber.toString(),
                    accountHolderName = "",
                    accountType = deposit.accountTypeDescription ?: "",
                    accountHint = "",
                    accountBalance = deposit.balance.toDouble(),
                    accountStatus = AccountStatus.ACTIVE,
                    defaulted = true
                )
            }
        }

    override suspend fun fetchReasons(params: TransferReasonUseCase.Params): Flow<Result<List<ReasonEntity>>> =
        service.reasons(
            TransferReasonRequest(
                transferType = params.transferType, destination = params.destination
            )
        ).mapApiResult {
            it.second.map { response ->
                ReasonEntity(
                    id = response.rowNumber.toLong(),
                    title = response.description,
                    description = response.code
                )
            }
        }

    override suspend fun transferAmount(params: TransferAmountUseCase.Params): Flow<Result<List<TransferMethodEntity>>> =
        service.submitAmount(
            body = TransferAmountRequest(
                amount = params.amount.toLong(), destination = params.destination
            )
        ).mapApiResult {
            it.second.map {
                TransferMethodEntity(
                    title = it.transferTypeDesc,
                    detail = it.info,
                    fee = it.commissionFee.toDouble(),
                    active = it.enabled,
                    default = it.enabled,
                    paymentType = PaymentType.fromId(it.transferType)
                )
            }
        }


    private fun searchTransactionClientBanks(
        list: List<TransactionClientBankEntity>, query: String
    ): List<TransactionClientBankEntity> {
        return list.filter { entity ->
            entity.clientBankEntity.name.contains(query) || entity.clientBankEntity.cardNumber.contains(
                query
            ) || entity.clientBankEntity.accountNumber.contains(query) || entity.clientBankEntity.iban.contains(
                query
            )
        }
    }
}