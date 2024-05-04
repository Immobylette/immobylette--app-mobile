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
import com.immobylette.appmobile.loading.loadingNavigation
import com.immobylette.appmobile.property.current.CurrentPropertyViewModel
import com.immobylette.appmobile.property.selection.PropertySelectionViewModel
import com.immobylette.appmobile.property.selection.navigateToConfirmationOfAttendance
import com.immobylette.appmobile.property.selection.propertySelectionNavigation
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.utils.LocationHelper

class MainActivity : ComponentActivity() {
    private val currentPropertyViewModel by viewModels<CurrentPropertyViewModel>()

    private val currentAgentViewModel by viewModels<CurrentAgentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        LocationHelper.init(this)

        super.onCreate(savedInstanceState)
        setContent {
            val agentSelectionViewModel by viewModels<AgentSelectionViewModel>()
            val propertySelectionViewModel by viewModels<PropertySelectionViewModel>()
            val navController = rememberNavController()

            ImmobyletteappmobileTheme {
                NavHost(
                    navController = navController,
                    startDestination = "agent-selection",
                ){
                    agentSelectionNavigation(
                        agentSelectionViewModel = agentSelectionViewModel,
                        currentAgentViewModel = currentAgentViewModel,
                        onNavigateToAgentSelected = { navController.navigate("loading") }
                    )

                    loadingNavigation(
                        onNavigateToLoadingFinished = { navController.navigate("property-selection") }
                    )

                    propertySelectionNavigation(
                        propertySelectionViewModel = propertySelectionViewModel,
                        currentPropertyViewModel = currentPropertyViewModel,
                        onNavigateToChangeAgent = { navController.navigateUp() },
                        onNavigateToPropertySelected = { navController::navigateToConfirmationOfAttendance }
                    )
                }
            }
        }
    }
}