package com.immobylette.appmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.immobylette.appmobile.agent.current.CurrentAgentViewModel
import com.immobylette.appmobile.agent.selection.AgentSelectionViewModel
import com.immobylette.appmobile.agent.selection.agentSelectionNavigation
import com.immobylette.appmobile.agent.selection.navigateToAgentSelection
import com.immobylette.appmobile.confirmation.ConfirmationViewModel
import com.immobylette.appmobile.confirmation.confirmationNavigation
import com.immobylette.appmobile.confirmation.navigateToConfirmation
import com.immobylette.appmobile.loading.loadingNavigation
import com.immobylette.appmobile.loading.navigateToLoadingPage
import com.immobylette.appmobile.property.current.CurrentPropertyViewModel
import com.immobylette.appmobile.property.selection.PropertySelectionViewModel
import com.immobylette.appmobile.inventory.current.CurrentInventoryViewModel
import com.immobylette.appmobile.property.selection.navigateToPropertySelection
import com.immobylette.appmobile.property.selection.propertySelectionNavigation
import com.immobylette.appmobile.ui.shared.component.ListAdditionalPhotos
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.utils.LocationHelper
import java.net.URL

class MainActivity : ComponentActivity() {
    private val currentPropertyViewModel by viewModels<CurrentPropertyViewModel>()
    private val currentAgentViewModel by viewModels<CurrentAgentViewModel>()
    private val currentInventoryViewModel by viewModels<CurrentInventoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        LocationHelper.init(this)

        super.onCreate(savedInstanceState)
        setContent {
            val agentSelectionViewModel by viewModels<AgentSelectionViewModel>()
            val propertySelectionViewModel by viewModels<PropertySelectionViewModel>()
            val confirmationViewModel by viewModels<ConfirmationViewModel>()
            val navController = rememberNavController()

            ImmobyletteappmobileTheme {
//                NavHost(
//                    navController = navController,
//                    startDestination = "agent-selection",
//                ){
//                    agentSelectionNavigation(
//                        agentSelectionViewModel = agentSelectionViewModel,
//                        currentAgentViewModel = currentAgentViewModel,
//                        onNavigateToAgentSelected = navController::navigateToLoadingPage
//                    )
//
//                    loadingNavigation(
//                        onNavigateToLoadingFinished = navController::navigateToPropertySelection
//                    )
//
//                    propertySelectionNavigation(
//                        propertySelectionViewModel = propertySelectionViewModel,
//                        currentPropertyViewModel = currentPropertyViewModel,
//                        onNavigateToChangeAgent = navController::navigateToAgentSelection,
//                        onNavigateToPropertySelected = navController::navigateToConfirmation
//                    )
//
//                    confirmationNavigation(
//                        confirmationViewModel = confirmationViewModel,
//                        currentPropertyViewModel = currentPropertyViewModel,
//                        currentInventoryViewModel = currentInventoryViewModel,
//                        currentAgentViewModel = currentAgentViewModel,
//                        onNavigateToConfirmed = {}
//                    )
//                }
                ListAdditionalPhotos(
                    photos = listOf(
                        URL("http://placekitten.com/200/300"),
                        URL("http://placekitten.com/200/300"),
                        URL("http://placekitten.com/200/300"),
                        URL("http://placekitten.com/200/300"),
                    ),
                    onDeletePhoto = {},
                    onAddPhoto = {},
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}