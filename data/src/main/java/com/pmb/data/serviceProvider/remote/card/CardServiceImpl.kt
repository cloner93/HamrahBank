package com.pmb.data.serviceProvider.remote.card

import com.pmb.core.platform.Result
import com.pmb.domain.model.Card
import com.pmb.domain.model.card.CardCustomerAddressRequest
import com.pmb.domain.model.card.CardCustomerAddressResponse
import com.pmb.domain.model.card.CardFetchPostCodeRequest
import com.pmb.domain.model.card.CardFetchPostCodeResponse
import com.pmb.domain.model.card.City
import com.pmb.domain.model.card.FetchCityListRequest
import com.pmb.domain.model.card.FetchCommissionForCreateCardRequest
import com.pmb.domain.model.card.FetchCommissionForCreateCardResponse
import com.pmb.domain.model.card.RegisterCardRequest
import com.pmb.domain.model.card.RegisterCardResponse
import com.pmb.domain.model.card.ReturnCardChequeResponse
import com.pmb.domain.model.openAccount.FetchCardFormatResponse
import com.pmb.domain.model.openAccount.accountType.Province
import com.pmb.domain.usecae.card.FetchCityListParams
import com.pmb.model.SuccessData
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardServiceImpl @Inject constructor(
    private val client: NetworkManger
) : CardService {
    override fun getCardList(): Flow<Result<SuccessData<List<Card>>>> {
        return client.request<Unit, List<Card>>(endpoint = "card/getUserCard")
    }

    override fun getReturnCheque(): Flow<Result<SuccessData<ReturnCardChequeResponse>>> {
        return client.request<Unit, ReturnCardChequeResponse>(endpoint = "card/getReturnCheque")
    }

    override fun getCustomerAddress(
        cardCustomerAddressRequest: CardCustomerAddressRequest
    ): Flow<Result<SuccessData<CardCustomerAddressResponse>>> {
        return client.request<CardCustomerAddressRequest, CardCustomerAddressResponse>(
            endpoint = "card/getCustomerAddress",
            cardCustomerAddressRequest
        )
    }

    override fun getProvinceList(): Flow<Result<SuccessData<List<Province>>>> {
        return client.request<Unit, List<Province>>(endpoint = "card/province")
    }

    override fun getCityList(params: FetchCityListParams): Flow<Result<SuccessData<List<City>>>> {
        val req = FetchCityListRequest(params.flag, params.provinceCode)
        return client.request<FetchCityListRequest, List<City>>(
            endpoint = "card/city",
            data = req
        )
    }

    override fun fetchPostCodeCard(postalCode: Long): Flow<Result<SuccessData<CardFetchPostCodeResponse>>> {
        val cardCustomerAddressRequest = CardFetchPostCodeRequest(
            postalCode = postalCode
        )
        return client.request<CardFetchPostCodeRequest, CardFetchPostCodeResponse>(
            endpoint = "card/fetchPostCode",
            cardCustomerAddressRequest
        )

    }

    override fun fetchCommissionForCreateCard(
        cardGroup: Long,
        accountNumber: Long
    ): Flow<Result<SuccessData<FetchCommissionForCreateCardResponse>>> {
        val fetchCommissionForCreateCardRequest = FetchCommissionForCreateCardRequest(
            cardGroup = cardGroup,
            accountNumber = accountNumber
        )
        return client.request<FetchCommissionForCreateCardRequest, FetchCommissionForCreateCardResponse>(
            endpoint = "card/fetchCommissionForCreate",
            fetchCommissionForCreateCardRequest

        )
    }

    override fun fetchCardFormat(): Flow<Result<SuccessData<List<FetchCardFormatResponse>>>> {
        return client.request<Unit, List<FetchCardFormatResponse>>(endpoint = "card/fetchCardFormat")
    }

    override fun registerCard(registerCardRequest: RegisterCardRequest): Flow<Result<SuccessData<RegisterCardResponse>>> {
        return client.request<RegisterCardRequest, RegisterCardResponse>(
            endpoint = "card/getRegisterCard",
            registerCardRequest
        )
    }

}