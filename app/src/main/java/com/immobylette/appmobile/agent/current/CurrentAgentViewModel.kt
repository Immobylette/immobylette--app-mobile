package com.immobylette.appmobile.agent.current

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import java.util.UUID

class CurrentAgentViewModel : ViewModel() {
    private val _currentAgent = mutableStateOf(UUID.randomUUID())
    val currentAgent: State<UUID>
        get() = _currentAgent

    fun setCurrentAgent(agent: UUID) {
        _currentAgent.value = agent
    }
}