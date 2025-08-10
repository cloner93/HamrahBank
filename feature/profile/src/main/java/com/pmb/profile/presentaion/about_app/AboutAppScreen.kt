package com.pmb.profile.presentaion.about_app

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.actionCall
import com.pmb.core.utils.openWebPage
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.profile.R

@Composable
fun AboutAppScreen() {
    val navigationManager = LocalNavigationManager.current
    val context = LocalContext.current

    AppContent(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.about_app),
                onBack = {
                    navigationManager.navigateBack()
                })
        }
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        Headline6Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "همراه بانک ملت، همراه شما در هر لحظه",
            color = AppTheme.colorScheme.onBackgroundPrimaryDefault,
        )
        Spacer(modifier = Modifier.size(24.dp))
        BodySmallText(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "بانک ملت با سابقه\u200Cای بیش از چهار دهه، به\u200Cعنوان یکی از بزرگ\u200Cترین بانک\u200Cهای کشور، متعهد به ارائه خدمات بانکی نوآورانه و مشتری\u200Cمحور است.",
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
        )
        Spacer(modifier = Modifier.size(16.dp))
        BodySmallText(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "همراه بانک ملت، طراحی شده است تا خدمات بانکی را سریع، امن، و در دسترس شما قرار دهد. با این اپلیکیشن، می\u200Cتوانید تمامی امور مالی خود را بدون نیاز به مراجعه حضوری انجام دهید. هدف ما ایجاد تجربه\u200Cای مطمئن و ساده برای مدیریت حساب\u200Cهای بانکی شماست.",
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
        )
        Spacer(modifier = Modifier.size(40.dp))
        Headline6Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "اطلاعات تماس",
            color = AppTheme.colorScheme.onBackgroundPrimaryDefault,
        )
        Spacer(modifier = Modifier.size(12.dp))
        MenuItem(
            title = "1556",
            startIcon = com.pmb.ballon.R.drawable.ic_phone_call,
            titleStyle = TextStyle(
                color = AppTheme.colorScheme.foregroundNeutralDefault,
                typography = AppTheme.typography.bodyMedium,
            ),
            startIconStyle = IconStyle(
                tint = AppTheme.colorScheme.onBackgroundNeutralSubdued,
            ),
            onItemClick = {
                context.actionCall("1556")
            }
        )
        MenuItem(
            title = "www.bankmellat.ir",
            startIcon = com.pmb.ballon.R.drawable.ic_language,
            titleStyle = TextStyle(
                color = AppTheme.colorScheme.foregroundNeutralDefault,
                typography = AppTheme.typography.bodyMedium,
            ),
            startIconStyle = IconStyle(
                tint = AppTheme.colorScheme.onBackgroundNeutralSubdued,
            ),
            onItemClick = {
                context.openWebPage("https://www.bankmellat.ir")
            }
        )
    }
}