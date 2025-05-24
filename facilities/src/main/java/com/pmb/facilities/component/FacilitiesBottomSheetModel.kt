package com.pmb.facilities.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AmountText
import com.pmb.ballon.component.RoundedCornerCheckboxComponent
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.BaseAppText
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline5Text
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.AppTypography
import com.pmb.core.utils.toCurrency
import com.pmb.facilities.R
import com.pmb.facilities.bill.domain.bill.entity.BillType
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdEntity
import com.pmb.facilities.bill.domain.bill_id.entity.TeleCommunicationEntity

@Composable
fun ChooseSimTypeBottomSheet(
    items: List<String>,
    onItemClickListener: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = { onDismiss() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                BaseAppText(
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                        typography = AppTypography.buttonMedium
                    ),
                    title = stringResource(R.string.choose_sim_type)
                )
                Spacer(modifier = Modifier.size(32.dp))
                items.forEach { item ->
                    BodyMediumText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClickListener(item) }
                            .padding(vertical = 12.dp, horizontal = 12.dp),
                        text = item,
                        textAlign = TextAlign.Right,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        })
}

@Composable
fun ChooseBillTypeBottomSheet(
    items: List<BillType>,
    header: String,
    onItemClickListener: (BillType) -> Unit,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = { onDismiss() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                BaseAppText(
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                        typography = AppTypography.buttonMedium
                    ),
                    title = header
                )
                Spacer(modifier = Modifier.size(32.dp))
                items.forEach { item ->
                    BodyMediumText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClickListener(item) }
                            .padding(vertical = 12.dp, horizontal = 12.dp),
                        text = item.title,
                        textAlign = TextAlign.Right,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        })
}

@Composable
fun PurchaseBillBottomSheet(
    bottomSheetData: BillIdEntity,
    onPurchaseClickListener: () -> Unit,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = {
            onDismiss.invoke()
        }, cancelable = true
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(32.dp))
            AppImage(
                image = bottomSheetData.billImage,
                style = ImageStyle(size = Size.FIX(44.dp))
            )
            Spacer(modifier = Modifier.size(12.dp))
            BodySmallText(
                text = bottomSheetData.billTitle,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
            CaptionText(
                text = bottomSheetData.billId,
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
            Spacer(modifier = Modifier.size(12.dp))
            Headline5Text(
                text = "${bottomSheetData.billPrice.toCurrency()} ${stringResource(com.pmb.ballon.R.string.rial)}",
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
            CaptionText(
                text = bottomSheetData.billPriceTitle,
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
            Spacer(modifier = Modifier.size(32.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    BodySmallText(
                        text = stringResource(R.string.customer_name),
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                    BodySmallText(
                        text = bottomSheetData.billDetails?.billCustomer ?: "",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Left,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    BodySmallText(
                        text = stringResource(R.string.address),
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                    BodySmallText(
                        text = bottomSheetData.billDetails?.address ?: "",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Left,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    BodySmallText(
                        text = stringResource(R.string.expire_date),
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                    BodySmallText(
                        text = bottomSheetData.billDetails?.expireDate ?: "",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Left,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                }
            }
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp, vertical = 17.dp
                    ),
                title = stringResource(com.pmb.ballon.R.string.purchase),
                enable = true,
                onClick = {
                    onDismiss()
                    onPurchaseClickListener()
                })
        }
    }
}

@Composable
fun TeleComBillBottomSheet(
    bottomSheetData: TeleCommunicationEntity,
    onPurchaseClickListener: () -> Unit,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    var checkedId by remember { mutableStateOf(-1) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = {
            onDismiss.invoke()
        }, cancelable = true
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(32.dp))
            AppImage(
                image = bottomSheetData.billImage,
                style = ImageStyle(size = Size.FIX(44.dp))
            )
            Spacer(modifier = Modifier.size(12.dp))
            BodySmallText(
                text = bottomSheetData.billTitle,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
            CaptionText(
                text = bottomSheetData.phoneNumber,
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                bottomSheetData.teleCommunicationDetails.forEach { item ->
                    var isChecked = checkedId == item.id
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp)
                    ) {
                        RoundedCornerCheckboxComponent(
                            modifier = Modifier.weight(1f),
                            title = item.title,
                            isChecked = isChecked
                        ) {
                            if (it)
                                checkedId = item.id
                            else
                                checkedId = item.id

                        }
                        AmountText(
                            amount = item.price,
                            currency = stringResource(com.pmb.ballon.R.string.rial),
                            amountStyle = TextStyle(
                                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                                typography = AppTheme.typography.bodyMedium,
                            ),
                            currencyStyle = TextStyle(
                                color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                                typography = AppTheme.typography.caption,
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(32.dp))
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp, vertical = 17.dp
                    ),
                title = stringResource(com.pmb.ballon.R.string.purchase),
                enable = true,
                onClick = {
                    onDismiss()
                    onPurchaseClickListener()
                })
        }
    }
}