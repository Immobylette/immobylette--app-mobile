package com.immobylette.appmobile.agent.selection

import  androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immobylette.appmobile.data.dto.ThirdPartyDto
import com.immobylette.appmobile.utils.RetrofitHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AgentSelectionViewModel: ViewModel() {
    private val _state = MutableStateFlow(AgentListState())

    val state: StateFlow<AgentListState>
        get() {
            return _state.asStateFlow()
        }

    fun fetchAgentList() {
        viewModelScope.launch {
            val agentList = RetrofitHelper.agentService.getAllAgents().map(ThirdPartyDto::toAgentState)
            _state.update { current ->
                current.copy(agentList = agentList)
            }
        }
    }
}