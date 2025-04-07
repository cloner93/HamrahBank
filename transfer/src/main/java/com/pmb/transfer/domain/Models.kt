package com.pmb.transfer.domain

import androidx.annotation.DrawableRes

data class ClientBank(
    val name: String,
    val phoneNumber: String,
    val profileUrl: String,
    val cardNumber: Long,
    val accountNumber: String,
    val iban: String
)

enum class BankIdentifierNumberType { ACCOUNT, CARD, IBAN }

data class TransactionClientBank(
    val clientBank: ClientBank,
    val type: BankIdentifierNumberType,
    val favorite: Boolean = false
)


val clientBanks = listOf(
    ClientBank(
        name = "محمد صادقی",
        phoneNumber = "09123456789",
        profileUrl = "https://randomuser.me/api/portraits/men/1.jpg",
        cardNumber = 6037991234567890,
        accountNumber = "1234567890123456",
        iban = "IR820540102680020817909002"
    ),
    ClientBank(
        name = "علی رضایی",
        phoneNumber = "09123456788",
        profileUrl = "https://randomuser.me/api/portraits/men/2.jpg",
        cardNumber = 6104339876543210,
        accountNumber = "2345678901234567",
        iban = "IR620540102680020817909003"
    ),
    ClientBank(
        name = "زهرا محمدی",
        phoneNumber = "09123456787",
        profileUrl = "https://randomuser.me/api/portraits/women/1.jpg",
        cardNumber = 6219861122334455,
        accountNumber = "3456789012345678",
        iban = "IR360540102680020817909004"
    ),
    ClientBank(
        name = "سارا احمدی",
        phoneNumber = "09123456786",
        profileUrl = "https://randomuser.me/api/portraits/women/2.jpg",
        cardNumber = 6274123456789012,
        accountNumber = "4567890123456789",
        iban = "IR700540102680020817909005"
    ),
    ClientBank(
        name = "رضا حسینی",
        phoneNumber = "09123456785",
        profileUrl = "https://randomuser.me/api/portraits/men/3.jpg",
        cardNumber = 5892109876543210,
        accountNumber = "5678901234567890",
        iban = "IR530540102680020817909006"
    ),
    ClientBank(
        name = "فاطمه نادری",
        phoneNumber = "09123456784",
        profileUrl = "https://randomuser.me/api/portraits/women/3.jpg",
        cardNumber = 5022298765432101,
        accountNumber = "6789012345678901",
        iban = "IR140540102680020817909007"
    ),
    ClientBank(
        name = "مهدی کاظمی",
        phoneNumber = "09123456783",
        profileUrl = "https://randomuser.me/api/portraits/men/4.jpg",
        cardNumber = 6037701234567891,
        accountNumber = "7890123456789012",
        iban = "IR820540102680020817909008"
    ),
    ClientBank(
        name = "هانیه سعیدی",
        phoneNumber = "09123456782",
        profileUrl = "https://randomuser.me/api/portraits/women/4.jpg",
        cardNumber = 6104331122334456,
        accountNumber = "8901234567890123",
        iban = "IR920540102680020817909009"
    ),
    ClientBank(
        name = "امیر زمانی",
        phoneNumber = "09123456781",
        profileUrl = "https://randomuser.me/api/portraits/men/5.jpg",
        cardNumber = 6362145678901234,
        accountNumber = "9012345678901234",
        iban = "IR250540102680020817909010"
    ),
    ClientBank(
        name = "نگار فرهادی",
        phoneNumber = "09123456780",
        profileUrl = "https://randomuser.me/api/portraits/women/5.jpg",
        cardNumber = 5029387654321098,
        accountNumber = "0123456789012345",
        iban = "IR180540102680020817909011"
    )
)

val transactionClientBanks = listOf(
    TransactionClientBank(
        clientBank = ClientBank(
            name = "محمد صادقی",
            phoneNumber = "09123456789",
            profileUrl = "https://randomuser.me/api/portraits/men/1.jpg",
            cardNumber = 6037991234567890,
            accountNumber = "1234567890123456",
            iban = "IR820540102680020817909002"
        ),
        type = BankIdentifierNumberType.CARD
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "علی رضایی",
            phoneNumber = "09123456788",
            profileUrl = "https://randomuser.me/api/portraits/men/2.jpg",
            cardNumber = 6104339876543210,
            accountNumber = "2345678901234567",
            iban = "IR620540102680020817909003"
        ),
        type = BankIdentifierNumberType.ACCOUNT
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "زهرا محمدی",
            phoneNumber = "09123456787",
            profileUrl = "https://randomuser.me/api/portraits/women/1.jpg",
            cardNumber = 6219861122334455,
            accountNumber = "3456789012345678",
            iban = "IR360540102680020817909004"
        ),
        type = BankIdentifierNumberType.IBAN
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "سارا احمدی",
            phoneNumber = "09123456786",
            profileUrl = "https://randomuser.me/api/portraits/women/2.jpg",
            cardNumber = 6274123456789012,
            accountNumber = "4567890123456789",
            iban = "IR700540102680020817909005"
        ),
        type = BankIdentifierNumberType.CARD
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "رضا حسینی",
            phoneNumber = "09123456785",
            profileUrl = "https://randomuser.me/api/portraits/men/3.jpg",
            cardNumber = 5892109876543210,
            accountNumber = "5678901234567890",
            iban = "IR530540102680020817909006"
        ),
        type = BankIdentifierNumberType.ACCOUNT
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "فاطمه نادری",
            phoneNumber = "09123456784",
            profileUrl = "https://randomuser.me/api/portraits/women/3.jpg",
            cardNumber = 5022298765432101,
            accountNumber = "6789012345678901",
            iban = "IR140540102680020817909007"
        ),
        type = BankIdentifierNumberType.IBAN
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "مهدی کاظمی",
            phoneNumber = "09123456783",
            profileUrl = "https://randomuser.me/api/portraits/men/4.jpg",
            cardNumber = 6037701234567891,
            accountNumber = "7890123456789012",
            iban = "IR820540102680020817909008"
        ),
        type = BankIdentifierNumberType.CARD
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "هانیه سعیدی",
            phoneNumber = "09123456782",
            profileUrl = "https://randomuser.me/api/portraits/women/4.jpg",
            cardNumber = 6104331122334456,
            accountNumber = "8901234567890123",
            iban = "IR920540102680020817909009"
        ),
        type = BankIdentifierNumberType.ACCOUNT
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "امیر زمانی",
            phoneNumber = "09123456781",
            profileUrl = "https://randomuser.me/api/portraits/men/5.jpg",
            cardNumber = 6362145678901234,
            accountNumber = "9012345678901234",
            iban = "IR250540102680020817909010"
        ),
        type = BankIdentifierNumberType.IBAN
    ), TransactionClientBank(
        clientBank = ClientBank(
            name = "فاطمه نادری",
            phoneNumber = "09123456784",
            profileUrl = "https://randomuser.me/api/portraits/women/3.jpg",
            cardNumber = 5022298765432101,
            accountNumber = "6789012345678901",
            iban = "IR140540102680020817909007"
        ),
        type = BankIdentifierNumberType.IBAN
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "مهدی کاظمی",
            phoneNumber = "09123456783",
            profileUrl = "https://randomuser.me/api/portraits/men/4.jpg",
            cardNumber = 6037701234567891,
            accountNumber = "7890123456789012",
            iban = "IR820540102680020817909008"
        ),
        type = BankIdentifierNumberType.CARD
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "هانیه سعیدی",
            phoneNumber = "09123456782",
            profileUrl = "https://randomuser.me/api/portraits/women/4.jpg",
            cardNumber = 6104331122334456,
            accountNumber = "8901234567890123",
            iban = "IR920540102680020817909009"
        ),
        type = BankIdentifierNumberType.ACCOUNT
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "امیر زمانی",
            phoneNumber = "09123456781",
            profileUrl = "https://randomuser.me/api/portraits/men/5.jpg",
            cardNumber = 6362145678901234,
            accountNumber = "9012345678901234",
            iban = "IR250540102680020817909010"
        ),
        type = BankIdentifierNumberType.IBAN
    ), TransactionClientBank(
        clientBank = ClientBank(
            name = "فاطمه نادری",
            phoneNumber = "09123456784",
            profileUrl = "https://randomuser.me/api/portraits/women/3.jpg",
            cardNumber = 5022298765432101,
            accountNumber = "6789012345678901",
            iban = "IR140540102680020817909007"
        ),
        type = BankIdentifierNumberType.IBAN
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "مهدی کاظمی",
            phoneNumber = "09123456783",
            profileUrl = "https://randomuser.me/api/portraits/men/4.jpg",
            cardNumber = 6037701234567891,
            accountNumber = "7890123456789012",
            iban = "IR820540102680020817909008"
        ),
        type = BankIdentifierNumberType.CARD
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "هانیه سعیدی",
            phoneNumber = "09123456782",
            profileUrl = "https://randomuser.me/api/portraits/women/4.jpg",
            cardNumber = 6104331122334456,
            accountNumber = "8901234567890123",
            iban = "IR920540102680020817909009"
        ),
        type = BankIdentifierNumberType.ACCOUNT
    ),
    TransactionClientBank(
        clientBank = ClientBank(
            name = "امیر زمانی",
            phoneNumber = "09123456781",
            profileUrl = "https://randomuser.me/api/portraits/men/5.jpg",
            cardNumber = 6362145678901234,
            accountNumber = "9012345678901234",
            iban = "IR250540102680020817909010"
        ),
        type = BankIdentifierNumberType.IBAN
    ),

    TransactionClientBank(
        clientBank = ClientBank(
            name = "نگار فرهادی",
            phoneNumber = "09123456780",
            profileUrl = "https://randomuser.me/api/portraits/women/5.jpg",
            cardNumber = 5029387654321098,
            accountNumber = "0123456789012345",
            iban = "IR180540102680020817909011"
        ),
        type = BankIdentifierNumberType.CARD
    )
)

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
        accountPrefix = "056",
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





