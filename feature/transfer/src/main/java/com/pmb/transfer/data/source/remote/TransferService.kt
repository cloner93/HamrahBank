package com.pmb.transfer.data.source.remote

import com.pmb.core.platform.Result
import com.pmb.model.SuccessData
import com.pmb.transfer.data.model.DestinationInfoResponse
import com.pmb.transfer.data.model.TransferAmountResponse
import com.pmb.transfer.data.model.TransferTypeMellatResponse
import com.pmb.transfer.data.model.body.DestinationInfoRequest
import com.pmb.transfer.data.model.body.TransferAmountRequest
import com.pmb.transfer.data.model.body.TransferTypeMellatRequest
import kotlinx.coroutines.flow.Flow

interface TransferService {
    fun getDestinationInfo(body: DestinationInfoRequest): Flow<Result<SuccessData<DestinationInfoResponse>>>
    fun submitAmount(body: TransferAmountRequest): Flow<Result<SuccessData<List<TransferAmountResponse>>>>
    fun transferTypeMellat(body: TransferTypeMellatRequest): Flow<Result<SuccessData<TransferTypeMellatResponse>>>
}