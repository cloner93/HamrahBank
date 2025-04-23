package com.pmb.profile.presentaion.themeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.RoundedCornerCheckboxComponent
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.profile.R

@Composable
fun ThemeScreen() {

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        topBar = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                AppButtonIcon(
                    modifier = Modifier
                        .align(Alignment.Companion.TopStart),
                    icon = Icons.Outlined.ArrowForward,
                    style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralDefault),
                    onClick = {

                    })
            }
        },
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                BodyMediumText(
                    text = "نوع تراکنش",
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )
                Row(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.ic_light_theme),
                            contentDescription = ""
                        )
                        RoundedCornerCheckboxComponent(
                            modifier = Modifier
                                .padding(vertical = 12.dp, horizontal = 8.dp),
                            title = "روشن",
                            isChecked = true
                        ) { }
                    }
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.ic_dark_theme),
                            contentDescription = ""
                        )

                        RoundedCornerCheckboxComponent(
                            modifier = Modifier
                                .padding(vertical = 12.dp, horizontal = 8.dp),
                            title = "تیره",
                            isChecked = false
                        ) { }
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = AppTheme.colorScheme.strokeNeutral3Devider
                )

                RoundedCornerCheckboxComponent(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                    title = "مطابق دستگاه",
                    isChecked = false
                ) { }
            }
        }

    }
}

@AppPreview
@Composable
private fun ThemeScreenPreview() {
    HamrahBankTheme {
        ThemeScreen()
    }

}