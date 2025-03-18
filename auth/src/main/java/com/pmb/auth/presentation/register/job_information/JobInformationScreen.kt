package com.pmb.auth.presentation.register.job_information

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.domain.register.job_information.entity.AnnualIncomingPrediction
import com.pmb.auth.domain.register.select_job_information.entity.JobInformation
import com.pmb.auth.presentation.AuthScreens
import com.pmb.auth.presentation.register.job_information.viewModel.JobInformationViewActions
import com.pmb.auth.presentation.register.job_information.viewModel.JobInformationViewEvents
import com.pmb.auth.presentation.register.job_information.viewModel.JobInformationViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.CustomSpinner
import com.pmb.ballon.component.base.AppBaseTextField
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.utils.CollectAsEffect

@Composable
fun JobInformationScreen(
    navigationManager: NavigationManager,
    viewModel: JobInformationViewModel
) {
    val viewState by viewModel.viewState.collectAsState()
    var annualIncome by remember {
        mutableStateOf<AnnualIncomingPrediction?>(null)
    }
    navigationManager.getCurrentScreenFlowData<JobInformation?>(
        "jobInformation",
        null
    )?.CollectAsEffect {
        it.takeIf {
            it != null
        }?.also {
            viewModel.handle(JobInformationViewActions.SetJobInformation(it))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                JobInformationViewEvents.SendJobInformationSucceed -> {
                    navigationManager.navigate(AuthScreens.CheckPostalCode)
                }
            }
        }
    }
    AppContent(modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.job_information),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        }, footer = {

            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = !viewState.isLoading && viewState.jobInformation != null,
                title = stringResource(R.string._continue),
                onClick = {
                    annualIncome?.id?.let {
                        viewModel.handle(
                            JobInformationViewActions.SendJonInformation(
                                it,
                                jobId = viewState.jobInformation?.id ?: -1
                            )
                        )
                    }
                })
        }) {
        viewState.data?.let {
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable(true) {

                    navigationManager.navigate(AuthScreens.SelectJobInformation)

                }) {
                AppBaseTextField(
                    value = viewState.jobInformation?.jobInformation ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = "انتخاب شغل",
                    enabled = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "down Icon",
                        )
                    },

                    )
            }
            Spacer(modifier = Modifier.size(24.dp))
            CustomSpinner(
                modifier = Modifier
                    .fillMaxWidth(),
                options = viewState.data?.annualIncomingPrediction?.map { it.income },
                labelString = "پیش بینی درآمد سالیانه",
                displayText = viewState.data?.annualIncomingPrediction?.findLast {
                    it.id == (annualIncome?.id ?: -1)
                }?.income ?: "",
                isEnabled = true
            ) { type ->
                viewState.data?.annualIncomingPrediction?.findLast { it.income == type }?.let {
                    annualIncome = it
                }
            }
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = false,
                title = stringResource(R.string.upload_document),
                onClick = {

                })
        }
    }
    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}