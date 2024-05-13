package com.immobylette.appmobile.inventory.current

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import java.util.UUID

class CurrentInventoryViewModel: ViewModel() {
    private val _currentInventory = mutableStateOf(UUID.randomUUID())
    val currentInventory: State<UUID> get() = _currentInventory

    fun changeCurrentInventory(newInventory: UUID) {
        _currentInventory.value = newInventory
    }

    fun getCurrentInventory(): UUID {
        return _currentInventory.value
    }
}