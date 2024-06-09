package com.immobylette.appmobile.agent.current

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.immobylette.appmobile.agent.selection.AgentState

class CurrentAgentViewModel : ViewModel() {
    private val _currentAgent = mutableStateOf(AgentState())
    val currentAgent: State<AgentState> get() = _currentAgent

    fun setCurrentAgent(agent: AgentState) {
        _currentAgent.value = agent
    }
}