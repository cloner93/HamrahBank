package com.pmb.facilities.complex_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.ImageItemRow
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.facilities.charge.domain.charge.entity.ChargeData
import com.pmb.facilities.component.HistoryInformationComponent

@Composable
fun HistoryListComponent(
    modifier: Modifier = Modifier,
    pageImage: Int,
    historyTitle: String,
    historyButtonTitle: String,
    items: List<ChargeData>,
    isInnerComponent: Boolean = false,
    onHistoryClickListener: () -> Unit,
    onItemClickListener: (String) -> Unit
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        AppImage(
            image = painterResource(pageImage),
            style = ImageStyle(size = Size.FIX(128.dp))
        )
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                HistoryInformationComponent(
                    title = historyTitle,
                    titleColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                    historyTitle = historyButtonTitle,
                    historyColor = AppTheme.colorScheme.onBackgroundNeutralCTA,
                    onHistoryClickListener = {
                        onHistoryClickListener.invoke()
                    }
                )
            }
            items(items) { item ->
                ImageItemRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 68.dp)
                        .then(
                            if (isInnerComponent) {
                                modifier.background(
                                    AppTheme.colorScheme.background1Neutral,
                                    RoundedCornerShape(12.dp)
                                )
                            } else
                                modifier
                        )
                        .padding(horizontal = 12.dp),
                    horizontalPadding = 12.dp,
                    title = item.title,
                    startImage = item.imageString,
                    titleStyle = TextStyle(
                        color = AppTheme.colorScheme.foregroundNeutralDefault,
                        typography = AppTheme.typography.bodyMedium
                    ),
                    subtitle = item.subTitle,
                    clickable = true,
                    onItemClick = {
                        onItemClickListener(item.subTitle)
                    }
                )
            }
        }
    }
}