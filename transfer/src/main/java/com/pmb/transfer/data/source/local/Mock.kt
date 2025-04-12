package com.pmb.transfer.data.source.local

import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.entity.ClientBankEntity
import com.pmb.transfer.domain.entity.PaymentType
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity

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

    val transactionClientBanksEntityEntities = listOf(
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
}







