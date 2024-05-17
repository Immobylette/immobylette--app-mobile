package com.immobylette.appmobile.element.camera

import androidx.compose.material3.Surface
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.element.current.CurrentElementViewModel
import com.immobylette.appmobile.step.current.CurrentStepViewModel

const val cameraRoute = "camera"

fun NavGraphBuilder.cameraNavigation(
    currentStepViewModel: CurrentStepViewModel,
    currentElementViewModel: CurrentElementViewModel
) {

    composable(cameraRoute) {
        Surface {
            CameraPage(
                getElementName = currentElementViewModel::getName,
                addPhoto = currentStepViewModel::addPhoto
            )
        }
    }
}

fun NavController.navigateToCamera(){
    navigate(cameraRoute)
}