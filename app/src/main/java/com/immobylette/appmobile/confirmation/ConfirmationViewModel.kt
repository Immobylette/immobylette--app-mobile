package com.immobylette.appmobile.confirmation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immobylette.appmobile.data.dto.AgentDto
import com.immobylette.appmobile.utils.RetrofitHelper
import kotlinx.coroutines.launch
import java.util.UUID

class ConfirmationViewModel: ViewModel() {
    fun startInventory(propertyId: UUID, agentId: UUID, onInventoryStarted: (UUID) -> Unit) {
        viewModelScope.launch {
            val agentDto = AgentDto(agentId)
            val result = RetrofitHelper.inventoryService.startInventory(propertyId, agentDto)
            if (!result.isSuccessful) {
                Log.e("RequestError", "Error starting inventory")
            }
            onInventoryStarted(result.body()!!)
        }
    }
}