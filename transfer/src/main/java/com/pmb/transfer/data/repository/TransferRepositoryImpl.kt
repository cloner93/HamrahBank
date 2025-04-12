package com.pmb.transfer.data.repository

import com.pmb.core.platform.Result
import com.pmb.transfer.data.source.local.Mock
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferConfirmEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.param.AccountDetailParam
import com.pmb.transfer.domain.param.AccountFavoriteToggleParam
import com.pmb.transfer.domain.param.AccountRemoveFavoriteParam
import com.pmb.transfer.domain.repository.TransferRepository
import com.pmb.transfer.domain.use_case.TransferConfirmUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
//    private val api: TransferApi,
//    private val mapper: ClientBankMapper,
//    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TransferRepository {
    var transactionClientBankEntities = Mock.transactionClientBanksEntityEntities
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

    override suspend fun transferConfirm(value: TransferConfirmUseCase.Params): Flow<Result<TransferConfirmEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(TransferConfirmEntity(id = "1234", duration = 120)))
        }


    private fun searchTransactionClientBanks(
        list: List<TransactionClientBankEntity>,
        query: String
    ): List<TransactionClientBankEntity> {
        return list.filter { entity ->
            entity.clientBankEntity.name.contains(query) ||
                    entity.clientBankEntity.cardNumber.toString().contains(query) ||
                    entity.clientBankEntity.accountNumber.contains(query) ||
                    entity.clientBankEntity.iban.contains(query)
        }
    }
}