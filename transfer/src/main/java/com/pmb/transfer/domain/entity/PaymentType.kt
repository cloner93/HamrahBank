package com.pmb.transfer.domain.entity

enum class PaymentType(val value: String) {
    CARD_TO_CARD("کارت به کارت"),
    INTERNAL_SATNA("بین بانکی (ساتنا)"),
    INTERNAL_PAYA("بین بانکی (پایا)"),
    INTERNAL_BRIDGE("بین بانکی (پل)"),
    MELLAT_TO_MELLAT("ملت به ملت")
}
