package com.pmb.auth.domain.register.search_opening_branch.entity

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class OpeningBranchEntity(
    val isSuccess: Boolean,
    val openingBranch: List<OpeningBranch>
)

@Parcelize
data class OpeningBranch(
    val id: Int,
    val openingBranch: String,
    val openingBranchAddress: String,
    var isChecked: @RawValue MutableState<Boolean> = mutableStateOf(false),
) : Parcelable


data class OpeningBranchParams(
    val id: Int
)