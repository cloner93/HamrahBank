package com.pmb.account.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.ballon.component.CopyItem
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppOutlineButton
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size

@Composable
fun ShareDepositBottomSheet(info: DepositModel, onDismiss: () -> Unit) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = { onDismiss() },
        content = {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextImage(
                    image = R.drawable.bank_card_recieve,
                    text = stringResource(R.string.deposit_info),
                    imageStyle = ImageStyle(size = Size.FIX(80.dp))
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    CopyItem(title = stringResource(R.string.deposit_number),
                        value = info.depositNumber,
                        onClickCopy = {

                        })

                    Spacer(modifier = Modifier.height(24.dp))

                    CopyItem(title = stringResource(R.string.iban_number),
                        value = info.ibanNumber,
                        onClickCopy = {

                        })
                    Spacer(modifier = Modifier.height(24.dp))

                    CopyItem(title = stringResource(R.string.cart_number),
                        value = info.cardNumber,
                        onClickCopy = {

                        })
                }

                AppOutlineButton(modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.close),
                    onClick = {
                        isVisible = false
                    })
            }
        })
}
