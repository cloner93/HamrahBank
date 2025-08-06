package com.pmb.profile.presentaion.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppButtonWithIcon
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.Headline5Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.shareText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowInviteFriendBottomSheet(onDismiss: () -> Unit) {
    var isVisible by remember { mutableStateOf(true) }
    val context = LocalContext.current
    AppBottomSheet(
        isVisible = isVisible,
        cancelable = true,
        onDismiss = { onDismiss() },
        dragHandle = { BottomSheetDefaults.DragHandle() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Headline5Text(
                    text = "دوستان خود را به همراه بانک ملت دعوت کنید!",
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
                Spacer(modifier = Modifier.size(16.dp))
                BodyMediumText(
                    text = "با معرفی این اپلیکیشن، امکانات بانکی پیشرفته را به اشتراک بگذارید.",
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(56.dp))
                AppButtonWithIcon(
                    modifier = Modifier.fillMaxWidth(),
                    title = "ارسال دعوت",
                    icon = R.drawable.ic_send,
                    spacer = 8.dp,
                    onClick = {
                        context.shareText("\uD83C\uDF89 دوست عزیزم!\n" +
                                "همراه بانک ملت، بهترین همراه برای مدیریت مالی شماست! امکانات اپلیکیشن:\n" +
                                "\n" +
                                "✅ انتقال وجه سریع\n" +
                                "✅ پرداخت قبض و خرید شارژ\n" +
                                "✅ مشاهده موجودی و گردش حساب\n" +
                                "✅ امکانات ویژه دیگر\n" +
                                "\n" +
                                "همین حالا ثبت\u200Cنام کن و راحتی رو تجربه کن!\n" +
                                "\uD83D\uDD17  /https://bankmellat.ir/default.aspx")
                    })
            }
        })
}