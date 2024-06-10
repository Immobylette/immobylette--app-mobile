package com.immobylette.appmobile.inventory.summary

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.inventory.current.CurrentInventoryViewModel

const val inventorySummaryRoute = "inventorySummary"

fun NavGraphBuilder.inventorySummaryNavigation(
    currentInventoryViewModel: CurrentInventoryViewModel,
    inventorySummaryViewModel: InventorySummaryViewModel,
    onNavigateToPropertySelection: () -> Unit,
    onNavigateToSignature: () -> Unit
) {
    composable(inventorySummaryRoute) {
        InventorySummaryPage(
            getInventorySummary = {
                inventorySummaryViewModel.fetchInventorySummary(currentInventoryViewModel.getCurrentInventory())
            },
            getNbPhotos = inventorySummaryViewModel::getNbPhotos,
            getNbRooms = inventorySummaryViewModel::getNbRooms,
            getDate = inventorySummaryViewModel::getDate,
            onNavigateToPropertySelection = onNavigateToPropertySelection,
            onNavigateToSignature = onNavigateToSignature
        )
    }
}

fun NavController.navigateToInventorySummary() {
    navigate(inventorySummaryRoute)
}