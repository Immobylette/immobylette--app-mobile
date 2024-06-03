package com.immobylette.appmobile.room.elements

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import com.immobylette.appmobile.element.current.CurrentElementViewModel
import com.immobylette.appmobile.inventory.current.CurrentInventoryViewModel
import com.immobylette.appmobile.property.current.CurrentPropertyViewModel
import com.immobylette.appmobile.room.current.CurrentRoomViewModel
import com.immobylette.appmobile.step.current.CurrentStepViewModel
import java.util.UUID

const val wallsRoute = "walls"
const val elementsRoute = "elements"

fun NavGraphBuilder.elementsNavigation(
    wallsViewModel: WallsViewModel,
    elementsViewModel: ElementsViewModel,
    currentRoomViewModel: CurrentRoomViewModel,
    currentPropertyViewModel: CurrentPropertyViewModel,
    currentInventoryViewModel: CurrentInventoryViewModel,
    currentElementViewModel: CurrentElementViewModel,
    currentStepViewModel: CurrentStepViewModel,
    onNavigateToTakePicture: () -> Unit,
    onNavigateToElements: () -> Unit,
    onNavigateToInventorySummary: () -> Unit,
    onNavigateToCurrentRoom: () -> Unit,
    onNavigateToPropertySelection: () -> Unit
) {
    composable(wallsRoute){
        val walls: ElementListState by wallsViewModel.walls.collectAsStateWithLifecycle()

        ElementsPage(
            isWallsPage = true,
            elements = walls,
            getNbRooms = currentPropertyViewModel::getNbRooms,
            getRoomNumber = currentRoomViewModel::getOrderNb,
            getNbWalls = currentRoomViewModel::getNbWalls,
            getNbDoors = currentRoomViewModel::getNbDoors,
            getNbWindows = currentRoomViewModel::getNbWindows,
            onClickSameState = { element -> wallsViewModel.check(element.id, element.state, currentInventoryViewModel.getCurrentInventory())},
            setCurrentElement = currentElementViewModel::setCurrentElement,
            resetStep = { state -> currentStepViewModel.resetStepWithStateAndIsWall(state, true) },
            onClickOnNext = {
                wallsViewModel.checkAll(currentInventoryViewModel.getCurrentInventory())
                onNavigateToElements()
            },
            fetchElements = { wallsViewModel.fetchWallList(currentInventoryViewModel.getCurrentInventory()) },
            fetchElement = { elementId: UUID -> wallsViewModel.fetchWall(currentInventoryViewModel.getCurrentInventory(), elementId) },
            onNavigateToTakePicture = onNavigateToTakePicture,
            onNavigateToPropertySelection = onNavigateToPropertySelection
        )
    }

    composable(elementsRoute) {
        val elements: ElementListState by elementsViewModel.elements.collectAsStateWithLifecycle()

        ElementsPage(
            elements = elements,
            getNbRooms = currentPropertyViewModel::getNbRooms,
            getRoomNumber = currentRoomViewModel::getOrderNb,
            getNbWalls = currentRoomViewModel::getNbWalls,
            getNbDoors = currentRoomViewModel::getNbDoors,
            getNbWindows = currentRoomViewModel::getNbWindows,
            onClickSameState = { element -> elementsViewModel.check(element.id, element.state)},
            setCurrentElement = currentElementViewModel::setCurrentElement,
            resetStep = { state -> currentStepViewModel.resetStepWithStateAndIsWall(state, false) },
            onClickOnNext = {
                elementsViewModel.checkAll()
                if (currentRoomViewModel.getOrderNb() + 1 == currentPropertyViewModel.getNbRooms()) {
                    onNavigateToInventorySummary()
                } else {
                    onNavigateToCurrentRoom()
                }
            },
            fetchElements = { elementsViewModel.fetchElementList(currentInventoryViewModel.getCurrentInventory()) },
            fetchElement = { elementId: UUID -> elementsViewModel.fetchElement(currentInventoryViewModel.getCurrentInventory(), elementId) },
            onNavigateToTakePicture = onNavigateToTakePicture,
            onNavigateToPropertySelection = onNavigateToPropertySelection
        )
    }
}

fun NavController.navigateToWalls() {
    navigate(wallsRoute)
}

fun NavController.navigateToElements() {
    navigate(elementsRoute)
}