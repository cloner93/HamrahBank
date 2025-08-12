package com.pmb.account.presentation.issueCard.issueCardIntro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppOutlineButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ButtonLargeText
import com.pmb.ballon.component.base.ButtonMediumText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme

@Composable
fun IssueCardIntroScreen() {
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
                    modifier = Modifier.fillMaxWidth(),
                    title = "همراه بانک",
                    onClick = { })

                Spacer(modifier = Modifier.height(8.dp))
                AppOutlineButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "دریافت نوبت حضوری",
                    onClick = { })
            }
        },
        topBar = {
            AppTopBar(
                title = "صدور کارت برای سپرده",
                startIcon = ClickableIcon(
                    icon = IconType.ImageVector(Icons.Default.ArrowForward),
                    onClick = {
//                        navigationManager.navigateBack()
                    })
            )
        }) {

        Spacer(modifier = Modifier.height(24.dp))
        BodyMediumText(
            text = "به دو صورت امکان ثبت درخواست صدور کارت برای سپرده وجود دارد.",
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ButtonLargeText(
                            text = "همراه بانک",
                            color = AppTheme.colorScheme.foregroundNeutralDefault
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(
                            modifier = Modifier,
                            color = AppTheme.colorScheme.strokeNeutral3Devider
                        )

                    }
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ButtonLargeText(
                            text = "مراجعه حضوری",
                            color = AppTheme.colorScheme.foregroundNeutralDefault
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(
                            modifier = Modifier,
                            color = AppTheme.colorScheme.strokeNeutral3Devider
                        )
                    }
                }
                Row {
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))
                        ButtonMediumText(
                            text = "ارسال بین ۲ تا ۷ روز",
                            color = AppTheme.colorScheme.onBackgroundPrimarySubdued,

                            )

                    }
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))
                        ButtonMediumText(
                            text = "صدور آنی",
                            color = AppTheme.colorScheme.onBackgroundPrimarySubdued
                        )

                    }
                }
                Row {
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))
                        ButtonMediumText(
                            text = "کارمزد صدور + هزینه ارسال",
                            color = AppTheme.colorScheme.onBackgroundPrimarySubdued
                        )

                    }
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))
                        ButtonMediumText(
                            text = "کارمزد صدور",
                            color = AppTheme.colorScheme.onBackgroundPrimarySubdued
                        )

                    }
                }
                Row {
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))
                        ButtonMediumText(
                            text = "ارسال به آدرس دلخواه",
                            color = AppTheme.colorScheme.onBackgroundPrimarySubdued
                        )

                    }
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))
                        ButtonMediumText(
                            text = "دریافت در شعبه",
                            color = AppTheme.colorScheme.onBackgroundPrimarySubdued
                        )

                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        BodyMediumText(
            text = "در صورتی که به کارت بانکی خود نیاز فوری دارید پیشنهاد می\u200Cشود از روش حضوری استفاده نمایید.",
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )

    }
}

@AppPreview
@Composable
private fun IssueCardIntroScreenPreview() {
    HamrahBankTheme {
        IssueCardIntroScreen()
    }
}