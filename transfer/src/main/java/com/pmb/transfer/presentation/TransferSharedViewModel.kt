package com.pmb.transfer.presentation

import androidx.lifecycle.ViewModel
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
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


    fun setAccount(account: TransactionClientBankEntity) {
        _account.value = account
    }

    fun setAmount(amount: Long) {
        _amount.value = amount
    }

    fun setTransferMethod(transferMethod: TransferMethodEntity) {
        _transferMethod.value = transferMethod
    }


    fun clear() {
        _account.value = null
        _transferMethod.value = null
    }
}