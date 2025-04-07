package com.pmb.auth.presentation.scan_card_info.scan_card.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface ScanCardViewEvents : BaseViewEvent {
    data object SendScanCard : ScanCardViewEvents
}