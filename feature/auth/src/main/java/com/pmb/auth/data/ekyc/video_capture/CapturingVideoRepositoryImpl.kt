package com.pmb.auth.data.ekyc.video_capture

import com.pmb.auth.domain.ekyc.capture_video.entity.CapturingVideoEntity
import com.pmb.auth.domain.ekyc.capture_video.entity.CapturingVideoParams
import com.pmb.auth.domain.ekyc.capture_video.repository.CapturingVideoRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CapturingVideoRepositoryImpl @Inject constructor() : CapturingVideoRepository {
    override fun sendVideo(capturingVideoParams: CapturingVideoParams): Flow<Result<CapturingVideoEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            capturingVideoParams.uri.takeIf { !it.isNullOrEmpty() }?.let {
                emit(Result.Success(CapturingVideoEntity(isSuccess = true)))
            }?:run{
                emit(Result.Error(message = "Your video's address is not valid"))
            }
        }
}