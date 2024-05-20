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
import java.util.UUID

const val wallsRoute = "walls"

fun NavGraphBuilder.elementsNavigation(
    wallsViewModel: WallsViewModel,
    currentRoomViewModel: CurrentRoomViewModel,
    currentPropertyViewModel: CurrentPropertyViewModel,
    currentInventoryViewModel: CurrentInventoryViewModel,
    currentElementViewModel: CurrentElementViewModel,
    onNavigateToTakePicture: () -> Unit,
) {
    composable(wallsRoute){
        val walls: ElementListState by wallsViewModel.walls.collectAsStateWithLifecycle()

        val inventoryId = currentInventoryViewModel.currentInventory.value

        ElementsPage(
            isWallsPage = true,
            elements = walls,
            getNbRooms = currentPropertyViewModel::getNbRooms,
            getRoomNumber = currentRoomViewModel::getOrderNb,
            getNbWalls = currentRoomViewModel::getNbWalls,
            getNbDoors = currentRoomViewModel::getNbDoors,
            getNbWindows = currentRoomViewModel::getNbWindows,
            onClickSameState = { element -> wallsViewModel.check(element.id, element.state)},
            setCurrentElement = currentElementViewModel::setCurrentElement,
            onClickOnNext = wallsViewModel::checkAll,
            fetchElements = { wallsViewModel.fetchWallList(inventoryId) },
            fetchElement = { elementId: UUID -> wallsViewModel.fetchWall(inventoryId, elementId) },
            onNavigateToTakePicture = onNavigateToTakePicture
        )
    }
}

fun NavController.navigateToWalls() {
    navigate(wallsRoute)
}