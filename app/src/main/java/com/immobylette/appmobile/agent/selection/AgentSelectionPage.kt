package com.immobylette.appmobile.agent.selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.component.Agent
import com.immobylette.appmobile.ui.shared.component.GraphicFooter
import com.immobylette.appmobile.ui.shared.component.Logo
import com.immobylette.appmobile.ui.shared.component.Title
import java.util.UUID
import kotlin.math.ceil

@Composable
fun AgentSelectionPage(
    state: AgentListState,
    fetchAgentList: () -> Unit,
    setCurrentAgent: (agent: UUID) -> Unit,
    onNavigateToAgentSelected:() -> Unit
) {
    val halfNbAgents: Int = ceil(state.agentList.size.toDouble()/2).toInt()
    val agentFirstList: List<AgentState> = state.agentList.take(halfNbAgents)
    val agentSecondList: List<AgentState> = state.agentList.drop(halfNbAgents)

    LaunchedEffect(Unit) {
        fetchAgentList()
    }

    Surface {
        GraphicFooter()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Logo(
                size = 250,
                corner = 45
            )
            Title(
                text = stringResource(id = R.string.label_select_agent))
            AgentRow(
                agentList = agentFirstList,
                setCurrentAgent = setCurrentAgent,
                onNavigateToAgentSelected = onNavigateToAgentSelected
            )
            AgentRow(
                agentList = agentSecondList,
                setCurrentAgent = setCurrentAgent,
                onNavigateToAgentSelected = onNavigateToAgentSelected
            )
        }
    }
}


@Composable
fun AgentRow(
    agentList: List<AgentState>,
    setCurrentAgent: (UUID) -> Unit,
    onNavigateToAgentSelected: () -> Unit
) {
    val agentListHeight = 250
    LazyHorizontalGrid(
        rows = GridCells.Adaptive(minSize = agentListHeight.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(agentListHeight.dp)
            .padding(0.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        items(agentList) { agent: AgentState ->
            Agent(
                url = agent.photo,
                name = agent.name,
                modifier = Modifier.padding(30.dp).width(150.dp).height(agentListHeight.dp),
                onClick = {
                    setCurrentAgent(agent.id)
                    onNavigateToAgentSelected()
                }
            )
        }
    }
}