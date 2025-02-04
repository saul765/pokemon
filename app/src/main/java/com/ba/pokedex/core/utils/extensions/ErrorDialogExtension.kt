package com.ba.pokedex.core.utils.extensions


import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ba.pokedex.core.base.state.BaseDialogState
import com.ba.pokedex.core.data.UIStateStatus
import com.ba.pokedex.core.data.exception.ErrorMessage
import com.ba.pokedex.core.data.exception.getErrorMessage

import com.ba.pokedex.core.design_system.ErrorDialogInformation


@Composable
fun UIStateStatus.Error<*>.ShowErrorDialog() {
    val error = exception.getErrorMessage()
    val failureReason = when (val second = error.second) {
        is ErrorMessage.Resource -> Pair(
            stringResource(error.first),
            stringResource(second.resId)
        )

        is ErrorMessage.Text -> Pair(
            stringResource(error.first),
            second.text
        )
    }

    ErrorDialogInformation(
        dialogText = failureReason.first,
        dialogAssistText = failureReason.second
    )
}

@Composable
fun Throwable.ShowErrorDialog(dialogState: BaseDialogState, onDismissRequest: () -> Unit = {}) {
    val error = getErrorMessage()
    val failureReason = when (val second = error.second) {
        is ErrorMessage.Resource -> Pair(
            stringResource(error.first),
            stringResource(second.resId)
        )

        is ErrorMessage.Text -> Pair(
            stringResource(error.first),
            second.text
        )
    }

    ErrorDialogInformation(
        state = dialogState,
        onDismissRequest = onDismissRequest,
        dialogText = failureReason.first,
        dialogAssistText = failureReason.second
    )
}