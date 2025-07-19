package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.AddressEntity
import com.pmb.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangeAddressUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<ChangeAddressUseCase.Param, AddressEntity>() {
    override suspend fun execute(params: Param): Flow<Result<AddressEntity>> =
        profileRepository.changeAddress(params.id, params.postalCode)


    data class Param(val id: Long, val postalCode: String)
}