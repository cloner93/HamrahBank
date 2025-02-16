package com.pmb.auth.domain.ekyc.captureVideo.repository

import com.pmb.auth.domain.ekyc.captureVideo.entity.CapturingVideoEntity
import com.pmb.auth.domain.ekyc.captureVideo.entity.CapturingVideoParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface CapturingVideoRepository {
    fun sendVideo(capturingVideoParams: CapturingVideoParams): Flow<Result<CapturingVideoEntity>>
}