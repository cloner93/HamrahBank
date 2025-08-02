package com.pmb.auth.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppSearchTextField
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.domain.model.openAccount.accountType.Province
import com.pmb.domain.model.openAccount.accountVerifyCode.CityOfBirthInfoDTO
import com.pmb.domain.model.openAccount.cityName.City

@Composable
fun CitySearchComponent(
    searchQuery: String,
    cityList: List<CityOfBirthInfoDTO>?,
    searchHintText: String = "جستحوی شهر",
    onSearchResult: (String) -> Unit,
    onBackClick: () -> Unit,
    onCityClick: (CityOfBirthInfoDTO) -> Unit
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

        cityList?.forEach { cityName ->
            BodyMediumText(
                text = cityName.cityName ?: "",
                modifier = Modifier.clickable {
                    onCityClick(cityName)
                },
                color = AppTheme.colorScheme.onBackgroundNeutralDefault

            )
            Spacer(modifier = Modifier.size(16.dp))
        }

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

        cityList?.forEach { cityName ->
            BodyMediumText(
                text = cityName.provinceName ?: "",
                modifier = Modifier.clickable {
                    onCityClick(cityName)
                },
                color = AppTheme.colorScheme.onBackgroundNeutralDefault

            )
            Spacer(modifier = Modifier.size(16.dp))
        }

    }
}
@Composable
fun CityListSearchComponent(
    searchQuery: String,
    cityList: List<City>?,
    searchHintText: String = "جستحوی شهر",
    onSearchResult: (String) -> Unit,
    onBackClick: () -> Unit,
    onCityClick: (City) -> Unit
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

        cityList?.forEach { cityName ->
            BodyMediumText(
                text = cityName.cityName ?: "",
                modifier = Modifier.clickable {
                    onCityClick(cityName)
                },
                color = AppTheme.colorScheme.onBackgroundNeutralDefault

            )
            Spacer(modifier = Modifier.size(16.dp))
        }

    }
}