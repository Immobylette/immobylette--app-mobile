package com.immobylette.appmobile.room.gotoroom

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import com.immobylette.appmobile.inventory.current.CurrentInventoryViewModel
import com.immobylette.appmobile.room.current.CurrentRoomViewModel

const val goToRoomRoute = "go-to-room"

fun NavGraphBuilder.goToRoomNavigation(
    goToRoomViewModel: GoToRoomViewModel,
    currentRoomViewModel: CurrentRoomViewModel,
    currentInventoryViewModel: CurrentInventoryViewModel,
    onNavigateToRoomElements: () -> Unit,
    onNavigateToPropertySelection: () -> Unit,
    onNavigateToInventorySummary: () -> Unit
) {
    composable(goToRoomRoute) {
        val state: RoomState by goToRoomViewModel.state.collectAsStateWithLifecycle()

        GoToRoomPage(
            state = state,
            fetchCurrentRoom = goToRoomViewModel::fetchCurrentRoom,
            setCurrentRoom = currentRoomViewModel::setCurrentRoom,
            getCurrentInventory = currentInventoryViewModel::getCurrentInventory,
            onNavigateToRoomElements = onNavigateToRoomElements,
            onNavigateToPropertySelection = onNavigateToPropertySelection,
            onNavigateToInventorySummary = onNavigateToInventorySummary
        )
    }
}

fun NavController.navigateToGoToRoom() {
    navigate(goToRoomRoute)
}