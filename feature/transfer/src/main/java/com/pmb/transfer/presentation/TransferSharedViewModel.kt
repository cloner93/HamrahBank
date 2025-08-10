package com.pmb.transfer.presentation

import androidx.lifecycle.ViewModel
import com.pmb.transfer.domain.entity.CardVerificationEntity
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.domain.entity.TransferSourceEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TransferSharedViewModel @Inject constructor() : ViewModel() {
    private val _destinationAccount = MutableStateFlow<TransactionClientBankEntity?>(null)
    val destinationAccount: StateFlow<TransactionClientBankEntity?> = _destinationAccount

    private val _amount = MutableStateFlow(0.0)
    val amount: StateFlow<Double> = _amount

    private val _transferMethods = MutableStateFlow<List<TransferMethodEntity>>(listOf())
    val transferMethods: StateFlow<List<TransferMethodEntity>> = _transferMethods

    private val _transferMethod = MutableStateFlow<TransferMethodEntity?>(null)
    val transferMethod: StateFlow<TransferMethodEntity?> = _transferMethod

    private val _source = MutableStateFlow<TransferSourceEntity?>(null)
    val source: StateFlow<TransferSourceEntity?> = _source

    private val _cardVerification = MutableStateFlow<CardVerificationEntity?>(null)
    val cardVerification: StateFlow<CardVerificationEntity?> = _cardVerification

    private val _transferReasons = MutableStateFlow<List<ReasonEntity>>(listOf())
    val transferReasons: StateFlow<List<ReasonEntity>> = _transferReasons

    private val _transferReason = MutableStateFlow<ReasonEntity?>(null)
    val transferReason: StateFlow<ReasonEntity?> = _transferReason


    private val _transferReceipt = MutableStateFlow<TransferReceiptEntity?>(null)
    val transferReceipt: StateFlow<TransferReceiptEntity?> = _transferReceipt


    fun setDestinationAccount(account: TransactionClientBankEntity) {
        _destinationAccount.value = account
    }

    fun setAmount(amount: Double) {
        _amount.value = amount
    }


    fun setTransferMethods(transferMethods: List<TransferMethodEntity>) {
        _transferMethods.value = transferMethods
    }

    fun setTransferMethod(transferMethod: TransferMethodEntity?) {
        _transferMethod.value = transferMethod
    }

    fun setSource(source: TransferSourceEntity?) {
        _source.value = source
    }

    fun setTransferVerificationCard(cardVerification: CardVerificationEntity) {
        _cardVerification.value = cardVerification
    }

    fun setTransferReasons(reasons: List<ReasonEntity>?) {
        _transferReasons.value = reasons ?: listOf()
    }

    fun setReason(reason: ReasonEntity?) {
        _transferReason.value = reason
    }

    fun setTransferReceipt(receipt: TransferReceiptEntity) {
        _transferReceipt.value = TransferReceiptEntity(
            destination = _destinationAccount.value ?: receipt.destination,
            status = receipt.status,
            amount = _amount.value,
            date = System.currentTimeMillis(),
            paymentType = _transferMethod.value?.paymentType ?: receipt.paymentType,
            source = _source.value ?: receipt.source,
            trackingNumber = receipt.trackingNumber,
            message = receipt.message
        )
    }


    fun clear() {
        _destinationAccount.value = null
        _transferMethod.value = null
        _amount.value = 0.0
        _source.value = null
        _source.value = null
        _cardVerification.value = null
        _transferReason.value = null
        _transferReceipt.value = null
    }

    fun clearPaymentData() {
        _source.value = null
        _source.value = null
        _cardVerification.value = null
        _transferReason.value = null
    }
}