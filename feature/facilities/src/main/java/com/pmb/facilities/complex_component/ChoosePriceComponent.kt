package com.pmb.facilities.complex_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ChipWithIcon
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency
import com.pmb.facilities.charge.domain.choose_charge_price.entity.ChoosePrice

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChoosePriceComponent(
    headerImage: Int,
    headerText: String,
    chosenText: String,
    items: List<ChoosePrice>,
    onItemClickCallBack:(ChoosePrice)-> Unit
) {
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        TextImage(
            image = headerImage,
            imageStyle = ImageStyle(size = Size.FIX(48.dp)),
            text = headerText,
            spacer = 0.dp,
            textStyle = TextStyle(
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                typography = AppTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.size(40.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            BodyMediumText(
                text = chosenText,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = Int.MAX_VALUE
        ) {
            items.forEach { item ->
                ChipWithIcon(
                    modifier = Modifier,
                    value = item.price.toCurrency(),
                    clickable = { onItemClickCallBack(item) },
                    color = if (item.isChecked.value) AppTheme.colorScheme.stateLayerNeutralPressed else Color.White,
                    assetColor = Color.Black,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                    fillMaxWidth = false
                )
            }
        }
    }
}