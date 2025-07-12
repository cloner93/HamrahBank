package com.pmb.auth.domain.scan_card_info.card_confirmation.repository

import com.pmb.auth.domain.scan_card_info.card_confirmation.entity.CardInformationConfirmationEntity
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface CardInformationConfirmationRepository {
    fun getCardInformationData(): Flow<Result<CardInformationConfirmationEntity>>
    fun sendCardInformationConfirmation(): Flow<Result<Boolean>>
}