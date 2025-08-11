package com.pmb.transfer.domain.entity

import androidx.annotation.DrawableRes

enum class Bank(
    val accountPrefix: String,
    val cardPrefix: List<String>,
    val shebaPrefix: String,
    @DrawableRes val icon: Int
) {
    MELLI(
        accountPrefix = "017",
        cardPrefix = listOf("603799", "170019"),
        shebaPrefix = "IR01",
        icon = com.pmb.ballon.R.drawable.ic_bank_melli
    ),
    SADERAT(
        accountPrefix = "019",
        cardPrefix = listOf("603769", "589210"),
        shebaPrefix = "IR02",
        icon = com.pmb.ballon.R.drawable.ic_bank_saderat
    ),
    MELLAT(
        accountPrefix = "012",
        cardPrefix = listOf("610433", "991975"),
        shebaPrefix = "IR12",
        icon = com.pmb.ballon.R.drawable.ic_bank_mellat
    ),
    TEJARAT(
        accountPrefix = "018",
        cardPrefix = listOf("627353", "585983"),
        shebaPrefix = "IR18",
        icon = com.pmb.ballon.R.drawable.ic_bank_tejarat
    ),
    SEPAH(
        accountPrefix = "015",
        cardPrefix = listOf("589210"),
        shebaPrefix = "IR15",
        icon = com.pmb.ballon.R.drawable.ic_bank_sepah
    ),
    MASKAN(
        accountPrefix = "013",
        cardPrefix = listOf("628023"),
        shebaPrefix = "IR13",
        icon = com.pmb.ballon.R.drawable.ic_bank_maskan
    ),
    KESHAVARZI(
        accountPrefix = "016",
        cardPrefix = listOf("603770"),
        shebaPrefix = "IR16",
        icon = com.pmb.ballon.R.drawable.ic_bank_keshavarzi
    ),
    SANAT_MADAN(
        accountPrefix = "011",
        cardPrefix = listOf("627961"),
        shebaPrefix = "IR11",
        icon = com.pmb.ballon.R.drawable.ic_bank_sanat_madan
    ),
    POST_BANK(
        accountPrefix = "021",
        cardPrefix = listOf("627760"),
        shebaPrefix = "IR21",
        icon = com.pmb.ballon.R.drawable.ic_bank_postbank
    ),
    TOSEE_SADERAT(
        accountPrefix = "020",
        cardPrefix = listOf("207177"),
        shebaPrefix = "IR20",
        icon = com.pmb.ballon.R.drawable.ic_bank_saderat
    ),
    EGHTESAD_NOVIN(
        accountPrefix = "055",
        cardPrefix = listOf("627412"),
        shebaPrefix = "IR55",
        icon = com.pmb.ballon.R.drawable.ic_bank_eghtesad_novin
    ),
    PARSIAN(
        accountPrefix = "054",
        cardPrefix = listOf("622106", "627884"),
        shebaPrefix = "IR54",
        icon = com.pmb.ballon.R.drawable.ic_bank_parsian
    ),
    PASARGAD(
        accountPrefix = "057",
        cardPrefix = listOf("502229", "639347"),
        shebaPrefix = "IR57",
        icon = com.pmb.ballon.R.drawable.ic_bank_pasargad
    ),
    KARAFARIN(
        accountPrefix = "053",
        cardPrefix = listOf("627488"),
        shebaPrefix = "IR53",
        icon = com.pmb.ballon.R.drawable.ic_bank_karafarin
    ),
    BLU(
        accountPrefix = "-1",
        cardPrefix = listOf("62198619"),
        shebaPrefix = "IR56",
        icon = com.pmb.ballon.R.drawable.ic_bank_blu
    ),
    SAMAN(
        accountPrefix = "056",
        cardPrefix = listOf("621986"),
        shebaPrefix = "IR56",
        icon = com.pmb.ballon.R.drawable.ic_bank_saman
    ),
    SINA(
        accountPrefix = "059",
        cardPrefix = listOf("639346"),
        shebaPrefix = "IR59",
        icon = com.pmb.ballon.R.drawable.ic_bank_sina
    ),
    SARMAYEH(
        accountPrefix = "058",
        cardPrefix = listOf("639607"),
        shebaPrefix = "IR58",
        icon = com.pmb.ballon.R.drawable.ic_bank_sarmayeh
    ),
    SHAHR(
        accountPrefix = "061",
        cardPrefix = listOf("502806", "504706"),
        shebaPrefix = "IR61",
        icon = com.pmb.ballon.R.drawable.ic_bank_shahr
    ),
    DEY(
        accountPrefix = "066",
        cardPrefix = listOf("502938"),
        shebaPrefix = "IR66",
        icon = com.pmb.ballon.R.drawable.ic_bank_dey
    ),
    IRAN_ZAMIN(
        accountPrefix = "069",
        cardPrefix = listOf("505785"),
        shebaPrefix = "IR69",
        icon = com.pmb.ballon.R.drawable.ic_bank_iran_zamin
    ),
    ANSAR(
        accountPrefix = "063",
        cardPrefix = listOf("627381"),
        shebaPrefix = "IR63",
        icon = com.pmb.ballon.R.drawable.ic_bank_ansar
    ),
    MEHR_IRAN(
        accountPrefix = "060",
        cardPrefix = listOf("606373"),
        shebaPrefix = "IR60",
        icon = com.pmb.ballon.R.drawable.ic_bank_mehr_iran
    ),
    GHAVAMIN(
        accountPrefix = "052",
        cardPrefix = listOf("639599"),
        shebaPrefix = "IR52",
        icon = com.pmb.ballon.R.drawable.ic_bank_ghavamin
    ),
    HEKMAT_IRANIAN(
        accountPrefix = "065",
        cardPrefix = listOf("636949"),
        shebaPrefix = "IR65",
        icon = com.pmb.ballon.R.drawable.ic_bank_hekmat
    ),

    //    TOURISM("064", listOf("505416"), "IR64", com.pmb.ballon.R.drawable.ic_bank_tourism)),
    REFAH(
        accountPrefix = "013",
        cardPrefix = listOf("589463"),
        shebaPrefix = "IR13",
        icon = com.pmb.ballon.R.drawable.ic_bank_refah
    ),
    TOSEE(
        accountPrefix = "051",
        cardPrefix = listOf("628157"),
        shebaPrefix = "IR51",
        icon = com.pmb.ballon.R.drawable.ic_bank_saderat
    ),

    //    COOPERATIVE_DEVELOPMENT("022", listOf("504709"), "IR22", com.pmb.ballon.R.drawable.ic_bank_cooperative_development),
    IRAN_VENEZUELA(
        accountPrefix = "090",
        cardPrefix = listOf("507677"),
        shebaPrefix = "IR90",
        icon = com.pmb.ballon.R.drawable.ic_bank_iran_venezuela
    ),

    //    MIDDLE_EAST("062", listOf("505809"), "IR62", com.pmb.ballon.R.drawable.ic_bank_middle_east),
    RESALAT(
        accountPrefix = "070",
        cardPrefix = listOf("504172"),
        shebaPrefix = "IR70",
        icon = com.pmb.ballon.R.drawable.ic_bank_resalat
    ),
    KHAVARMIANEH(
        accountPrefix = "078",
        cardPrefix = listOf("505785"),
        shebaPrefix = "IR78",
        icon = com.pmb.ballon.R.drawable.ic_bank_khavarmianeh
    ),
    // اگر بانک‌های دیگری لازم بود، می‌توانید به اینجا اضافه کنید
}

