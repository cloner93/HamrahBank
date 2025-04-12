package com.pmb.home.domain.home.repository

import com.pmb.core.platform.Result
import com.pmb.home.domain.home.entity.HomeEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getHomeData(): Flow<Result<HomeEntity>>
}