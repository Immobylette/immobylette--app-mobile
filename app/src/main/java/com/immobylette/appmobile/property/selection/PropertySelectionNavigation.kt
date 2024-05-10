package com.immobylette.appmobile.property.selection

import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.composable
import com.immobylette.appmobile.property.current.CurrentPropertyViewModel

const val propertySelectionRoute = "property-selection"

fun NavGraphBuilder.propertySelectionNavigation(
    propertySelectionViewModel: PropertySelectionViewModel,
    currentPropertyViewModel: CurrentPropertyViewModel,
    onNavigateToChangeAgent: () -> Unit,
    onNavigateToPropertySelected: () -> Unit,
) {

    composable(propertySelectionRoute) {
        val state: PropertyListState by propertySelectionViewModel.state.collectAsStateWithLifecycle()

        Surface {
            PropertySelectionPage(
                state = state,
                fetchPropertyList = propertySelectionViewModel::fetchPropertyList,
                fetchProperty = propertySelectionViewModel::fetchProperty,
                saveCurrentProperty = currentPropertyViewModel::changeCurrentProperty,
                onNavigateToChangeAgent = onNavigateToChangeAgent,
                onNavigateToPropertySelected = onNavigateToPropertySelected
            )
        }
    }
}

fun NavController.navigateToPropertySelection(){
    navigate(propertySelectionRoute)
}