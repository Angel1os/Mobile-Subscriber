package com.example.mobilesubscriber.screens.subscribers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.use_case.SubscriberUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SubscribersViewModel @Inject constructor(
    private val subscriberUseCases: SubscriberUseCases
): ViewModel() {

    private val _state = mutableStateOf(SubscribersState())
    val state: State<SubscribersState> = _state

    private var getSubscribersJob: Job? = null

    init {
        getSubscribers()
    }

    private fun getSubscribers() {
        getSubscribersJob?.cancel()
        getSubscribersJob = subscriberUseCases.getSubscribers()
            .onEach { subscribers ->
                _state.value = state.value.copy(
                    subscribers = subscribers
                )
            }
            .launchIn(viewModelScope)
    }
}