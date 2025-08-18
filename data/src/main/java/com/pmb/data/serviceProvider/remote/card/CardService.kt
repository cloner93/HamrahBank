package com.pmb.data.serviceProvider.remote.card

import com.pmb.core.platform.Result
import com.pmb.domain.model.Card
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
import com.pmb.model.SuccessData
import kotlinx.coroutines.flow.Flow

interface CardService {
    fun getCardList(): Flow<Result<SuccessData<List<Card>>>>
    fun getReturnCheque(): Flow<Result<SuccessData<ReturnCardChequeResponse>>>
    fun getCustomerAddress(
        cardCustomerAddressRequest: CardCustomerAddressRequest
    ): Flow<Result<SuccessData<CardCustomerAddressResponse>>>
    fun getProvinceList(): Flow<Result<SuccessData<List<Province>>>>
    fun getCityList(params: FetchCityListParams): Flow<Result<SuccessData<List<City>>>>
    fun fetchPostCodeCard(
        postalCode: Long
    ): Flow<Result<SuccessData<CardFetchPostCodeResponse>>>

    fun fetchCommissionForCreateCard(
        cardGroup: Long,
        accountNumber: Long
    ): Flow<Result<SuccessData<FetchCommissionForCreateCardResponse>>>

    fun fetchCardFormat(): Flow<Result<SuccessData<List<FetchCardFormatResponse>>>>
    fun registerCard(registerCardRequest: RegisterCardRequest): Flow<Result<SuccessData<RegisterCardResponse>>>
}