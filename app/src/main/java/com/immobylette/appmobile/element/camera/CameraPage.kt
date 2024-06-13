package com.immobylette.appmobile.element.camera

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.immobylette.appmobile.R
import com.immobylette.appmobile.data.model.Photo
import com.immobylette.appmobile.toasts.ToastService
import com.immobylette.appmobile.ui.shared.component.PhotoDescription
import com.immobylette.appmobile.ui.shared.custommodifier.coloredShadow
import com.immobylette.appmobile.ui.shared.theme.BlueLight
import com.immobylette.appmobile.ui.shared.theme.Grey
import com.immobylette.appmobile.ui.shared.theme.Pink
import com.immobylette.appmobile.utils.executor
import com.immobylette.appmobile.utils.getCameraProvider
import com.immobylette.appmobile.utils.takePicture
import com.immobylette.appmobile.ui.shared.component.Button
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CameraPage(
    takingAnotherPicture: Boolean = false,
    getElementName: () -> String,
    addPhoto: (Photo) -> Unit,
    navigateToElementState: () -> Unit,
    onCancelClicked: () -> Unit
) {
    val photo  by remember { mutableStateOf(Photo(file = null)) }
    val elementName = getElementName()
    var displayModal by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var showCamera by remember { mutableStateOf(false) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }

    val toastTitle = stringResource(id = R.string.toast_title_success)
    val toastPictureAddedMessage = stringResource(id = R.string.toast_message_picture_added)

    val imageCaptureUseCase by remember {
        mutableStateOf(
            ImageCapture.Builder()
                .setCaptureMode(CAPTURE_MODE_MAXIMIZE_QUALITY)
                .build()
        )
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            showCamera = true
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(cameraPermissionState) {
        if(ContextCompat.checkSelfPermission(
                context,
                cameraPermissionState.permission
            ) == PackageManager.PERMISSION_GRANTED) {
            showCamera = true
        } else {
            requestPermissionLauncher.launch(cameraPermissionState.permission)
        }
    }

    LaunchedEffect(previewUseCase) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, previewUseCase, imageCaptureUseCase
        )
    }

    Box(
        modifier = if (displayModal) Modifier.blur(radius = 10.dp) else Modifier,
    ) {
                AndroidView(
                    factory = { context ->
                        val previewView = PreviewView(context).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                        }
                        previewUseCase = Preview.Builder()
                                .build()
                                .also {
                                    it.setSurfaceProvider(previewView.surfaceProvider)
                                }
                        previewView
                    }
                )
                Surface(
                    shadowElevation = 10.dp,
                    modifier = Modifier
                        .padding(30.dp)
                        .align(Alignment.TopCenter)
                        .coloredShadow(
                            color = Grey,
                            offsetY = 5.dp,
                            blurRadius = 5.dp,
                            spread = 1f,
                        )
                ) {
                    Text(
                        text = "${if(takingAnotherPicture) stringResource(id = R.string.label_button_take_new_picture) else stringResource(id = R.string.label_button_take_picture)} : $elementName",
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                CapturePictureButton(
                    modifier = Modifier
                        .size(180.dp)
                        .padding(30.dp)
                        .align(Alignment.BottomEnd),
                    onClick = {
                        coroutineScope.launch {
                            imageCaptureUseCase.takePicture(context.executor).let {
                                photo.file = it
                                displayModal = true
                            }
                        }
                    }
                )
                Button (
                    text = stringResource(id = R.string.label_button_cancel),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(0.dp),
                    onClick = onCancelClicked
                )
    }
    if(displayModal) {
        AlertDialog(
            onDismissRequest = { displayModal = false }
        ) {
            PhotoDescription(
                uri = photo.file!!.toUri(),
                onNextClick = {
                    photo.description = it
                    addPhoto(photo)
                    displayModal = false
                    ToastService.showToast(
                        activity =  context as Activity,
                        title = toastTitle,
                        message = toastPictureAddedMessage,
                        type = ToastService.successStyle
                    )
                    navigateToElementState()
                },
            )
        }
    }
}

@Composable
fun CapturePictureButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(15.dp, Pink),
        contentPadding = PaddingValues(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = BlueLight,
            ),
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(R.drawable.camera),
            contentDescription = "",
            tint = Pink,
            modifier = Modifier.fillMaxSize(0.6f)
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun PreviewCapturePictureButton() {
    Column(
        modifier = Modifier
            .size(180.dp)
            .wrapContentSize()
    ) {
        CapturePictureButton(
            modifier = Modifier
                .size(180.dp)
                .padding(30.dp),
            onClick = {}
        )
    }
}


