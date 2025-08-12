package com.pmb.data.serviceProvider.remote.card

import com.pmb.core.platform.Result
import com.pmb.domain.model.Card
import com.pmb.domain.model.card.CardCustomerAddressRequest
import com.pmb.domain.model.card.CardCustomerAddressResponse
import com.pmb.domain.model.card.CardFetchPostCodeResponse
import com.pmb.domain.model.card.FetchCommissionForCreateCardResponse
import com.pmb.domain.model.card.RegisterCardRequest
import com.pmb.domain.model.card.RegisterCardResponse
import com.pmb.domain.model.card.ReturnCardChequeResponse
import com.pmb.domain.model.openAccount.FetchCardFormatResponse
import com.pmb.model.SuccessData
import kotlinx.coroutines.flow.Flow

interface CardService {
    fun getCardList(): Flow<Result<SuccessData<List<Card>>>>
    fun getReturnCheque(): Flow<Result<SuccessData<ReturnCardChequeResponse>>>
    fun getCustomerAddress(
        cardCustomerAddressRequest: CardCustomerAddressRequest
    ): Flow<Result<SuccessData<CardCustomerAddressResponse>>>

    fun fetchPostCodeCard(
        postalCode: Int
    ): Flow<Result<SuccessData<CardFetchPostCodeResponse>>>

    fun fetchCommissionForCreateCard(
        cardGroup: Int,
        accountNumber: Int
    ): Flow<Result<SuccessData<FetchCommissionForCreateCardResponse>>>

    fun fetchCardFormat(): Flow<Result<SuccessData<FetchCardFormatResponse>>>
    fun registerCard(registerCardRequest: RegisterCardRequest): Flow<Result<SuccessData<RegisterCardResponse>>>
}