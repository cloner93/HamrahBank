package com.pmb.ballon.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.R
import com.pmb.core.utils.UsernameValidationResult


@Composable
fun UsernameConditions(result: UsernameValidationResult) {
    val unchecked = R.drawable.ic_dash_circle_2
    val checked = R.drawable.ic_check_circle
    Column {
        TextIcon(
            title = stringResource(R.string.username_condition_word),
            icon = if (result.specialChar) checked else unchecked
        )
        TextIcon(
            title = stringResource(R.string.username_condition_start_with_letter),
            icon = if (result.startWithLetter) checked else unchecked
        )
        TextIcon(
            title = stringResource(R.string.username_condition_limit_length),
            icon = if (result.minLen && result.maxLen) checked else unchecked
        )
    }
}