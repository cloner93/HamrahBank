package com.pmb.account.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.AppTypography
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.domain.model.DepositModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareDepositBottomSheet(
    content: @Composable ColumnScope.(NestedScrollConnection) -> Unit, onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = { onDismiss() },
        dragHandle = { BottomSheetDefaults.DragHandle() },
        cancelable = true,
        content = content
    )
}

@Composable
fun ShareDepositBottomSheetContent(
    modifier: Modifier = Modifier,
    info: DepositModel,
    onCopyAllClick: (String) -> Unit,
    onShareClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = AppTheme.colorScheme.background1Neutral)
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextImage(
            image = IconType.Painter(painterResource(R.drawable.card_info)),
            text = stringResource(R.string.deposit_info),
            imageStyle = ImageStyle(size = Size.FIX(80.dp)),
            textStyle = TextStyle(
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                typography = AppTypography.headline6
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = modifier.padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Headline6Text(
                        text = stringResource(R.string.deposit_number),
                        color = AppTheme.colorScheme.foregroundNeutralDefault
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CaptionText(
                        text = info.depositNumber,
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                }
                Row {

                    AppIcon(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { onCopyAllClick(info.depositNumber) },
                        icon = painterResource(com.pmb.ballon.R.drawable.ic_copy),
                        style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued)
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    AppIcon(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { onShareClick(info.depositNumber) },
                        icon = painterResource(com.pmb.ballon.R.drawable.ic_outline_share),
                        style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued)
                    )
                }
            }
            if (info.cardNumber.isNotEmpty()){
                Row(
                    modifier = modifier.padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Headline6Text(
                            text = stringResource(R.string.cart_number),
                            color = AppTheme.colorScheme.foregroundNeutralDefault
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CaptionText(
                            text = info.cardNumber,
                            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                        )
                    }
                    Row {

                        AppIcon(
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { onCopyAllClick(info.cardNumber) },
                            icon = painterResource(com.pmb.ballon.R.drawable.ic_copy),
                            style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued)
                        )
                        Spacer(modifier = modifier.width(4.dp))
                        AppIcon(
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { onShareClick(info.cardNumber) },
                            icon = painterResource(com.pmb.ballon.R.drawable.ic_outline_share),
                            style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued)
                        )
                    }
                }
            }
            Row(
                modifier = modifier.padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Headline6Text(
                        text = stringResource(R.string.iban_number),
                        color = AppTheme.colorScheme.foregroundNeutralDefault
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CaptionText(
                        text = info.ibanNumber,
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                }
                Row {

                    AppIcon(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { onCopyAllClick(info.ibanNumber) },
                        icon = painterResource(com.pmb.ballon.R.drawable.ic_copy),
                        style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued)
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    AppIcon(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { onShareClick(info.ibanNumber) },
                        icon = painterResource(com.pmb.ballon.R.drawable.ic_outline_share),
                        style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val allData = StringBuilder()
                allData.append(stringResource(R.string.deposit_number) + ": " + info.depositNumber)
                allData.append("\n")
                if (info.cardNumber.isNotEmpty()){
                    allData.append(stringResource(R.string.cart_number) + ": " + info.cardNumber)
                    allData.append("\n")
                }
                allData.append(stringResource(R.string.iban_number) + ": " + info.ibanNumber)

                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .weight(1F)
                        .clickable { onCopyAllClick(allData.toString()) },
                    colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.backgroundTintNeutralDefault),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(com.pmb.ballon.R.drawable.ic_copy_filled),
                            tint = AppTheme.colorScheme.onBackgroundTintNeutralDefault,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        BodySmallText(
                            text = stringResource(R.string.copy_all_data),
                            color = AppTheme.colorScheme.onBackgroundTintNeutralDefault,
                        )
                    }
                }

                Spacer(modifier = Modifier.width(13.dp))

                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .weight(1F)
                        .clickable { onShareClick(allData.toString()) },
                    colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.backgroundTintNeutralDefault),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(com.pmb.ballon.R.drawable.ic_share),
                            tint = AppTheme.colorScheme.onBackgroundTintNeutralDefault,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        BodySmallText(
                            text = stringResource(R.string.share_all_data),
                            color = AppTheme.colorScheme.onBackgroundTintNeutralDefault,
                        )
                    }
                }
            }
        }
    }
}

@AppPreview
@Composable
private fun ContentPreview() {
    val dip = DepositModel(
        title = "حساب قرض الحسنه آقای مشتاق مودت",
        desc = "تنخواه",
        depositNumber = "123456",
        amount = 10000023400.0,
        currency = stringResource(com.pmb.ballon.R.string.real_carrency),
        ibanNumber = "IR1234567890098765432112",
        cardNumber = "6219861920241234",
        categoryCode = 0,
    )
    HamrahBankTheme {
        ShareDepositBottomSheetContent(info = dip, onCopyAllClick = {}, onShareClick = {})
    }
}