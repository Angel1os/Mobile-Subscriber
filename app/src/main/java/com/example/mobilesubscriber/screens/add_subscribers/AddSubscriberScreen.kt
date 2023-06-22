package com.example.mobilesubscriber.screens.add_subscribers

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobilesubscriber.R
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddSubscriberScreen(
    navController: NavController,
    viewModel: AddSubscriberViewModel = hiltViewModel()
) {
    val subscriberNameState = viewModel.subscriberName.value
    val subscriberEmailState = viewModel.subscriberEmail.value
    val subscriberContactState = viewModel.subscriberContact.value
    val subscriberDobState = viewModel.subscriberDob.value
    val subscriberLocationState = viewModel.subscriberLocation.value
    val subscriberStatusState = viewModel.subscriberStatus.value

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is AddSubscriberViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddSubscriberViewModel.UiEvent.SaveSubscriber -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.subscriber_form_title),
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            "backIcon")
                    }
                },
            )
        },
        floatingActionButton = {
        FloatingActionButton(
            onClick = {viewModel.onEvent(AddSubscriberEvent.SaveSubscriber)
            },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = "Save note"
            )

        }
    },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            myTextField(
                value = subscriberNameState.text,
                onValueChange = {
                    viewModel.onEvent(AddSubscriberEvent.EnteredSubscriberName(it))
                },
                labelName = "Subscriber Name",
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )
            myTextField(
                value = subscriberEmailState.text,
                onValueChange = {
                    viewModel.onEvent(AddSubscriberEvent.EnteredSubscriberEmail(it))
                },
                labelName = "Email",
            )
            myTextField(
                value = subscriberContactState.text,
                onValueChange = {
                    viewModel.onEvent(AddSubscriberEvent.EnteredSubscriberContact(it))
                },
                labelName = "Phone Number",
            )
            myTextField(
                value = subscriberDobState.text,
                onValueChange = {
                    viewModel.onEvent(AddSubscriberEvent.EnteredSubscriberDob(it))
                },
                labelName = "Date of Birth",
            )
            myTextField(
                value = subscriberLocationState.text,
                onValueChange = {
                    viewModel.onEvent(AddSubscriberEvent.EnteredSubscriberLocation(it))
                },
                labelName = "Location",
            )
            Spacer(modifier = Modifier.height(10.dp))
            myDropDownField(subscriberStatusState,viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun myTextField(text: String, value: String, onValueChange: (String) -> Unit, labelName: String){
fun myTextField(value: String, onValueChange: (String) -> Unit, labelName: String){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = labelName)},
//        key
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myDropDownField(subscriberStatusState : SubscriberTextFieldState, viewModel: AddSubscriberViewModel){
    val statusOptions = listOf("Prepaid", "Postpaid")
    var expanded by remember { mutableStateOf(value = false) }
    var selectedOption by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    var icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column{
        OutlinedTextField(
            value = if (selectedOption.isEmpty()) "Status" else selectedOption,
            onValueChange = {selectedOption = it},
            readOnly = true,
            shape = RoundedCornerShape(4.dp),
            trailingIcon = {
                Icon(
                    icon,
                    contentDescription = "",
                Modifier.clickable{expanded = !expanded})
            },
            modifier = Modifier
                .size(280.dp, 50.dp)
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            statusOptions.forEach {
                    statusOption -> DropdownMenuItem(
                text = {
                    Text(text = statusOption, color = Color.Black)
                },
                onClick = {
                    expanded = false
                    selectedOption = statusOption
                },
                colors = MenuDefaults.itemColors()
                )
                subscriberStatusState.text = selectedOption
                viewModel.onEvent(AddSubscriberEvent.EnteredSubscriberStatus(selectedOption))
            }
        }
    }
}

