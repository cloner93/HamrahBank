package com.pmb.facilities.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.facilities.R

@Composable
fun PhoneNumberWithContactComponent(
    modifier: Modifier = Modifier,
    mobile: String,
    onValidate: (Boolean) -> Unit,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppMobileTextField(
            value = mobile,
            label = stringResource(R.string.mobile),
            onValidate = { onValidate(it) },
            onValueChange = { onValueChange(it) })
    }

}

@Composable
@Preview
fun PhoneNumberWithContactComponentPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
//        PhoneNumberWithContactComponent()
    }
}