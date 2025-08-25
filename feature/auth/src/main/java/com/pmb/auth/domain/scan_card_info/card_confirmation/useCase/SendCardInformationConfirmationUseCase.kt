package com.pmb.auth.domain.scan_card_info.card_confirmation.useCase

import com.pmb.auth.domain.scan_card_info.card_confirmation.repository.CardInformationConfirmationRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendCardInformationConfirmationUseCase @Inject constructor(
    private val cardInformationConfirmationRepository: CardInformationConfirmationRepository
) : BaseUseCase<Unit, Boolean>() {
    override suspend fun execute(params: Unit): Flow<Result<Boolean>> =
        cardInformationConfirmationRepository.sendCardInformationConfirmation()
}