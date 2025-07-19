package com.pmb.home.domain.home.entity

data class HomeEntity(
    val isSuccess:Boolean,
    val sliderImage: List<Int>,
    val homeItems: List<HomeItems>
)

data class HomeItems(
    val id: Int,
    val title: String,
    val img: Int,
    val type: HomeItemType
)

enum class HomeItemType {
    LOAN,
    CHARGE,
    BILL,
    INTERNET,
    CHEQUE,
    BRANCHES,
    FESTIVAL,
    CHARITY
}

