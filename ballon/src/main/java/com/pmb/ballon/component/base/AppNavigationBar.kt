package com.pmb.ballon.component.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pmb.ballon.R
import com.pmb.core.presentation.Screen

sealed class BottomNavItem(
    @StringRes
    val title: Int,
    @DrawableRes
    val selectedIcon: Int,
    @DrawableRes
    val unselectedIcon: Int,
    val badgeAmount: Int? = null,
    open val screen: Screen,
) {
    data class Home(override val screen: Screen) : BottomNavItem(
        title = R.string.home,
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_gray,
        screen = screen
    )

    data class Transfer(override val screen: Screen) : BottomNavItem(
        title = R.string.transfer_and_receive,
        selectedIcon = R.drawable.ic_double_left_circle,
        unselectedIcon = R.drawable.ic_double_left_circle,
        screen = screen
    )

    data class AccountCard(override val screen: Screen) : BottomNavItem(
        title = R.string.account_card,
        selectedIcon = R.drawable.ic_bank_card_filled,
        unselectedIcon = R.drawable.ic_bank_card,
        screen = screen
    )

    data class Profile(override val screen: Screen) : BottomNavItem(
        title = R.string.profile,
        selectedIcon = R.drawable.ic_user_circle_filled,
        unselectedIcon = R.drawable.ic_user_circle_black,
        screen = screen
    )
}


@Composable
fun AppBottomBar(tabBarItems: List<BottomNavItem>, selectedItem: (Screen) -> Unit) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        // looping over each tab to generate the views and navigation for each item
        tabBarItems.forEachIndexed { index, tabBarItem ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    selectedItem.invoke(tabBarItem.screen)
                },
                icon = {
                    TabBarIconView(
                        isSelected = selectedTabIndex == index,
                        selectedIcon = painterResource(tabBarItem.selectedIcon),
                        unselectedIcon = painterResource(tabBarItem.unselectedIcon),
                        title = stringResource(tabBarItem.title),
                        badgeAmount = tabBarItem.badgeAmount
                    )
                },
                label = {
                    if (selectedTabIndex == index) ButtonXSmallText(text = stringResource(tabBarItem.title))
                    else CaptionText(text = stringResource(tabBarItem.title))
                })
        }
    }
}

// This component helps to clean up the API call from our TabView above,
// but could just as easily be added inside the TabView without creating this custom component
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
            painter = if (isSelected) {
                selectedIcon
            } else {
                unselectedIcon
            },
            contentDescription = title
        )
    }
}

// This component helps to clean up the API call from our TabBarIconView above,
// but could just as easily be added inside the TabBarIconView without creating this custom component
@Composable
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge { Text(count.toString()) }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    AppBottomBar(tabBarItems = bottomNavItems)
}
