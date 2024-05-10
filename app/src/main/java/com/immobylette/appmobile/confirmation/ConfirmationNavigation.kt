package com.immobylette.appmobile.confirmation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.property.current.CurrentPropertyViewModel

const val confirmationRoute = "confirmation"

fun NavGraphBuilder.confirmationNavigation(
    confirmationViewModel: ConfirmationViewModel,
    currentPropertyViewModel: CurrentPropertyViewModel,
    onNavigateToConfirmed: () -> Unit,
) {
    composable(confirmationRoute) {
        ConfirmationPage (
            onNavigateToConfirmed = {
                val propertyId = currentPropertyViewModel.getId()
                confirmationViewModel.startInventory(propertyId)
                onNavigateToConfirmed()
            },
            getTenant = currentPropertyViewModel::getTenant
        )
    }
}

fun NavController.navigateToConfirmation(){
    navigate(confirmationRoute)
}