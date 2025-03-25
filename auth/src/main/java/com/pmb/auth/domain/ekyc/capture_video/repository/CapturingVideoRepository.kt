package com.pmb.auth.domain.ekyc.capture_video.repository

import com.pmb.auth.domain.ekyc.capture_video.entity.CapturingVideoEntity
import com.pmb.auth.domain.ekyc.capture_video.entity.CapturingVideoParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface CapturingVideoRepository {
    fun sendVideo(capturingVideoParams: CapturingVideoParams): Flow<Result<CapturingVideoEntity>>
}