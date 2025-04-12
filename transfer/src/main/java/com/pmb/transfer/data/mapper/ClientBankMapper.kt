package com.pmb.transfer.data.mapper

import com.pmb.core.platform.BaseMapper
import com.pmb.transfer.data.model.ClientBankDto
import com.pmb.transfer.domain.entity.ClientBankEntity
import javax.inject.Inject

class ClientBankMapper @Inject constructor() : BaseMapper<ClientBankDto, ClientBankEntity> {
    override fun map(dataModel: ClientBankDto): ClientBankEntity {
        return ClientBankEntity(
            name = "",
            phoneNumber = "",
            profileUrl = "",
            cardNumber = "",
            accountNumber = "",
            iban = ""
        )
    }
}