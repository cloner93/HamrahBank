package com.pmb.auth.domain.activation.tax_details.useCase

import com.pmb.auth.domain.activation.tax_details.repository.ActivationTaxDetailsRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendActivationTaxDetailsUseCase @Inject constructor(
    private val activationTaxDetailsRepository: ActivationTaxDetailsRepository
) : BaseUseCase<String, Boolean>() {
    override suspend fun execute(params: String): Flow<Result<Boolean>> =
        activationTaxDetailsRepository.sendActivationTaxDetailsData(params)
}