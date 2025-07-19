package com.pmb.facilities.charge.domain.charge_history.entity

import androidx.compose.runtime.MutableState
import com.pmb.facilities.charge.domain.charge.entity.ChargeData

data class ChargeHistoryEntity(
    val isSuccess: Boolean,
    val data: List<ChargeData>
)

data class ChargeHistoryParams(
    val filter: String
)

data class ChargeHistoryFilterEntity(
    val isSelected: MutableState<Boolean>,
    val filterName: String
)

data class ChargeHistoryFilterListEntity(
    val isSuccess: Boolean,
    val chargeFilterList: List<ChargeHistoryFilterEntity>
)