package com.pmb.account.tempRepo

import com.pmb.account.presentation.component.DepositModel
import javax.inject.Inject

class DepositsRepository @Inject constructor() {
    suspend fun getDeposits(): List<DepositModel> {
        return listOf(
            DepositModel(
                "حساب سرمایه گذاری بلند مدت",
                "97974632",
                3_200_000.0,
                "ریال",
                "IR1234567890098765432112",
                "6219861920241234"
            ),
            DepositModel(
                "حساب مشترک",
                "82768947",
                4_700_000.0,
                "ریال",
                "IR1234567890098765432112",
                "6219861920241234"
            ),
            DepositModel(
                "حساب جاری",
                "746384",
                2_300_000.0,
                "ریال",
                "IR1234567890098765432112",
                "6219861920241234"
            ),
            DepositModel(
                "حساب قرض الحسنه خانواده",
                "572839",
                5_000_000.0,
                "ریال",
                "IR1234567890098765432112",
                "6219861920241234"
            )

        )
    }
}