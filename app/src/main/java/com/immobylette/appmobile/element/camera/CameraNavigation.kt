package com.immobylette.appmobile.element.camera

import androidx.compose.material3.Surface
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.element.current.CurrentElementViewModel
import com.immobylette.appmobile.step.current.CurrentStepViewModel

const val takePictureRoute = "take-picture"
const val takeAnotherPictureRoute = "take-other-picture"

fun NavGraphBuilder.cameraNavigation(
    currentStepViewModel: CurrentStepViewModel,
    currentElementViewModel: CurrentElementViewModel,
    navigateToElementState: () -> Unit,
    onCancelClicked: () -> Unit
) {
    composable(takePictureRoute) {
        Surface {
            CameraPage(
                takingAnotherPicture = false,
                getElementName = currentElementViewModel::getName,
                addPhoto = currentStepViewModel::addPhoto,
                navigateToElementState = navigateToElementState,
                onCancelClicked = onCancelClicked
            )
        }
    }
    composable(takeAnotherPictureRoute) {
        Surface {
            CameraPage(
                takingAnotherPicture = true,
                getElementName = currentElementViewModel::getName,
                addPhoto = currentStepViewModel::addPhoto,
                navigateToElementState = navigateToElementState,
                onCancelClicked = onCancelClicked
            )
        }
    }
}

fun NavController.navigateToTakePicture(){
    navigate(takePictureRoute)
}

fun NavController.navigateToTakeAnotherPicture(){
    navigate(takeAnotherPictureRoute)
}