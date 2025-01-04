package com.pmb.ballon.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.Headline6Text


@Composable
fun CopyItem(title: String, value: String, onClickCopy: () -> Unit) {
    Column(
        modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Headline6Text(text = title)
        Spacer(modifier = Modifier.height(8.dp))
        IconButtonWithText(
            text = value, icon = com.pmb.ballon.R.drawable.ic_copy, onClick = onClickCopy
        )
    }
}