package com.pmb.core.platform

sealed class AlertModelState(val alertType: AlertType) {
    data class SnackBar(
        val type: AlertType = AlertType.Error,
        val message: String,
        val buttonTitle:String?=null,
    ) : AlertModelState(alertType = type)

    data class Dialog(
        val type: AlertType = AlertType.Error,
        val title:String,
        val description: String?=null,
        val positiveButtonTitle:String?=null,
        val negativeButtonTitle:String?=null,
    ) : AlertModelState(alertType = type)
}

enum class AlertType {
    Success, Error, Warning
}