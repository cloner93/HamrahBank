package com.pmb.facilities.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.BaseAppText
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.AppTypography
import com.pmb.facilities.R
import com.pmb.facilities.bill.domain.bill.entity.BillType

@Composable
fun ChooseSimTypeBottomSheet(
    items: List<String>,
    onItemClickListener: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = { onDismiss() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                BaseAppText(
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                        typography = AppTypography.buttonMedium
                    ),
                    title = stringResource(R.string.choose_sim_type)
                )
                Spacer(modifier = Modifier.size(32.dp))
                items.forEach { item ->
                    BodyMediumText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClickListener(item) }
                            .padding(vertical = 12.dp, horizontal = 12.dp),
                        text = item,
                        textAlign = TextAlign.Right
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        })
}
@Composable
fun ChooseBillTypeBottomSheet(
    items: List<BillType>,
    header: String,
    onItemClickListener: (BillType) -> Unit,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = { onDismiss() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                BaseAppText(
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                        typography = AppTypography.buttonMedium
                    ),
                    title = header
                )
                Spacer(modifier = Modifier.size(32.dp))
                items.forEach { item ->
                    BodyMediumText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClickListener(item) }
                            .padding(vertical = 12.dp, horizontal = 12.dp),
                        text = item.title,
                        textAlign = TextAlign.Right,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        })
}