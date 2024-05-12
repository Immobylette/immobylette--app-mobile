package com.immobylette.appmobile.inventory.current

import androidx.compose.runtime.mutableStateOf
import android.util.Log
import androidx.compose.runtime.State
import com.immobylette.appmobile.utils.RetrofitHelper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.UUID

class CurrentInventoryViewModel: ViewModel() {
    private val _currentInventory = mutableStateOf(UUID.randomUUID())
    val currentInventory: State<UUID> get() = _currentInventory

    fun startInventory(propertyId: UUID) {
        viewModelScope.launch {
            val result = RetrofitHelper.inventoryService.startInventory(propertyId)
            if (!result.isSuccessful) {
                Log.e("RequestError", "Error starting inventory")
                return
            }
            _currentInventory.value = result.body()
        }
    }
}