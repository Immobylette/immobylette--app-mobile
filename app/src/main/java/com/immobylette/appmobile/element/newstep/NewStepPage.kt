package com.immobylette.appmobile.element.newstep

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.immobylette.appmobile.R
import com.immobylette.appmobile.data.enum.ElementState
import com.immobylette.appmobile.data.model.Photo
import com.immobylette.appmobile.toasts.ToastService
import com.immobylette.appmobile.ui.shared.component.Slider
import com.immobylette.appmobile.ui.shared.theme.Black
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.ui.shared.component.Button
import com.immobylette.appmobile.ui.shared.component.ListAdditionalPhotos

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun NewStepPage(
    getStepState: () -> ElementState,
    getStepPhotos: () -> MutableList<Photo>,
    getElementName: () -> String,
    getElementDescription: () -> String?,
    changeStepState: (ElementState) -> Unit,
    changeDescription: (String) -> Unit,
    changeErrorDescription: (String) -> Unit,
    onClickDeletePhoto: (Int) -> Unit,
    onClickAddPhoto: () -> Unit,
    onClickCancel: () -> Unit,
    onClickCheckElement: () -> Unit
) {

    val context = LocalContext.current

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    var photos: SnapshotStateList<Photo> = getStepPhotos() as SnapshotStateList<Photo>
    val firstPhotoUrl = photos[0].file!!.toUri()

    var description by remember { mutableStateOf("") }
    var errorDescription by remember { mutableStateOf("") }
    var displayModalError by remember { mutableStateOf(false) }

    val toastTitle = stringResource(id = R.string.toast_title_success)
    val toastPhotoDeletedMessage = stringResource(id = R.string.toast_message_picture_deleted)
    val toastErrorDeclared = stringResource(id = R.string.toast_message_error_declared)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { focusManager.clearFocus() }
    ) {
        Scaffold(
            bottomBar = {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier
                            .width(170.dp),
                        text = stringResource(id = R.string.label_button_cancel),
                        style = MaterialTheme.typography.titleMedium,
                    ){
                        onClickCancel()
                    }
                    Button(
                        isOnLeftSide = false,
                        modifier = Modifier
                            .width(170.dp),
                        text = stringResource(id = R.string.label_button_check),
                        style = MaterialTheme.typography.titleMedium,
                    ) {
                        onClickCheckElement()
                    }
                }
            }
        ) {
            Column (
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = rememberAsyncImagePainter(firstPhotoUrl),
                    contentDescription = stringResource(id = R.string.label_captured_photo),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(500.dp)
                        .fillMaxWidth()
                )
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text(
                            text = getElementName(),
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 30.sp,
                        )
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Black),
                            onClick = { displayModalError = true }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = "",
                                tint = Color.Yellow,
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(
                                text = if(errorDescription == "") stringResource(id = R.string.label_add_error_button)
                                       else stringResource(id = R.string.label_edit_error_button),
                                color = Color.White
                            )
                        }

                    }
                    if(getElementDescription() != null) {
                        Text(
                            text = getElementDescription()!!,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 30.dp)
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.label_state).uppercase(),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Slider(
                        onStateChanged = changeStepState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        state = getStepState()
                    )
                    Text(
                        text = stringResource(id = R.string.label_description),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    TextField(
                        value = description,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedPlaceholderColor = Black,
                            unfocusedPlaceholderColor = Black
                        ),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        onValueChange = {
                            description = it
                            changeDescription(it)
                        },
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color.White)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        trailingIcon = {
                            if (description.isNotEmpty()) {
                                IconButton(onClick = { description = "" }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Close,
                                        contentDescription = null
                                    )
                                }
                            }
                        },
                        placeholder = { Text(
                            text = stringResource(id = R.string.label_description),
                            style = MaterialTheme.typography.bodyMedium) }
                    )
                    ListAdditionalPhotos(
                        photos = photos,
                        onDeletePhoto = {
                            ToastService.showToast(
                                activity = context as Activity,
                                title = toastTitle,
                                message = toastPhotoDeletedMessage,
                                type = ToastService.successStyle
                            )
                            onClickDeletePhoto(it)
                            photos = getStepPhotos() as SnapshotStateList<Photo>
                        },
                        onAddPhoto = onClickAddPhoto
                    )
                }

            }
        }

        if (displayModalError) {
            AlertDialog(
                onDismissRequest = { displayModalError = false }
            ) {
                ErrorModal(
                    errorDescription = errorDescription,
                    elementTitle = getElementName(),
                    onCancelClicked = { displayModalError = false },
                    onConfirmClicked = {
                        ToastService.showToast(
                            activity = context as Activity,
                            title = toastTitle,
                            message = toastErrorDeclared,
                            type = ToastService.successStyle
                        )
                        displayModalError = false
                        errorDescription = it
                        changeErrorDescription(it)
                    }
                )
            }
        }

    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ErrorModal(
    elementTitle: String,
    errorDescription: String,
    onCancelClicked: () -> Unit,
    onConfirmClicked: (String) -> Unit,
    modifier: Modifier = Modifier
){
    var errorDescriptionValue by remember { mutableStateOf(errorDescription) }

    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .width(500.dp)
            .height(250.dp)
            .padding(0.dp)
    ) {
        Scaffold(
            bottomBar = {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        text = stringResource(id = R.string.label_button_cancel),
                        onClick = onCancelClicked,
                    )
                    Button(
                        isOnLeftSide = false,
                        text = stringResource(id = R.string.label_button_confirm),
                        onClick = { onConfirmClicked(errorDescriptionValue) },
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(30.dp)
            ){
                Text(
                    text = "${stringResource(id = R.string.label_report_error_modal)} $elementTitle",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column {
                    Text(
                        text = stringResource(id = R.string.label_error),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = errorDescriptionValue,
                        onValueChange = { newValue ->
                            errorDescriptionValue = newValue
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.label),
                                style = MaterialTheme.typography.headlineMedium
                            )
                        },
                        textStyle = MaterialTheme.typography.headlineMedium,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = stringResource(id = R.string.label_clear),
                                modifier = Modifier.clickable {
                                    errorDescriptionValue = ""
                                }
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        )
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun ErrorModalPreview() {
    ImmobyletteappmobileTheme {
        ErrorModal(
            errorDescription = "", elementTitle = "Test", onCancelClicked = {}, onConfirmClicked = {})
    }
}