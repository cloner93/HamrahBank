package com.pmb.facilities.complex_component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.facilities.charge.domain.purchase_charge.entity.Operator
import com.pmb.facilities.component.ChooseListOperatorComponent
import com.pmb.facilities.component.PhoneNumberWithContactComponent

@Composable
fun ChoosePhoneNumberComponent(
    modifier: Modifier = Modifier,
    mobile: String,
    item: List<Operator>?,
    onValidate: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
    onContactClickListener: () -> Unit
) {
    PhoneNumberWithContactComponent(
        modifier = modifier,
        mobile = mobile,
        onValidate = {
            onValidate(it)
        },
        onValueChange = {
            onValueChange(it)
        }
    ) {
        onContactClickListener()
    }
    Spacer(modifier = Modifier.size(40.dp))
    item?.let {
        ChooseListOperatorComponent(
            modifier = Modifier.fillMaxWidth(),
            chooseOperator = it
        )
    }
}

