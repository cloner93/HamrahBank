package com.pmb.auth.presentation.register.choose_card.viewModel

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.core.fileManager.FileManager
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.CardFormatModel
import com.pmb.domain.usecae.auth.openAccount.FetchCardFormatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

@HiltViewModel
class ChooseCardViewModel @Inject constructor(
    private val fetchCardFormatUseCase: FetchCardFormatUseCase,
    private val fileManager: FileManager
) : BaseViewModel<ChooseCardViewActions, ChooseCardViewState, ChooseCardViewEvents>(
    ChooseCardViewState()
) {
    init {
        handle(ChooseCardViewActions.GetCardFormatList)
    }

    override fun handle(action: ChooseCardViewActions) {
        when (action) {
            is ChooseCardViewActions.GetCardFormatList -> {
                handleGetFetchCardList()
            }

            is ChooseCardViewActions.SelectCardFormat -> {
                handleSelectCardFormat(action)
            }
            is ChooseCardViewActions.ClearAlertModelState -> {
                setState {
                    it.copy(
                        alertModelState = null
                    )
                }
            }
        }
    }

    private fun handleSelectCardFormat(action: ChooseCardViewActions.SelectCardFormat) {
        setState {
            it.copy(
                selectedCard = action.card
            )
        }
    }

    private fun handleGetFetchCardList() {
        viewModelScope.launch {
            fetchCardFormatUseCase(Unit).collectLatest { result ->
                when (result) {
                    is Result.Loading -> {
                        setState { it.copy(isLoading = true) }
                    }

                    is Result.Error -> {
                        Log.d("Error", "error")
                        setState {
                            it.copy(
                                isLoading = false, alertModelState = AlertModelState.Dialog(
                                title = "خطا",
                                description = " ${result.message}",
                                positiveButtonTitle = "تایید",
                                onPositiveClick = {
                                    setState { state -> state.copy(alertModelState = null) }
                                }
                            ))
                        }
                    }

                    is Result.Success -> {
                        val processedCardList = coroutineScope {
                            result.data.map { response ->
                                val imageFile = decodeBase64ToFile(listOf(response.imageStr))
                                val backImageFile =
                                    decodeBase64ToFile(listOf(response.backImageStr))
                                CardFormatModel(
                                    incomeType = response.incomeType,
                                    actionNumber = response.actionNumber,
                                    formatId = response.formatId,
                                    cardGroup = response.cardGroup,
                                    imageUris = imageFile,
                                    backImageUris = backImageFile,
                                    formatDescription = response.formatDescription,
                                    commonAmount = response.commonAmount,
                                    isHorizontal = isImageHorizontal(imageFile)
                                )
                            }
                        }
                        setState {
                            it.copy(
                                isLoading = false,
                                horizontalCardList = processedCardList.filter { it.isHorizontal == true },
                                verticalCardList = processedCardList.filter { it.isHorizontal == false },
                                selectedCard = processedCardList.first { it.isHorizontal == false }
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun decodeBase64ToFile(base64List: List<String?>): File? =
        withContext(Dispatchers.IO) {
            base64List.firstOrNull()?.let { base64 ->
                try {
                    val decodedBytes = Base64.decode(
                        base64.toByteArray(Charsets.UTF_8),
                        Base64.NO_WRAP
                    )

                    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
                    if (bitmap == null) return@withContext null

                    val file = fileManager.createImageFile()
                    val success = fileManager.writeFile(file.absolutePath, decodedBytes)
                    if (success) file else null

                } catch (e: Exception) {
                    Log.e("Base64", "Failed to decode base64", e)
                    null
                }
            }

        }

    fun isImageHorizontal(file: File?): Boolean {
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }

        BitmapFactory.decodeFile(file?.absolutePath, options)

        val width = options.outWidth
        val height = options.outHeight

        return width >= height // landscape if width is greater
    }
}