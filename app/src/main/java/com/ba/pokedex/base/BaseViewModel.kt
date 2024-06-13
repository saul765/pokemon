package com.ba.pokedex.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ba.pokedex.utils.livedata.Event
import com.ba.pokedex.coroutines.ICoroutineContextProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


abstract class BaseViewModel : ViewModel(), KoinComponent {

    val contextProvider by inject<ICoroutineContextProvider>()

    val isLoadingEvent = MutableLiveData<Event<Boolean>>()

    fun showProgress() {
        isLoadingEvent.value = Event.Success(true)
    }

    fun hideProgress() {
        isLoadingEvent.value = Event.Success(false)
    }

    inline fun <T> executeInCoroutine(
        event: MutableLiveData<Event<T>>,
        isProgressViewEnabled: Boolean = true,
        crossinline backgroundProcess: suspend () -> T
    ) {
        viewModelScope.launch(contextProvider.getMainContext()) {
            try {
                if (isProgressViewEnabled) {
                    showProgress()
                }

                val result = withContext(contextProvider.getIoContext()) {
                    backgroundProcess.invoke()
                }
                event.value = Event.Success(result)
            } catch (t: Throwable) {
                event.value = Event.Failure(t)
            } finally {
                if (isProgressViewEnabled) {
                    hideProgress()
                }
            }
        }
    }

}