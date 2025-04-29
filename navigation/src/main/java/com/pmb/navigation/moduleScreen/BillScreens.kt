package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class BillScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object BillGraph : BillScreens("bill_graph")
    data object Bill : BillScreens("bill")
    data object BillsHistory : BillScreens("bills_history")
}