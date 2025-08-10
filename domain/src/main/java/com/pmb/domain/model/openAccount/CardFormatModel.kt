package com.pmb.domain.model.openAccount

import android.net.Uri
import java.io.File

data class CardFormatModel(
    val incomeType: Int?,
    val actionNumber: Int?,
    val formatId: Int?,
    val cardGroup: Int?,
    val imageUris: File?,
    val backImageUris: File?,
    val formatDescription: String?,
    val commonAmount: Int?,
    val isHorizontal : Boolean?
)
