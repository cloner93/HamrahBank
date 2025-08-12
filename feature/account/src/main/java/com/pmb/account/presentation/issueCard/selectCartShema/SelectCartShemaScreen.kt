package com.pmb.account.presentation.issueCard.selectCartShema

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.cardComponent.CardFlip
import com.pmb.ballon.component.cardComponent.CardImage
import com.pmb.ballon.component.cardComponent.FlipToggle
import com.pmb.ballon.component.cardComponent.Orientation
import com.pmb.ballon.component.cardComponent.OrientationToggle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme

@Composable
fun SelectCartShemaScreen() {
    var orientationSelected by remember { mutableStateOf(Orientation.Horizontal) }
    var sideSelected by remember { mutableStateOf(false) }
    listOf(
        R.drawable.ic_horizontal_front_card,
        R.drawable.ic_horizontal_front_card,
        R.drawable.ic_horizontal_front_card
    )
    listOf(
        R.drawable.ic_front_card,
        R.drawable.ic_front_card,
        R.drawable.ic_front_card
    )

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.choose_card),
                onBack = {
                }
            )
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = true,
                title = stringResource(R.string.confirm),
                onClick = {
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = 16.dp))
        OrientationToggle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            orientationSelected
        ) {
            orientationSelected = it
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        FlipToggle {
            sideSelected = !sideSelected
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CardFlip(
                front = {
                    CardImage(
                        if (orientationSelected == Orientation.Horizontal)
                            R.drawable.ic_horizontal_front_card
                        else
                            R.drawable.ic_front_card, orientationSelected == Orientation.Horizontal
                    )
                },
                back = {
                    CardImage(
                        if (orientationSelected == Orientation.Horizontal) R.drawable.ic_horizontal_back_card else
                            R.drawable.ic_back_card, orientationSelected == Orientation.Horizontal
                    )
                },
                onClick = {
                    sideSelected = !sideSelected
                },
                isBack = sideSelected
            )
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                style = AppTheme.typography.bodySmall,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                text = "کد ۱۲۳ - کارمزد:  ۳۲٬۰۰۰ ریال"
            )
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))

    }
}

@AppPreview
@Composable
private fun SelectCartShemaScreenPreview() {
    HamrahBankTheme { SelectCartShemaScreen() }
}