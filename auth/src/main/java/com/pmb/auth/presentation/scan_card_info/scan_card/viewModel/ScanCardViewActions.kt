package com.pmb.auth.presentation.scan_card_info.scan_card.viewModel

import com.pmb.camera.platform.PhotoViewActions

sealed interface ScanCardViewActions : PhotoViewActions {
    data object ClearAlert : ScanCardViewActions
    data class SendScannedCard(val uri: String) : ScanCardViewActions
}