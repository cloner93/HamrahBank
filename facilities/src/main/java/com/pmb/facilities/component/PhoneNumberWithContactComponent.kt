package com.pmb.facilities.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.Size
import com.pmb.facilities.R

@Composable
fun PhoneNumberWithContactComponent(
    modifier: Modifier = Modifier,
    mobile: String,
    onValidate: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
    onContactClickListener: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppMobileTextField(
            modifier = Modifier.weight(0.5f),
            value = mobile,
            label = stringResource(R.string.mobile),
            onValidate = { onValidate(it) },
            onValueChange = { onValueChange(it) })
        AppButtonIcon(
            icon = painterResource(com.pmb.ballon.R.drawable.ic_contact),
            style = IconStyle(size = Size.FIX(24.dp))
        ) {
            onContactClickListener()
        }

    }

}

@Composable
@Preview
fun PhoneNumberWithContactComponentPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
//        PhoneNumberWithContactComponent()
    }
}