package com.ba.pokedex.core.base.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.ba.pokedex.R
import com.ba.pokedex.core.coroutines.ICoroutineContextProvider
import com.ba.pokedex.core.domain.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


abstract class BaseViewModel : ViewModel(), KoinComponent {

    val contextProvider by inject<ICoroutineContextProvider>()

    private val _loadingState = MutableStateFlow(LoadingState())

    val loadingState = _loadingState.asStateFlow()

    private val _baseErrorState = MutableStateFlow<Throwable?>(null)

    open val baseErrorState = _baseErrorState.asStateFlow()

    open fun showError(throwable: Throwable) {
        _baseErrorState.update { throwable }
    }

    open fun hideError() {
        _baseErrorState.update { null }
    }

    open fun showLoading(@StringRes message: Int = R.string.local_default_loading_message) {
        _loadingState.update {
            it.copy(isLoading = true, message = message)
        }
    }

    open fun hideLoading() {
        _loadingState.update { it.copy(isLoading = false) }
    }


}