package com.pmb.auth.domain.ekyc.capture_video.useCase

import com.pmb.auth.domain.ekyc.capture_video.entity.CapturingVideoEntity
import com.pmb.auth.domain.ekyc.capture_video.entity.CapturingVideoParams
import com.pmb.auth.domain.ekyc.capture_video.repository.CapturingVideoRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendVideoUseCase @Inject constructor(
    private val capturingVideoRepository: CapturingVideoRepository
) : BaseUseCase<CapturingVideoParams, CapturingVideoEntity>() {
    override suspend fun execute(params: CapturingVideoParams): Flow<Result<CapturingVideoEntity>> =
        capturingVideoRepository.sendVideo(params)
}