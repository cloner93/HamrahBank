package com.pmb.profile.presentaion.personal_infos.change_address.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.profile.domain.entity.AddressEntity

sealed interface ChangeAddressViewEvents: BaseViewEvent {
    data class NavigateBackToPersonalInfo(val addressEntity: AddressEntity): ChangeAddressViewEvents
}