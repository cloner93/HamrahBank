package com.pmb.ballon.component.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.R
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme

sealed class BottomNavItem(
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val badgeAmount: Int? = null
) {
    data object Home : BottomNavItem(
        title = R.string.home,
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_gray,
    )

    data object Transfer : BottomNavItem(
        title = R.string.transfer_and_receive,
        selectedIcon = R.drawable.ic_transfer_unselected,
        unselectedIcon = R.drawable.ic_transfer_selected,
    )

    data object AccountCard : BottomNavItem(
        title = R.string.account_card,
        selectedIcon = R.drawable.ic_bank_card_filled,
        unselectedIcon = R.drawable.ic_bank_card,
    )

    data object Profile : BottomNavItem(
        title = R.string.profile,
        selectedIcon = R.drawable.ic_user_circle_filled,
        unselectedIcon = R.drawable.ic_user_circle_black,
    )
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Transfer,
    BottomNavItem.AccountCard,
    BottomNavItem.Profile
)

@Composable
fun AppBottomBar(
    tabBarItems: List<BottomNavItem>,
    selectedTab: BottomNavItem,
    selectedItem: (BottomNavItem) -> Unit
) {
    NavigationBar(containerColor = AppTheme.colorScheme.background1Neutral) {
        tabBarItems.forEachIndexed { _, tabBarItem ->
            val isSelected = tabBarItem == selectedTab
            NavigationBarItem(
                selected = isSelected,
                onClick = { selectedItem(tabBarItem) },
                icon = {
                    TabBarIconView(
                        isSelected = isSelected,
                        selectedIcon = painterResource(tabBarItem.selectedIcon),
                        unselectedIcon = painterResource(tabBarItem.unselectedIcon),
                        title = stringResource(tabBarItem.title),
                        badgeAmount = tabBarItem.badgeAmount
                    )
                },
                label = {
                    if (isSelected)
                        ButtonXSmallText(
                            text = stringResource(tabBarItem.title),
                            color = AppTheme.colorScheme.onBackgroundNeutralDefault
                        )
                    else
                        CaptionText(
                            text = stringResource(tabBarItem.title),
                            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                        )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    selectedTextColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    indicatorColor = AppTheme.colorScheme.backgroundTintPrimaryDefault,
                    unselectedIconColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                    unselectedTextColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                )
            )
        }
    }
}

@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: Painter,
    unselectedIcon: Painter,
    title: String,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        Icon(
            painter = if (isSelected) selectedIcon else unselectedIcon,
            contentDescription = title
        )
    }
}

@Composable
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge { Text(count.toString()) }
    }
}


@AppPreview
@Composable
fun AppBottomBarPreview() {

    HamrahBankTheme {
        Column {
            AppBottomBar(
                tabBarItems = bottomNavItems,
                selectedTab = bottomNavItems[0],
            ) { }
            AppBottomBar(
                tabBarItems = bottomNavItems,
                selectedTab = bottomNavItems[1],
            ) { }
            AppBottomBar(
                tabBarItems = bottomNavItems,
                selectedTab = bottomNavItems[2],
            ) { }
            AppBottomBar(
                tabBarItems = bottomNavItems,
                selectedTab = bottomNavItems[3],
            ) { }
        }
    }
}