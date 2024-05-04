package com.immobylette.appmobile.agent.selection

import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.immobylette.appmobile.agent.current.CurrentAgentViewModel

const val agentSelectionRoute = "agent-selection"

fun NavGraphBuilder.agentSelectionNavigation(
    agentSelectionViewModel: AgentSelectionViewModel,
    currentAgentViewModel: CurrentAgentViewModel,
    onNavigateToAgentSelected: () -> Unit,
) {
    composable(agentSelectionRoute) {
        val state: AgentListState by agentSelectionViewModel.state.collectAsStateWithLifecycle()
        AgentSelectionPage(
            state = state,
            fetchAgentList = agentSelectionViewModel::fetchAgentList,
            setCurrentAgent = currentAgentViewModel::setCurrentAgent,
            onNavigateToAgentSelected = onNavigateToAgentSelected
        )
    }
}

fun NavController.navigateToAgentSelection() {
    navigate(agentSelectionRoute)
}