package com.pmb.auth.presentaion.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle

@Composable
fun ShowChangedNewPasswordBottomSheet(onDismiss: () -> Unit) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = { onDismiss() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextImage(
                    image = com.pmb.ballon.R.drawable.img_check_circle,
                    text = stringResource(R.string.msg_changed_new_password),
                    imageStyle = ImageStyle(size = Size.FIX(80.dp)),
                )
                Spacer(modifier = Modifier.size(24.dp))
                AppButton(modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.complete),
                    onClick = {
                        isVisible = false
                    })
            }
        })
}


@Preview
@Composable
private fun ShowChangedNewPasswordBottomSheetPreview() {
    ShowChangedNewPasswordBottomSheet {

    }
}