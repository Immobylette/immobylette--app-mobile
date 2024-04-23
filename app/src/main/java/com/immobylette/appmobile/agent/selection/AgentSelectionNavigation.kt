package com.immobylette.appmobile.agent.selection

import androidx.compose.material3.Surface
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.ui.shared.component.GraphicFooter

const val agentSelectionRoute = "agent-selection"

fun NavGraphBuilder.agentSelectionNavigation(
    agentSelectionViewModel: AgentSelectionViewModel,
    onNavigateToAgentSelected: () -> Unit,
) {

    composable(agentSelectionRoute) {
        // Get the state from viewModel

        // Place the page below
        Surface {
            GraphicFooter()
        }
    }
}

// Place navigation functions below