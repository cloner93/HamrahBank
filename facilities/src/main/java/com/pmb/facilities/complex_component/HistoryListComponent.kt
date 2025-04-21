package com.pmb.facilities.complex_component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.facilities.charge.presentation.charge.ChargeData
import com.pmb.facilities.component.HistoryInformationComponent
import com.pmb.facilities.component.HistoryRowComponent

@Composable
fun HistoryListComponent(
    modifier: Modifier = Modifier,
    pageImage: Int,
    historyTitle: String,
    historyButtonTitle: String,
    items: List<ChargeData>
) {
    Column(
        modifier =modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        AppImage(
            image = painterResource(pageImage),
            style = ImageStyle(size = Size.FIX(128.dp))
        )
        Spacer(modifier = Modifier.size(32.dp))
        LazyColumn {
            item {
                HistoryInformationComponent(
                    title = historyTitle,
                    titleColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                    historyTitle = historyButtonTitle,
                    historyColor = AppTheme.colorScheme.onBackgroundNeutralCTA
                )
            }
            item {
                Spacer(modifier = Modifier.size(12.dp))
            }
            items(items) { item ->
                HistoryRowComponent(
                    data = item
                )
            }
        }
    }
}