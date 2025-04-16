package com.pmb.transfer.data.source.local

import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.entity.AccountStatus
import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.ClientBankEntity
import com.pmb.transfer.domain.entity.PaymentType
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.domain.entity.ReceiptStatus
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity

object Mock {
    val clientBankEntities = listOf(
        ClientBankEntity(
            name = "محمد صادقی",
            phoneNumber = "09123456789",
            profileUrl = "https://randomuser.me/api/portraits/men/1.jpg",
            cardNumber = "6037991234567890",
            accountNumber = "1234567890123456",
            iban = "IR820540102680020817909002"
        ),
        ClientBankEntity(
            name = "علی رضایی",
            phoneNumber = "09123456788",
            profileUrl = "https://randomuser.me/api/portraits/men/2.jpg",
            cardNumber = "6104339876543210",
            accountNumber = "2345678901234567",
            iban = "IR620540102680020817909003"
        ),
        ClientBankEntity(
            name = "زهرا محمدی",
            phoneNumber = "09123456787",
            profileUrl = "https://randomuser.me/api/portraits/women/1.jpg",
            cardNumber = "6219861122334455",
            accountNumber = "3456789012345678",
            iban = "IR360540102680020817909004"
        ),
        ClientBankEntity(
            name = "سارا احمدی",
            phoneNumber = "09123456786",
            profileUrl = "https://randomuser.me/api/portraits/women/2.jpg",
            cardNumber = "6274123456789012",
            accountNumber = "4567890123456789",
            iban = "IR700540102680020817909005"
        ),
        ClientBankEntity(
            name = "رضا حسینی",
            phoneNumber = "09123456785",
            profileUrl = "https://randomuser.me/api/portraits/men/3.jpg",
            cardNumber = "5892109876543210",
            accountNumber = "5678901234567890",
            iban = "IR530540102680020817909006"
        ),
        ClientBankEntity(
            name = "فاطمه نادری",
            phoneNumber = "09123456784",
            profileUrl = "https://randomuser.me/api/portraits/women/3.jpg",
            cardNumber = "5022298765432101",
            accountNumber = "6789012345678901",
            iban = "IR140540102680020817909007"
        ),
        ClientBankEntity(
            name = "مهدی کاظمی",
            phoneNumber = "09123456783",
            profileUrl = "https://randomuser.me/api/portraits/men/4.jpg",
            cardNumber = "6037701234567891",
            accountNumber = "7890123456789012",
            iban = "IR820540102680020817909008"
        ),
        ClientBankEntity(
            name = "هانیه سعیدی",
            phoneNumber = "09123456782",
            profileUrl = "https://randomuser.me/api/portraits/women/4.jpg",
            cardNumber = "6104331122334456",
            accountNumber = "8901234567890123",
            iban = "IR920540102680020817909009"
        ),
        ClientBankEntity(
            name = "امیر زمانی",
            phoneNumber = "09123456781",
            profileUrl = "https://randomuser.me/api/portraits/men/5.jpg",
            cardNumber = "6362145678901234",
            accountNumber = "9012345678901234",
            iban = "IR250540102680020817909010"
        ),
        ClientBankEntity(
            name = "نگار فرهادی",
            phoneNumber = "09123456780",
            profileUrl = "https://randomuser.me/api/portraits/women/5.jpg",
            cardNumber = "5029387654321098",
            accountNumber = "0123456789012345",
            iban = "IR180540102680020817909011"
        )
    )

    val transactionClientBanksEntity = listOf(
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "محمد صادقی",
                phoneNumber = "09123456789",
                profileUrl = "https://randomuser.me/api/portraits/men/1.jpg",
                cardNumber = "6037991234567890",
                accountNumber = "1234567890123456",
                iban = "IR820540102680020817909002"
            ),
            type = BankIdentifierNumberType.CARD
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "علی رضایی",
                phoneNumber = "09123456788",
                profileUrl = "https://randomuser.me/api/portraits/men/2.jpg",
                cardNumber = "6104339876543210",
                accountNumber = "2345678901234567",
                iban = "IR620540102680020817909003"
            ),
            type = BankIdentifierNumberType.ACCOUNT
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "زهرا محمدی",
                phoneNumber = "09123456787",
                profileUrl = "https://randomuser.me/api/portraits/women/1.jpg",
                cardNumber = "6219861122334455",
                accountNumber = "3456789012345678",
                iban = "IR360540102680020817909004"
            ),
            type = BankIdentifierNumberType.IBAN
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "سارا احمدی",
                phoneNumber = "09123456786",
                profileUrl = "https://randomuser.me/api/portraits/women/2.jpg",
                cardNumber = "6274123456789012",
                accountNumber = "4567890123456789",
                iban = "IR700540102680020817909005"
            ),
            type = BankIdentifierNumberType.CARD
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "رضا حسینی",
                phoneNumber = "09123456785",
                profileUrl = "https://randomuser.me/api/portraits/men/3.jpg",
                cardNumber = "5892109876543210",
                accountNumber = "5678901234567890",
                iban = "IR530540102680020817909006"
            ),
            type = BankIdentifierNumberType.ACCOUNT
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "فاطمه نادری",
                phoneNumber = "09123456784",
                profileUrl = "https://randomuser.me/api/portraits/women/3.jpg",
                cardNumber = "5022298765432101",
                accountNumber = "6789012345678901",
                iban = "IR140540102680020817909007"
            ),
            type = BankIdentifierNumberType.IBAN
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "مهدی کاظمی",
                phoneNumber = "09123456783",
                profileUrl = "https://randomuser.me/api/portraits/men/4.jpg",
                cardNumber = "6037701234567891",
                accountNumber = "7890123456789012",
                iban = "IR820540102680020817909008"
            ),
            type = BankIdentifierNumberType.CARD
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "هانیه سعیدی",
                phoneNumber = "09123456782",
                profileUrl = "https://randomuser.me/api/portraits/women/4.jpg",
                cardNumber = "6104331122334456",
                accountNumber = "8901234567890123",
                iban = "IR920540102680020817909009"
            ),
            type = BankIdentifierNumberType.ACCOUNT
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "امیر زمانی",
                phoneNumber = "09123456781",
                profileUrl = "https://randomuser.me/api/portraits/men/5.jpg",
                cardNumber = "6362145678901234",
                accountNumber = "9012345678901234",
                iban = "IR250540102680020817909010"
            ),
            type = BankIdentifierNumberType.IBAN
        ), TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "فاطمه نادری",
                phoneNumber = "09123456784",
                profileUrl = "https://randomuser.me/api/portraits/women/3.jpg",
                cardNumber = "5022298765432101",
                accountNumber = "6789012345678901",
                iban = "IR140540102680020817909007"
            ),
            type = BankIdentifierNumberType.IBAN
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "مهدی کاظمی",
                phoneNumber = "09123456783",
                profileUrl = "https://randomuser.me/api/portraits/men/4.jpg",
                cardNumber = "6037701234567891",
                accountNumber = "7890123456789012",
                iban = "IR820540102680020817909008"
            ),
            type = BankIdentifierNumberType.CARD
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "هانیه سعیدی",
                phoneNumber = "09123456782",
                profileUrl = "https://randomuser.me/api/portraits/women/4.jpg",
                cardNumber = "6104331122334456",
                accountNumber = "8901234567890123",
                iban = "IR920540102680020817909009"
            ),
            type = BankIdentifierNumberType.ACCOUNT
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "امیر زمانی",
                phoneNumber = "09123456781",
                profileUrl = "https://randomuser.me/api/portraits/men/5.jpg",
                cardNumber = "6362145678901234",
                accountNumber = "9012345678901234",
                iban = "IR250540102680020817909010"
            ),
            type = BankIdentifierNumberType.IBAN
        ), TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "فاطمه نادری",
                phoneNumber = "09123456784",
                profileUrl = "https://randomuser.me/api/portraits/women/3.jpg",
                cardNumber = "5022298765432101",
                accountNumber = "6789012345678901",
                iban = "IR140540102680020817909007"
            ),
            type = BankIdentifierNumberType.IBAN
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "مهدی کاظمی",
                phoneNumber = "09123456783",
                profileUrl = "https://randomuser.me/api/portraits/men/4.jpg",
                cardNumber = "6037701234567891",
                accountNumber = "7890123456789012",
                iban = "IR820540102680020817909008"
            ),
            type = BankIdentifierNumberType.CARD
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "هانیه سعیدی",
                phoneNumber = "09123456782",
                profileUrl = "https://randomuser.me/api/portraits/women/4.jpg",
                cardNumber = "6104331122334456",
                accountNumber = "8901234567890123",
                iban = "IR920540102680020817909009"
            ),
            type = BankIdentifierNumberType.ACCOUNT
        ),
        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "امیر زمانی",
                phoneNumber = "09123456781",
                profileUrl = "https://randomuser.me/api/portraits/men/5.jpg",
                cardNumber = "6362145678901234",
                accountNumber = "9012345678901234",
                iban = "IR250540102680020817909010"
            ),
            type = BankIdentifierNumberType.IBAN
        ),

        TransactionClientBankEntity(
            clientBankEntity = ClientBankEntity(
                name = "نگار فرهادی",
                phoneNumber = "09123456780",
                profileUrl = "https://randomuser.me/api/portraits/women/5.jpg",
                cardNumber = "5029387654321098",
                accountNumber = "0123456789012345",
                iban = "IR180540102680020817909011"
            ),
            type = BankIdentifierNumberType.CARD
        )
    )


    val transferMethods = listOf(
        TransferMethodEntity(
            title = "کارت به کارت",
            detail = "انتقال در لحظه | کارمزد: ۵٬۰۰۰ ریال",
            fee = 5000L,
            active = true,
            default = true,
            paymentType = PaymentType.CARD_TO_CARD
        ),
        TransferMethodEntity(
            title = "بین بانکی (ساتنا)",
            detail = "حداقل مبلغ انتقال در هر روز ۵۰۰٬۰۰۰٬۰۰۰ ریال",
            fee = 0,
            active = true,
            paymentType = PaymentType.INTERNAL_SATNA
        ),
        TransferMethodEntity(
            title = "بین بانکی (پایا)",
            detail = "انتقال امروز، ۷:۴۵ شب | کارمزد: ۲٬۴۰۰ ریال",
            fee = 2400L,
            active = false,
            paymentType = PaymentType.INTERNAL_PAYA
        ),
        TransferMethodEntity(
            title = "بین بانکی (پل)",
            detail = "انتقال در لحظه | کارمزد: ۵٬۰۰۰ ریال ",
            fee = 5000L,
            active = true,
            paymentType = PaymentType.INTERNAL_BRIDGE
        ),
        TransferMethodEntity(
            title = "کارت به کارت",
            detail = "انتقال در لحظه | کارمزد: ۳۰۰٬۰۰۰ ریال ",
            fee = 30000L,
            active = false,
            paymentType = PaymentType.MELLAT_TO_MELLAT
        )
    )

    val transferReceiptSussesEntity: TransferReceiptEntity =
        TransferReceiptEntity(
            account = transactionClientBanksEntity.first(),
            status = ReceiptStatus.SUCCESS,
            amount = 10000000.0,
            date = 1744455441,
            senderName = "پوریا خلج",
            paymentType = PaymentType.CARD_TO_CARD,
            message = "این رسید به معنای واریز قطعی و نهایی نیست.",
            sourceAccount = null,
            trackingNumber = 13710828,
        )


    val fakeCardBanks = listOf(
        CardBankEntity(
            id = 1L,
            cardNumber = "۶۰۳۷۶۹۱۹۰۱۲۳۴۵۶۷",
            cardHolderName = "علیرضا حسینی",
            cardExpireYear = "04",
            cardExpireMonth = "12",
            cardCvv = "843",
            cardType = "ملت",
            cardBalance = 4_820_000.0,
            cardStatus = AccountStatus.ACTIVE,
            defaulted = true
        ),
        CardBankEntity(
            id = 2L,
            cardNumber = "۵۸۹۲۱۰۱۲۰۰۰۱۲۳۴۵",
            cardHolderName = "محمدرضا قاسمی",
            cardExpireYear = "05",
            cardExpireMonth = "09",
            cardCvv = "238",
            cardType = "سپه",
            cardBalance = 1_575_000.0,
            cardStatus = AccountStatus.SUSPEND,
            defaulted = false
        ),
        CardBankEntity(
            id = 3L,
            cardNumber = "۶۲۷۴۱۲۹۰۲۳۴۵۶۷۸۹",
            cardHolderName = "زهرا محمدی",
            cardExpireYear = "06",
            cardExpireMonth = "03",
            cardCvv = "771",
            cardType = "اقتصاد نوین",
            cardBalance = 620_000.0,
            cardStatus = AccountStatus.ACTIVE,
            defaulted = false
        ),
        CardBankEntity(
            id = 4L,
            cardNumber = "۵۰۲۲۲۹۱۰۲۳۴۵۶۷۸۰",
            cardHolderName = "مهدی قربانی",
            cardExpireYear = "07",
            cardExpireMonth = "11",
            cardCvv = "995",
            cardType = "پاسارگاد",
            cardBalance = 9_360_000.0,
            cardStatus = AccountStatus.ACTIVE,
            defaulted = false
        ),
        CardBankEntity(
            id = 5L,
            cardNumber = "۶۰۳۷۹۹۷۵۰۱۲۳۴۵۶۷",
            cardHolderName = "سارا احمدی",
            cardExpireYear = "03",
            cardExpireMonth = "07",
            cardCvv = "524",
            cardType = "ملی",
            cardBalance = 120_000.0,
            cardStatus = AccountStatus.DE_ACTIVE,
            defaulted = false
        )
    )


    val fakeAccountBanks = listOf(
        AccountBankEntity(
            id = 1L,
            accountNumber = "37380863",
            accountHolderName = "رضا محمدی",
            accountType = "سپرده جاری",
            accountHint = "حساب تنخواه",
            accountBalance = 7_543_200.0,
            accountStatus = AccountStatus.ACTIVE,
            defaulted = false
        ),
        AccountBankEntity(
            id = 2L,
            accountNumber = "14820973",
            accountHolderName = "زهرا احمدی",
            accountType = "سپرده قرض‌الحسنه",
            accountHint = "پس‌انداز",
            accountBalance = 120_000.0,
            accountStatus = AccountStatus.ACTIVE,
            defaulted = true
        ),
        AccountBankEntity(
            id = 3L,
            accountNumber = "88325467",
            accountHolderName = "علی رستمی",
            accountType = "سپرده کوتاه‌مدت",
            accountHint = null,
            accountBalance = 432_500.0,
            accountStatus = AccountStatus.DE_ACTIVE,
            defaulted = false
        ),
        AccountBankEntity(
            id = 4L,
            accountNumber = "54432211",
            accountHolderName = "فاطمه نصیری",
            accountType = "سپرده بلندمدت",
            accountHint = "پس‌انداز",
            accountBalance = 10_500_000.0,
            accountStatus = AccountStatus.ACTIVE,
            defaulted = false
        ),
        AccountBankEntity(
            id = 5L,
            accountNumber = "66789900",
            accountHolderName = "مهدی فرجی",
            accountType = "سپرده جاری",
            accountHint = "خرجی",
            accountBalance = 234_000.0,
            accountStatus = AccountStatus.SUSPEND,
            defaulted = false
        ),
        AccountBankEntity(
            id = 6L,
            accountNumber = "00111222",
            accountHolderName = "سمیه موسوی",
            accountType = "سپرده کوتاه‌مدت",
            accountHint = null,
            accountBalance = 847_000.0,
            accountStatus = AccountStatus.ACTIVE,
            defaulted = false
        ),
        AccountBankEntity(
            id = 7L,
            accountNumber = "44556677",
            accountHolderName = "حسین کریمی",
            accountType = "سپرده قرض‌الحسنه",
            accountHint = "خرجی",
            accountBalance = 34_000.0,
            accountStatus = AccountStatus.ACTIVE,
            defaulted = false
        ),
        AccountBankEntity(
            id = 8L,
            accountNumber = "34567890",
            accountHolderName = "نگار یوسفی",
            accountType = "سپرده بلندمدت",
            accountHint = "پس‌انداز",
            accountBalance = 17_500_000.0,
            accountStatus = AccountStatus.DE_ACTIVE,
            defaulted = false
        ),
        AccountBankEntity(
            id = 9L,
            accountNumber = "99887776",
            accountHolderName = "مجید نادری",
            accountType = "سپرده جاری",
            accountHint = "حساب تنخواه",
            accountBalance = 1_250_000.0,
            accountStatus = AccountStatus.ACTIVE,
            defaulted = false
        ),
        AccountBankEntity(
            id = 10L,
            accountNumber = "22334455",
            accountHolderName = "مریم سلطانی",
            accountType = "سپرده قرض‌الحسنه",
            accountHint = null,
            accountBalance = 93_000.0,
            accountStatus = AccountStatus.SUSPEND,
            defaulted = false
        )
    )


    val fakeReason = listOf(
        ReasonEntity(
            id = 1,
            title = "پرداخت حقوق و دستمزد",
            description = "واریز ماهانه حقوق کارمندان و کارکنان به حساب بانکی آن‌ها"
        ),
        ReasonEntity(
            id = 2,
            title = "پرداخت اجاره",
            description = "انتقال وجه بابت اجاره‌بهای ملک یا دفتر کار"
        ),
        ReasonEntity(
            id = 3,
            title = "پرداخت اقساط",
            description = "پرداخت اقساط وام یا خریدهای اقساطی به افراد یا مؤسسات"
        ),
        ReasonEntity(
            id = 4,
            title = "هزینه‌های خدماتی",
            description = "پرداخت وجه بابت خدمات مشاوره، فنی، آموزشی و سایر خدمات مشابه"
        ),
        ReasonEntity(
            id = 5,
            title = "تامین اعتبار شرکت‌ها",
            description = "انتقال وجه بین حساب‌های شرکت یا به تأمین‌کنندگان برای خرید کالا و خدمات"
        ),
        ReasonEntity(
            id = 6,
            title = "پرداخت سود سرمایه‌گذاری",
            description = "واریز سود به حساب سرمایه‌گذاران یا سهامداران"
        ),
        ReasonEntity(
            id = 7,
            title = "کمک مالی",
            description = "کمک به خیریه‌ها، بستگان یا اهدای وجه برای امور خیریه"
        ),
        ReasonEntity(
            id = 8,
            title = "بازپرداخت بدهی",
            description = "پرداخت وجه برای تسویه بدهی‌های شخصی یا تجاری"
        ),
        ReasonEntity(
            id = 9,
            title = "هزینه‌های درمانی",
            description = "پرداخت به مراکز درمانی یا افراد بابت خدمات درمانی"
        ),
        ReasonEntity(
            id = 10,
            title = "سایر",
            description = "سایر دلایل عمومی که در دسته‌بندی مشخصی قرار نمی‌گیرند"
        )
    )


}







