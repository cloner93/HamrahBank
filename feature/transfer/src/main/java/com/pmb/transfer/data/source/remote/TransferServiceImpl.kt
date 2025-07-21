package com.pmb.transfer.data.source.remote

import com.pmb.core.platform.Result
import com.pmb.model.SuccessData
import com.pmb.network.NetworkManger
import com.pmb.transfer.data.model.DestinationInfoResponse
import com.pmb.transfer.data.model.TransferAmountResponse
import com.pmb.transfer.data.model.TransferTypeMellatResponse
import com.pmb.transfer.data.model.body.DestinationInfoRequest
import com.pmb.transfer.data.model.body.TransferAmountRequest
import com.pmb.transfer.data.model.body.TransferTypeMellatRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private object Endpoints {
    private const val BASE_PATH = "transfer"

    const val DESTINATION_INFO = "${BASE_PATH}/destination-info"
    const val SUGGEST_TYPE = "${BASE_PATH}/suggest-type"
    const val TRANSFER_MELLAT = "${BASE_PATH}/transfer-mellat"

}

class TransferServiceImpl @Inject constructor(
    private val client: NetworkManger
) : TransferService {

    override fun getDestinationInfo(body: DestinationInfoRequest): Flow<Result<SuccessData<DestinationInfoResponse>>> =
        client.request(
            endpoint = Endpoints.DESTINATION_INFO,
            data = body,
        )


    override fun submitAmount(body: TransferAmountRequest): Flow<Result<SuccessData<List<TransferAmountResponse>>>> =
        client.request(
            endpoint = Endpoints.SUGGEST_TYPE,
            data = body,
        )

    override fun transferTypeMellat(body: TransferTypeMellatRequest): Flow<Result<SuccessData<TransferTypeMellatResponse>>> =
        client.request(
            endpoint = Endpoints.TRANSFER_MELLAT,
            data = body,
        )

}