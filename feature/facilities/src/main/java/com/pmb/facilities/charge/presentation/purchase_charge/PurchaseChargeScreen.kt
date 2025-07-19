package com.pmb.facilities.charge.presentation.purchase_charge

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.core.utils.fetchContactPhoneNumber
import com.pmb.facilities.R
import com.pmb.facilities.charge.presentation.ChargeSharedState
import com.pmb.facilities.complex_component.ChoosePhoneNumberComponent
import com.pmb.facilities.utils.SimOperatorDetector.normalizeIranPhone
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.ChargeScreens

@Composable
fun PurchaseChargeScreen(
    sharedState: State<ChargeSharedState>,
    viewModel: PurchaseChargeViewModel,
    updateState: (PurchaseChargeViewState) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.onSinglePermissionResult(isGranted)
    }
    val contactPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickContact()
    ) { uri: Uri? ->
        context.fetchContactPhoneNumber(uri)?.let {
            viewModel.handle(PurchaseChargeViewActions.SetMobileNumber(it.replace(" ", "").normalizeIranPhone()))
            viewModel.handle(PurchaseChargeViewActions.SetMobileOperator)
        }
    }
    LaunchedEffect(Unit) {

        viewModel.viewEvent.collect { event ->
            when (event) {
                is PurchaseChargeViewEvents.OpenContactList -> {
                    contactPickerLauncher.launch(null)
                }
            }
        }
    }
    var isMobile by remember { mutableStateOf(false) }
    LaunchedEffect(isMobile) {
        if (isMobile)
            viewModel.handle(PurchaseChargeViewActions.SetMobileOperator)
        else
            viewModel.handle(PurchaseChargeViewActions.ClearMobileOperator)
    }
    AppContent(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBar = {
            AppTopBar(title = stringResource(R.string.buy_charge), onBack = {
                navigationManager.navigateBack()
            })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp, vertical = 17.dp
                    ),
                title = stringResource(com.pmb.ballon.R.string._continue),
                enable = true,
                onClick = {
                    updateState.invoke(viewState.copy())
                    navigationManager.navigate(ChargeScreens.ChooseChargePrice)
                })
        }) {
        ChoosePhoneNumberComponent(
            modifier = Modifier.fillMaxWidth(),
            mobile = viewState.mobile,
            item = viewState.operatorData,
            onValidate = {
                isMobile = it
            },
            onValueChange = {
                viewModel.handle(PurchaseChargeViewActions.SetMobileNumber(it))

            }
        ) {
            viewModel.handle(PurchaseChargeViewActions.RequestContactPermission(permissionLauncher))
        }
    }
}
