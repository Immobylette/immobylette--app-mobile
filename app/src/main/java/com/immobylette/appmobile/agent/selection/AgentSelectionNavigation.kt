package com.immobylette.appmobile.agent.selection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.ui.shared.component.GraphicFooter
import com.immobylette.appmobile.ui.shared.component.Logo

const val agentSelectionRoute = "agent-selection"

fun NavGraphBuilder.agentSelectionNavigation(
    agentSelectionViewModel: AgentSelectionViewModel,
    onNavigateToAgentSelected: () -> Unit,
) {

    composable(agentSelectionRoute) {
        // Get the state from viewModel

        // Place the page below
        Surface {
            //GraphicFooter()
            Box(
                contentAlignment = Alignment.Center, // Centre le contenu à l'intérieur de la Box
                modifier = Modifier.fillMaxSize() // La Box remplit toute la taille de l'écran
            ) {
                Logo();
            }

        }
    }
}

// Place navigation functions below