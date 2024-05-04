package com.immobylette.appmobile.property.current

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import java.util.UUID

class CurrentPropertyViewModel: ViewModel() {
    private val _currentProperty = mutableStateOf(UUID.randomUUID())
    val currentProperty: State<UUID> get() = _currentProperty

    fun changeCurrentProperty(property: UUID) {
        _currentProperty.value = property
    }
}