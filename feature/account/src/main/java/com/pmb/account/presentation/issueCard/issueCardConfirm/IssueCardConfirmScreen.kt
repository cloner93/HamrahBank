package com.pmb.account.presentation.issueCard.issueCardConfirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.MenuItemDefaults.horizontalDividerPadding
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.core.utils.toCurrency

@Composable
fun IssueCardConfirmScreen() {
    val listOfData = listOf<Pair<String, String>>(
        "صاحب کارت" to "نیلوفر طائفی",
        "کد ملی" to "236234623654",
        "نوع کارت" to "معمولی",
        "نحوه تخصیص شماره کارت" to "شماره کارت قبلی",
        "حساب اصلی کارت" to "234523452",
        "نوع حساب اصلی" to "قرض\u200Cالحسنه پس\u200Cانداز کوتاه مدت",
        "تحویل کارت" to "تحویل در آدرس مشتری",
        "آدرس" to "تهران، کوی مطهری، خیابان ۲۷، پلاک ۱۵، واحد ۳",
        "کد پستی" to "123456789",
        "کسر کارمزد از حساب" to "123456789",
        "مجموع کارمزدها" to 5000000.0.toCurrency()
    )

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        footer = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    title = "تایید",
                    enable = true,
                    onClick = { })
            }
        },
        topBar = {
            AppTopBar(
                title = "مشاهده کارمزدها",
                startIcon = ClickableIcon(
                    icon = IconType.ImageVector(Icons.Default.ArrowForward),
                    onClick = {

                    })
            )
        }) {

        Spacer(modifier = Modifier.padding(24.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                listOfData.forEachIndexed { index, it ->

                    FeeRow(
                        title = it.first,
                        value = it.second,
                        bottomDivider = listOfData.size - 1 != index
                    )
                }
            }
        }
    }
}

@Composable
private fun FeeRow(
    title: String,
    value: String,
    bottomDivider: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CaptionText(
                text = title,
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )

            BodyMediumText(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = value,
                textAlign = TextAlign.End,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )

        }
        if (bottomDivider)
            HorizontalDivider(
                modifier = Modifier.padding(horizontalDividerPadding.toPaddingValues()),
                color = AppTheme.colorScheme.strokeNeutral3Devider
            )
    }

}

@AppPreview
@Composable
private fun IssueCardConfirmScreenPreview() {
    HamrahBankTheme { IssueCardConfirmScreen() }
}