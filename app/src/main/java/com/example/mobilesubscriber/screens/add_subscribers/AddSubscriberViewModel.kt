package com.example.mobilesubscriber.screens.add_subscribers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesubscriber.data.model.InvalidSubscriberException
import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.use_case.SubscriberUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSubscriberViewModel @Inject constructor(
    private val subscriberUseCases: SubscriberUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _subscriberName = mutableStateOf(
        SubscriberTextFieldState()
    )
    val subscriberName: State<SubscriberTextFieldState> = _subscriberName

    private val _subscriberEmail = mutableStateOf(
        SubscriberTextFieldState()
    )
    val subscriberEmail: State<SubscriberTextFieldState> = _subscriberEmail

    private val _subscriberContact = mutableStateOf(
        SubscriberTextFieldState()
    )
    val subscriberContact: State<SubscriberTextFieldState> = _subscriberContact

    private val _subscriberDob = mutableStateOf(
        SubscriberTextFieldState()
    )
    val subscriberDob: State<SubscriberTextFieldState> = _subscriberDob

    private val _subscriberLocation = mutableStateOf(
        SubscriberTextFieldState()
    )
    val subscriberLocation: State<SubscriberTextFieldState> = _subscriberLocation

    private val _subscriberStatus = mutableStateOf(
        SubscriberTextFieldState()
    )
    val subscriberStatus: State<SubscriberTextFieldState> = _subscriberStatus

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentSubscriberId: Int? = null

    init {
        savedStateHandle.get<Int>("subscriberId")?.let { subscriberId->
            if (subscriberId != -1){
                viewModelScope.launch {
                    subscriberUseCases.getSubscriber(subscriberId)?.also { subscriber ->
                        currentSubscriberId = subscriber.id
                        _subscriberName.value = subscriberName.value.copy(
                            text = subscriber.name,
                        )
                        _subscriberEmail.value = subscriberEmail.value.copy(
                            text = subscriber.email,
                        )
                        _subscriberContact.value = subscriberContact.value.copy(
                            text = subscriber.contact,
                        )
                        _subscriberDob.value = subscriberDob.value.copy(
                            text = subscriber.doB
                        )
                        _subscriberLocation.value = subscriberLocation.value.copy(
                            text = subscriber.location,
                        )
                        _subscriberStatus.value = subscriberStatus.value.copy(
                            text = subscriber.status,
                        )
                    }
                }

            }
        }
    }

    fun onEvent(event: AddSubscriberEvent){
        when(event){
            is AddSubscriberEvent.EnteredSubscriberName -> {
                _subscriberName.value = subscriberName.value.copy(
                    text = event.value
                )
            }
            is AddSubscriberEvent.EnteredSubscriberEmail -> {
                _subscriberEmail.value = subscriberEmail.value.copy(
                    text = event.value
                )
            }
            is AddSubscriberEvent.EnteredSubscriberContact -> {
                _subscriberContact.value = subscriberContact.value.copy(
                    text = event.value
                )
            }
            is AddSubscriberEvent.EnteredSubscriberDob -> {
                _subscriberDob.value = subscriberDob.value.copy(
                    text = event.value
                )
            }
            is AddSubscriberEvent.EnteredSubscriberLocation -> {
                _subscriberLocation.value = subscriberLocation.value.copy(
                    text = event.value
                )
            }
            is AddSubscriberEvent.EnteredSubscriberStatus -> {
                _subscriberStatus.value = subscriberStatus.value.copy(
                    text = event.value
                )
            }
            is AddSubscriberEvent.SaveSubscriber -> {
                viewModelScope.launch {
                    try {
                        subscriberUseCases.addSubscriber(
                            Subscriber(
                                name = subscriberName.value.text,
                                email = subscriberEmail.value.text,
                                contact = subscriberContact.value.text,
                                doB = subscriberDob.value.text,
                                location = subscriberLocation.value.text,
                                status = subscriberStatus.value.text,
                                timestamp = System.currentTimeMillis(),
                                id = currentSubscriberId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveSubscriber)
                    }catch(e: InvalidSubscriberException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save subscriber"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar( val message: String): UiEvent()
        object SaveSubscriber: UiEvent()
    }
}


