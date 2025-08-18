package com.pmb.account.presentation.issueCard

import com.pmb.account.presentation.issueCard.selectAddress.AddressType
import com.pmb.core.platform.BaseSharedState
import com.pmb.core.platform.BaseSharedViewModel
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.card.CardCustomerAddressResponse
import com.pmb.domain.model.card.City
import com.pmb.domain.model.card.FetchCommissionForCreateCardResponse
import com.pmb.domain.model.card.Result
import com.pmb.domain.model.openAccount.accountType.Province
import jakarta.inject.Inject

class IssueCardSharedViewModel @Inject constructor() :
    BaseSharedViewModel<IssueCardSharedState>(IssueCardSharedState())

data class IssueCardSharedState(
    val userData: CardCustomerAddressResponse? = null,
    val formatId: Long = 0,
    val panList: List<Result>? = emptyList(),
    val cardOwnerAccount: DepositModel? = null,
    val commissionFeeAccount: DepositModel? = null,
    val commissionFee: FetchCommissionForCreateCardResponse? = null,
    val accountNumber: String = "",
    val selectedOldPan: String? = null,

    val provinceList: List<Province>? = emptyList(),
    val cityList: List<City>? = emptyList(),
    val provinceOfDeposit: Province? = null,
    val cityOfDeposit: City? = null,
    val cardGroup: Long = 1,
    val addressType: AddressType = AddressType.UNSPECIFIED,
    val address: String? = null,
    val postalCode: String? = null,

    ) : BaseSharedState {
    val userAddress: List<AddressOption>
        get() {
            userData?.let {
                return buildList {
                    add(
                        AddressOption(
                            AddressType.OLD_ADDRESS.title,
                            isHeader = true,
                            isClickable = false
                        )
                    )
                    add(
                        AddressOption(
                            it.output.address ?: "",
                            isHeader = false,
                            isClickable = true
                        )
                    )
                    add(
                        AddressOption(
                            AddressType.NEW_ADDRESS.title,
                            isHeader = true,
                            isClickable = true
                        )
                    )
                }
            }
            return emptyList()
        }
}

data class AddressOption(
    val text: String,
    val isHeader: Boolean = false,
    val isClickable: Boolean = true
)