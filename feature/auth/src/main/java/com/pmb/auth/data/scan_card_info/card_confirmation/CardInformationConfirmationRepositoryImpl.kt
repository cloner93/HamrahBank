package com.pmb.auth.data.scan_card_info.card_confirmation

import com.pmb.auth.domain.scan_card_info.card_confirmation.entity.CardInformationConfirmationEntity
import com.pmb.auth.domain.scan_card_info.card_confirmation.repository.CardInformationConfirmationRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CardInformationConfirmationRepositoryImpl @Inject constructor() :
    CardInformationConfirmationRepository {
    override fun getCardInformationData(): Flow<Result<CardInformationConfirmationEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(
            Result.Success(
                CardInformationConfirmationEntity(
                    cardNumber = "۱۲۳۴ ۵۶۷۸ ۴۵۶۷ ۳۴۲۱",
                    cardOwnerFamily = "مودت",
                    cardOwnerName = "مشتاق",
                    cvv2 = "۳۴۵",
                    expirationDate = "۱۴۰۴/۱۰"
                )
            )
        )
    }

    override fun sendCardInformationConfirmation(): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(
            Result.Success(
                true
            )
        )
    }
}