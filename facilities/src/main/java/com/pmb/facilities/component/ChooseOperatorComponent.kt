package com.pmb.facilities.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.facilities.charge.presentation.buying_charge.ChooseOperator

@Composable
fun ChooseOperatorComponent(
    modifier: Modifier = Modifier,
    data: ChooseOperator
) {
    Row(
        modifier = modifier.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppImage(
            image = data.operatorImage,
            style = ImageStyle(size = Size.Rectangle(52.dp, 40.dp))
        )
        Spacer(modifier = Modifier.size(8.dp))
        BodyMediumText(
            text = data.operator,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
        )
        if (data.isChecked)
            AppImage(image = painterResource(R.drawable.ic_checked))
    }
}