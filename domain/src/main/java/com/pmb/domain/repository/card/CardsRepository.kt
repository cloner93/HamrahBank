package com.pmb.domain.repository.card

import com.pmb.core.platform.Result
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
import com.pmb.domain.usecae.card.FetchCityListParams
import kotlinx.coroutines.flow.Flow

interface CardsRepository {
    fun getCardList(): Flow<Result<List<CardModel>>>
    fun getReturnCheque(): Flow<Result<ReturnCardChequeResponse>>
    fun getCustomerAddress(
        cardCustomerAddressRequest: CardCustomerAddressRequest
    ): Flow<Result<CardCustomerAddressResponse>>

    fun getProvinceList(): Flow<Result<List<Province>>>
    fun getCityList(params: FetchCityListParams): Flow<Result<List<City>>>
    fun fetchPostCodeCard(
        postalCode: Long
    ): Flow<Result<CardFetchPostCodeResponse>>

    fun fetchCommissionForCreateCard(
        cardGroup: Long,
        accountNumber: Long
    ): Flow<Result<FetchCommissionForCreateCardResponse>>

    fun fetchCardFormat(): Flow<Result<List<FetchCardFormatResponse>>>

    fun registerCard(registerCardRequest: RegisterCardRequest): Flow<Result<RegisterCardResponse>>
}
