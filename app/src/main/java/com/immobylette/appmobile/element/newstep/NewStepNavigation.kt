package com.immobylette.appmobile.element.newstep

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.element.current.CurrentElementViewModel
import com.immobylette.appmobile.inventory.current.CurrentInventoryViewModel
import com.immobylette.appmobile.room.elements.ElementsViewModel
import com.immobylette.appmobile.step.current.CurrentStepViewModel

const val newStepRoute = "new-step"

fun NavGraphBuilder.newStepNavigation(
    newStepViewModel: NewStepViewModel,
    currentElementViewModel: CurrentElementViewModel,
    currentStepViewModel: CurrentStepViewModel,
    currentInventoryViewModel: CurrentInventoryViewModel,
    elementsViewModel: ElementsViewModel,
    navigateToTakeAnotherPicture: () -> Unit,
    navigateToWalls: () -> Unit,
    navigateToElements: () -> Unit,
    navigateGoToRoom: () -> Unit,
) {

    composable(newStepRoute) {
        NewStepPage(
            getStepState = currentStepViewModel::getState,
            getStepPhotos = currentStepViewModel::getPhotos,
            getElementName = currentElementViewModel::getName,
            getElementDescription = currentElementViewModel::getDescription,
            changeStepState = currentStepViewModel::changeState,
            changeDescription = currentStepViewModel::changeDescription,
            changeErrorDescription = currentStepViewModel::changeErrorDescription,
            onClickAddPhoto = navigateToTakeAnotherPicture,
            onClickDeletePhoto = currentStepViewModel::removePhoto,
            onClickCancel = {
                if (currentStepViewModel.getStep().isWall) navigateToWalls() else navigateToElements()
            },
            onClickCheckElement =  { loadingFinished ->
                val currentInventoryId = currentInventoryViewModel.getCurrentInventory()
                val currentElementId = currentElementViewModel.getId()
                val currentStep = currentStepViewModel.getStep()

                newStepViewModel.addStep(currentInventoryId, currentElementId, currentStep) {
                    loadingFinished()
                    if (currentStep.isWall) navigateToWalls() else {
                        val checkedElements = elementsViewModel.elements.value.elements.filter { it.checked }
                        if (checkedElements.size == elementsViewModel.elements.value.elements.size - 1) navigateGoToRoom() else navigateToElements()
                    }
                }
            }
        )
    }
}
fun NavController.navigateToNewStep() {
    navigate(newStepRoute)
}