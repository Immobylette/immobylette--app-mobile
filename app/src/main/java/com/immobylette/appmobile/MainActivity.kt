package com.immobylette.appmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.immobylette.appmobile.agent.current.CurrentAgentViewModel
import com.immobylette.appmobile.agent.selection.AgentSelectionViewModel
import com.immobylette.appmobile.agent.selection.agentSelectionNavigation
import com.immobylette.appmobile.agent.selection.navigateToAgentSelection
import com.immobylette.appmobile.confirmation.ConfirmationViewModel
import com.immobylette.appmobile.confirmation.confirmationNavigation
import com.immobylette.appmobile.confirmation.navigateToConfirmation
import com.immobylette.appmobile.element.camera.cameraNavigation
import com.immobylette.appmobile.element.camera.navigateToTakeAnotherPicture
import com.immobylette.appmobile.element.camera.navigateToTakePicture
import com.immobylette.appmobile.element.current.CurrentElementViewModel
import com.immobylette.appmobile.ending.endingNavigation
import com.immobylette.appmobile.element.newstep.NewStepViewModel
import com.immobylette.appmobile.element.newstep.navigateToNewStep
import com.immobylette.appmobile.element.newstep.newStepNavigation
import com.immobylette.appmobile.ending.navigateToEndingPage
import com.immobylette.appmobile.loading.loadingNavigation
import com.immobylette.appmobile.loading.navigateToLoadingPage
import com.immobylette.appmobile.property.current.CurrentPropertyViewModel
import com.immobylette.appmobile.property.selection.PropertySelectionViewModel
import com.immobylette.appmobile.inventory.current.CurrentInventoryViewModel
import com.immobylette.appmobile.inventory.summary.InventorySummaryViewModel
import com.immobylette.appmobile.inventory.summary.inventorySummaryNavigation
import com.immobylette.appmobile.inventory.summary.navigateToInventorySummary
import com.immobylette.appmobile.property.selection.navigateToPropertySelection
import com.immobylette.appmobile.property.selection.propertySelectionNavigation
import com.immobylette.appmobile.room.current.CurrentRoomViewModel
import com.immobylette.appmobile.room.elements.ElementsViewModel
import com.immobylette.appmobile.room.elements.WallsViewModel
import com.immobylette.appmobile.room.elements.elementsNavigation
import com.immobylette.appmobile.room.elements.navigateToElements
import com.immobylette.appmobile.room.elements.navigateToWalls
import com.immobylette.appmobile.room.gotoroom.GoToRoomViewModel
import com.immobylette.appmobile.room.gotoroom.goToRoomNavigation
import com.immobylette.appmobile.room.gotoroom.navigateToGoToRoom
import com.immobylette.appmobile.signature.signatureNavigation
import com.immobylette.appmobile.signature.current.CurrentSignatureViewModel
import com.immobylette.appmobile.signature.navigateToTenantSignature
import com.immobylette.appmobile.step.current.CurrentStepViewModel
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.utils.LocationHelper

class MainActivity : ComponentActivity() {
    private val currentPropertyViewModel by viewModels<CurrentPropertyViewModel>()
    private val currentAgentViewModel by viewModels<CurrentAgentViewModel>()
    private val currentInventoryViewModel by viewModels<CurrentInventoryViewModel>()
    private val currentStepViewModel by viewModels<CurrentStepViewModel>()
    private val currentElementViewModel by viewModels<CurrentElementViewModel>()
    private val currentRoomViewModel by viewModels<CurrentRoomViewModel>()
    private val currentSignatureViewModel by viewModels<CurrentSignatureViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        LocationHelper.init(this)

        super.onCreate(savedInstanceState)
        setContent {
            val agentSelectionViewModel by viewModels<AgentSelectionViewModel>()
            val propertySelectionViewModel by viewModels<PropertySelectionViewModel>()
            val confirmationViewModel by viewModels<ConfirmationViewModel>()
            val newStepViewModel by viewModels<NewStepViewModel>()
            val goToRoomViewModel by viewModels<GoToRoomViewModel>()
            val wallsViewModel by viewModels<WallsViewModel>()
            val elementsViewModel by viewModels<ElementsViewModel>()
            val inventorySummaryViewModel by viewModels<InventorySummaryViewModel>()
            val navController = rememberNavController()

            ImmobyletteappmobileTheme {
                NavHost(
                    navController = navController,
                    startDestination = "agent-selection",
                ){
                    agentSelectionNavigation(
                        agentSelectionViewModel = agentSelectionViewModel,
                        currentAgentViewModel = currentAgentViewModel,
                        onNavigateToAgentSelected = navController::navigateToLoadingPage
                    )

                    loadingNavigation(
                        onNavigateToLoadingFinished = navController::navigateToPropertySelection
                    )

                    propertySelectionNavigation(
                        propertySelectionViewModel = propertySelectionViewModel,
                        currentPropertyViewModel = currentPropertyViewModel,
                        onNavigateToChangeAgent = navController::navigateToAgentSelection,
                        onNavigateToPropertySelected = navController::navigateToConfirmation
                    )

                    confirmationNavigation(
                        confirmationViewModel = confirmationViewModel,
                        currentPropertyViewModel = currentPropertyViewModel,
                        currentInventoryViewModel = currentInventoryViewModel,
                        currentAgentViewModel = currentAgentViewModel,
                        onNavigateToConfirmed = navController::navigateToGoToRoom,
                        onNavigateToPropertySelection = navController::navigateToPropertySelection
                    )

                    goToRoomNavigation(
                        goToRoomViewModel = goToRoomViewModel,
                        currentRoomViewModel = currentRoomViewModel,
                        currentInventoryViewModel = currentInventoryViewModel,
                        onNavigateToRoomElements = navController::navigateToWalls,
                        onNavigateToPropertySelection = navController::navigateToPropertySelection,
                        onNavigateToInventorySummary = navController::navigateToInventorySummary
                    )

                    elementsNavigation(
                        wallsViewModel = wallsViewModel,
                        elementsViewModel = elementsViewModel,
                        currentRoomViewModel = currentRoomViewModel,
                        currentPropertyViewModel = currentPropertyViewModel,
                        currentInventoryViewModel = currentInventoryViewModel,
                        currentElementViewModel = currentElementViewModel,
                        currentStepViewModel = currentStepViewModel,
                        onNavigateToTakePicture = navController::navigateToTakePicture,
                        onNavigateToElements = navController::navigateToElements,
                        onNavigateToInventorySummary = navController::navigateToInventorySummary,
                        onNavigateToCurrentRoom = navController::navigateToGoToRoom,
                        onNavigateToPropertySelection = navController::navigateToPropertySelection
                    )

                    cameraNavigation(
                        currentStepViewModel = currentStepViewModel,
                        currentElementViewModel = currentElementViewModel,
                        navigateToElementState = navController::navigateToNewStep,
                        onCancelClicked = { navController.navigateUp() }
                    )

                    newStepNavigation(
                        newStepViewModel = newStepViewModel,
                        currentElementViewModel = currentElementViewModel,
                        currentStepViewModel = currentStepViewModel,
                        currentInventoryViewModel = currentInventoryViewModel,
                        navigateToTakeAnotherPicture = navController::navigateToTakeAnotherPicture,
                        navigateToWalls = navController::navigateToWalls,
                        navigateToElements = navController::navigateToElements
                    )

                    signatureNavigation(
                        currentSignatureViewModel = currentSignatureViewModel,
                        currentAgentViewModel = currentAgentViewModel,
                        currentPropertyViewModel = currentPropertyViewModel,
                        onNavigateToSignatureTenant = navController::navigateToTenantSignature,
                        onNavigateToEndingPage = navController::navigateToEndingPage
                    )

                    inventorySummaryNavigation(
                        currentInventoryViewModel = currentInventoryViewModel,
                        inventorySummaryViewModel = inventorySummaryViewModel,
                        onNavigateToPropertySelection = navController::navigateToPropertySelection,
                        onNavigateToSignature = {}
                    )

                    endingNavigation(
                        onNavigateToWaitingFinished = navController::navigateToPropertySelection
                    )
                }
            }
        }
    }
}