package com.pmb.transfer.presentation

import androidx.lifecycle.ViewModel
import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferConfirmEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TransferSharedViewModel @Inject constructor() : ViewModel() {
    private val _account = MutableStateFlow<TransactionClientBankEntity?>(null)
    val account: StateFlow<TransactionClientBankEntity?> = _account

    private val _amount = MutableStateFlow(0L)
    val amount: StateFlow<Long> = _amount

    private val _transferMethod = MutableStateFlow<TransferMethodEntity?>(null)
    val transferMethod: StateFlow<TransferMethodEntity?> = _transferMethod

    private val _sourceCardBank = MutableStateFlow<CardBankEntity?>(null)
    val sourceCardBank: StateFlow<CardBankEntity?> = _sourceCardBank

    private val _sourceAccountBank = MutableStateFlow<AccountBankEntity?>(null)
    val sourceAccountBank: StateFlow<AccountBankEntity?> = _sourceAccountBank

    private val _transferConfirm = MutableStateFlow<TransferConfirmEntity?>(null)
    val transferConfirm: StateFlow<TransferConfirmEntity?> = _transferConfirm

    private val _transferReason = MutableStateFlow<ReasonEntity?>(null)
    val transferReason: StateFlow<ReasonEntity?> = _transferReason


    private val _transferReceipt = MutableStateFlow<TransferReceiptEntity?>(null)
    val transferReceipt: StateFlow<TransferReceiptEntity?> = _transferReceipt


    fun setAccount(account: TransactionClientBankEntity) {
        _account.value = account
    }

    fun setAmount(amount: Long) {
        _amount.value = amount
    }

    fun setTransferMethod(transferMethod: TransferMethodEntity?) {
        _transferMethod.value = transferMethod
    }

    fun setSourceCardBank(sourceCard: CardBankEntity?) {
        _sourceCardBank.value = sourceCard
    }

    fun setSourceAccountBank(sourceAccount: AccountBankEntity?) {
        _sourceAccountBank.value = sourceAccount
    }

    fun setTransferConfirm(transferConfirm: TransferConfirmEntity) {
        _transferConfirm.value = transferConfirm
    }


    fun setReason(reason: ReasonEntity?) {
        _transferReason.value = reason
    }

    fun setTransferReceipt(receipt: TransferReceiptEntity) {
        _transferReceipt.value = receipt
    }


    fun clear() {
        _account.value = null
        _transferMethod.value = null
        _amount.value = 0L
        _sourceCardBank.value = null
        _sourceAccountBank.value = null
        _transferConfirm.value = null
        _transferReason.value = null
        _transferReceipt.value = null
    }

    fun clearPaymentData(){
        _sourceCardBank.value = null
        _sourceAccountBank.value = null
        _transferConfirm.value = null
        _transferReason.value = null
    }
}