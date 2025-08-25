package com.pmb.transfer.domain.entity

enum class PaymentType(val value: String, val id: Int) {
    MELLAT_TO_MELLAT("ملت به ملت", 0),
    CARD_TO_CARD("کارت به کارت", 1),
    INTERNAL_PAYA("بین بانکی (پایا)", 2),
    INTERNAL_SATNA("بین بانکی (ساتنا)", 3),
    INTERNAL_BRIDGE("بین بانکی (پل)", 4);

    companion object {
        private val map = values().associateBy(PaymentType::id)

        fun fromId(id: Int): PaymentType = map[id] ?: INTERNAL_BRIDGE
    }
}
