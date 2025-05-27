package com.pmb.profile.presentaion.themeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.RoundedCornerCheckboxComponent
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.core.platform.ThemeMode
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.profile.R
import com.pmb.profile.presentaion.themeScreen.viewmodel.ThemeScreenViewActions
import com.pmb.profile.presentaion.themeScreen.viewmodel.ThemeScreenViewModel

@Composable
fun ThemeScreen(viewModel: ThemeScreenViewModel) {
    /**
    TODO checkList ThemeScreen.kt
     *
     * - use viewmodel to handle state, event and actions.
     * - store theme in dataStore.
     */

    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        topBar = {
            AppTopBar(
                title = "ظاهر برنامه",
                onBack = {
                    navigationManager.navigateBack()
                })
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
                    Column(
                        modifier = Modifier.clickable {
                            viewModel.handle(ThemeScreenViewActions.SelectTheme(ThemeMode.LIGHT))
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_light_theme),
                            contentDescription = ""
                        )
                        RoundedCornerCheckboxComponent(
                            modifier = Modifier
                                .padding(vertical = 12.dp, horizontal = 8.dp),
                            title = "روشن",
                            isChecked = viewState.themeMode == ThemeMode.LIGHT
                        ) { }
                    }
                    Column(modifier = Modifier.clickable {
                        viewModel.handle(ThemeScreenViewActions.SelectTheme(ThemeMode.DARK))
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_dark_theme),
                            contentDescription = ""
                        )

                        RoundedCornerCheckboxComponent(
                            modifier = Modifier
                                .padding(vertical = 12.dp, horizontal = 8.dp),
                            title = "تیره",
                            isChecked = viewState.themeMode == ThemeMode.DARK
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
                    isChecked = viewState.themeMode == ThemeMode.SYSTEM
                ) {
                    viewModel.handle(ThemeScreenViewActions.SelectTheme(ThemeMode.SYSTEM))
                }
            }
        }

    }
}

@AppPreview
@Composable
private fun ThemeScreenPreview() {
    HamrahBankTheme {
//        ThemeScreen()
    }

}