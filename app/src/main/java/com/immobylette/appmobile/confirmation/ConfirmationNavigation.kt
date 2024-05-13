package com.immobylette.appmobile.confirmation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.agent.current.CurrentAgentViewModel
import com.immobylette.appmobile.property.current.CurrentPropertyViewModel
import com.immobylette.appmobile.inventory.current.CurrentInventoryViewModel

const val confirmationRoute = "confirmation"

fun NavGraphBuilder.confirmationNavigation(
    confirmationViewModel: ConfirmationViewModel,
    currentPropertyViewModel: CurrentPropertyViewModel,
    currentInventoryViewModel: CurrentInventoryViewModel,
    currentAgentViewModel: CurrentAgentViewModel,
    onNavigateToConfirmed: () -> Unit,
) {
    composable(confirmationRoute) {
        ConfirmationPage (
            onNavigateToConfirmed = {
                val propertyId = currentPropertyViewModel.getId()
                val agentId = currentAgentViewModel.currentAgent.value
                confirmationViewModel.startInventory(propertyId, agentId) {
                    currentInventoryViewModel.changeCurrentInventory(it)
                    onNavigateToConfirmed()
                }
            },
            getTenant = currentPropertyViewModel::getTenant
        )
    }
}

fun NavController.navigateToConfirmation(){
    navigate(confirmationRoute)
}