package com.pmb.auth.domain.activation.tax_details.repository

import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface ActivationTaxDetailsRepository {
    suspend fun sendActivationTaxDetailsData(accountNumber: String): Flow<Result<Boolean>>
}