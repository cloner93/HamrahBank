package com.pmb.profile.presentaion.comments_suggestions

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppMultiTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.profile.R
import com.pmb.profile.presentaion.comments_suggestions.viewmodel.CommentsSuggestionsViewActions
import com.pmb.profile.presentaion.comments_suggestions.viewmodel.CommentsSuggestionsViewEvents
import com.pmb.profile.presentaion.comments_suggestions.viewmodel.CommentsSuggestionsViewModel

@Composable
fun CommentsSuggestionsScreen(viewModel: CommentsSuggestionsViewModel) {
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                CommentsSuggestionsViewEvents.NavigateBack -> {
                    navigationManager.navigateBack()
                }
            }
        }
    }

    AppContent(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.comments_and_suggestions),
                onBack = {
                    navigationManager.navigateBack()
                })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = stringResource(if (viewState.submitted) R.string.back_to_profile else R.string.registration),
                enable = viewState.buttonEnabled
            ) {
                viewModel.handle(CommentsSuggestionsViewActions.Submit)
            }

        }
    ) {
        if (viewState.submitted) SubmittedCommentsSuggestions()
        else {
            BodyMediumText(
                text = stringResource(R.string.comments_suggestions_header),
                color = AppTheme.colorScheme.onBackgroundPrimarySubdued
            )
            Spacer(modifier = Modifier.size(24.dp))
            AppMultiTextField(
                value = viewState.comment,
                label = stringResource(R.string.comment_text),
                minLines = 3,
                onValueChange = {
                    viewModel.handle(CommentsSuggestionsViewActions.UpdateComment(it))
                }
            )
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}

@Composable
private fun SubmittedCommentsSuggestions() {
    Spacer(modifier = Modifier.size(24.dp))
    TextImage(
        image = com.pmb.ballon.R.drawable.img_check_circle,
        text = "دیدگاه شما با موفقیت ثبت شد",
        imageStyle = ImageStyle(size = Size.FIX(80.dp)),
    )
    Spacer(modifier = Modifier.size(12.dp))
    BodyMediumText(
        text = "از اینکه در ارائه خدمات بهتر ما را همراهی می\u200Cکنید، سپاسگزاریم!",
        color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
        textAlign = TextAlign.Center
    )
}