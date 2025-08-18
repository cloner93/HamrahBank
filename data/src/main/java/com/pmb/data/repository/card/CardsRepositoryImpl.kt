package com.pmb.data.repository.card

import com.pmb.core.platform.Result
import com.pmb.data.mapper.cardService.toDomain
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.domain.model.CardModel
import com.pmb.domain.model.card.CardCustomerAddressRequest
import com.pmb.domain.model.card.CardCustomerAddressResponse
import com.pmb.domain.model.card.CardFetchPostCodeResponse
import com.pmb.domain.model.card.City
import com.pmb.domain.model.card.FetchCommissionForCreateCardResponse
import com.pmb.domain.model.card.RegisterCardRequest
import com.pmb.domain.model.card.RegisterCardResponse
import com.pmb.domain.model.card.ReturnCardChequeResponse
import com.pmb.domain.model.openAccount.FetchCardFormatResponse
import com.pmb.domain.model.openAccount.accountType.Province
import com.pmb.domain.repository.card.CardsRepository
import com.pmb.domain.usecae.card.FetchCityListParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider
) : CardsRepository {
    override fun getCardList(): Flow<Result<List<CardModel>>> {
        return remoteServiceProvider.getCardService().getCardList().mapApiResult {
            it.second.toDomain()
        }
    }

    override fun getReturnCheque(): Flow<Result<ReturnCardChequeResponse>> {
        return remoteServiceProvider.getCardService().getReturnCheque().mapApiResult {
            it.second
        }
    }

    override fun getCustomerAddress(
        cardCustomerAddressRequest: CardCustomerAddressRequest
    ): Flow<Result<CardCustomerAddressResponse>> {
        return remoteServiceProvider.getCardService().getCustomerAddress(
            cardCustomerAddressRequest
        ).mapApiResult {
            it.second
        }
    }

    override fun getProvinceList(): Flow<Result<List<Province>>> {
        return remoteServiceProvider.getCardService().getProvinceList().mapApiResult {
            it.second
        }
    }

    override fun getCityList(params: FetchCityListParams): Flow<Result<List<City>>> {
        return remoteServiceProvider.getCardService().getCityList(params).mapApiResult {
            it.second
        }
    }

    override fun fetchPostCodeCard(postalCode: Long): Flow<Result<CardFetchPostCodeResponse>> {
        return remoteServiceProvider.getCardService().fetchPostCodeCard(postalCode).mapApiResult {
            it.second
        }
    }

    override fun fetchCommissionForCreateCard(
        cardGroup: Long,
        accountNumber: Long
    ): Flow<Result<FetchCommissionForCreateCardResponse>> {
        return remoteServiceProvider.getCardService().fetchCommissionForCreateCard(
            cardGroup = cardGroup,
            accountNumber = accountNumber
        ).mapApiResult {
            it.second
        }
    }

    override fun fetchCardFormat(): Flow<Result<List<FetchCardFormatResponse>>> {
        return remoteServiceProvider.getCardService().fetchCardFormat().mapApiResult {
            it.second
        }
    }

    override fun registerCard(registerCardRequest: RegisterCardRequest): Flow<Result<RegisterCardResponse>> {
        return remoteServiceProvider.getCardService().registerCard(registerCardRequest)
            .mapApiResult {
                it.second
            }
    }
}
