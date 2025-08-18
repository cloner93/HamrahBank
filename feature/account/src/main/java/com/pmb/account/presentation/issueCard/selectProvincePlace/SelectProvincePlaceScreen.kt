package com.pmb.account.presentation.issueCard.selectProvincePlace

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.account.presentation.issueCard.IssueCardSharedState
import com.pmb.ballon.component.EmptyList
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppSearchTextField
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.domain.model.openAccount.accountType.Province
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@Composable
fun SelectProvincePlaceScreen(
    sharedViewState: IssueCardSharedState,
    updateProvince: (Province) -> Unit
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    var searchQuery by remember { mutableStateOf("") }
    var filteredProvinceList by remember { mutableStateOf(sharedViewState.provinceList) }
    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }.debounce(300L).collectLatest {
            filteredProvinceList = if (it.isNotEmpty() && it.length >= 2) {
                sharedViewState.provinceList?.filter { province ->
                    province.provinceName?.contains(
                        it
                    ) == true
                }
            } else {
                sharedViewState.provinceList
            }
        }
    }
    ProvinceSearchComponent(
        searchQuery = searchQuery,
        cityList = filteredProvinceList,
        onSearchResult = {
            searchQuery = it
        },
        onBackClick = {
            navigationManager.navigateBack()
        },
    ) { province ->
        updateProvince(province)
        navigationManager.navigateBack()
    }
}

@Composable
fun ProvinceSearchComponent(
    searchQuery: String,
    cityList: List<Province>?,
    searchHintText: String = "جستحوی استان",
    onSearchResult: (String) -> Unit,
    onBackClick: () -> Unit,
    onCityClick: (Province) -> Unit
) {
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            Spacer(modifier = Modifier.size(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppButtonIcon(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(24.dp),
                    icon = Icons.Default.Close,
                    onClick = {
                        onBackClick()
                    }
                )
                AppSearchTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp, start = 16.dp),
                    hint = searchHintText,
                    query = searchQuery,
                    onValueChange = { onSearchResult(it) }
                )
            }
        },
        footer = {

        }) {

        Spacer(modifier = Modifier.size(24.dp))

        cityList?.takeIf { it.isNotEmpty() }?.forEach { cityName ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.backgroundTintNeutralDefault),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCityClick(cityName)
                        }
                ) {
                    BodyMediumText(
                        text = cityName.provinceName,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } ?: run {
            Spacer(modifier = Modifier.size(16.dp))
            EmptyList(
                modifier = Modifier.fillMaxSize(),
                iconType = IconType.Painter(painterResource(R.drawable.ic_not_found)),
                message = "نتیجه ای یافت نشد."
            )
        }
    }
}

@AppPreview
@Composable
private fun ProvinceSearchComponentPreview() {
    HamrahBankTheme {
        ProvinceSearchComponent(
            searchQuery = "",
            cityList = listOf(
            ),
            onSearchResult = {},
            onBackClick = {},
            onCityClick = {})
    }
}