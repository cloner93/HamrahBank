package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class ChargeScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object ChargeGraph : ChargeScreens(route = "charge_graph")
    data object Charge : ChargeScreens(route = "charge")
    data object PurchaseCharge : ChargeScreens(route = "purchase_charge")
    data object ChargeHistory : ChargeScreens(route = "charge_history")
    data object ChooseChargePrice : ChargeScreens(route = "charge_price")
    data object ChargeConfirm: ChargeScreens(route = "charge_confirm")
    data object ChargeReceipt: ChargeScreens(route = "charge_receipt")
}