package com.immobylette.appmobile.confirmation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.property.current.CurrentPropertyViewModel
import com.immobylette.appmobile.inventory.current.CurrentInventoryViewModel

const val confirmationRoute = "confirmation"

fun NavGraphBuilder.confirmationNavigation(
    currentPropertyViewModel: CurrentPropertyViewModel,
    currentInventoryViewModel: CurrentInventoryViewModel,
    onNavigateToConfirmed: () -> Unit,
) {
    composable(confirmationRoute) {
        ConfirmationPage (
            onNavigateToConfirmed = {
                val propertyId = currentPropertyViewModel.getId()
                currentInventoryViewModel.startInventory(propertyId)
                onNavigateToConfirmed()
            },
            getTenant = currentPropertyViewModel::getTenant
        )
    }
}

fun NavController.navigateToConfirmation(){
    navigate(confirmationRoute)
}