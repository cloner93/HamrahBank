package com.pmb.core.platform

sealed class AlertModelState(val alertType: AlertType) {
    data class SnackBar(
        val type: AlertType = AlertType.Error,
        val message: String,
        val buttonTitle: String? = null,
        val onActionPerformed: () -> Unit,
        val onDismissed: () -> Unit
    ) : AlertModelState(alertType = type)

    data class Dialog(
        val type: AlertType = AlertType.Error,
        val title: String,
        val description: String? = null,
        val positiveButtonTitle: String? = null,
        val negativeButtonTitle: String? = null,
        val onPositiveClick: () -> Unit,
        val onNegativeClick: () -> Unit,
    ) : AlertModelState(alertType = type)
}

enum class AlertType {
    Success, Error, Warning
}