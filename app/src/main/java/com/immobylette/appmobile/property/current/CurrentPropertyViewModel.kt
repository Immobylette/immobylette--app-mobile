package com.immobylette.appmobile.property.current

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.immobylette.appmobile.property.selection.PropertyState
import java.util.UUID

class CurrentPropertyViewModel: ViewModel() {
    private val _currentProperty = mutableStateOf(PropertyState())
    val currentProperty: State<PropertyState> get() = _currentProperty

    fun changeCurrentProperty(property: PropertyState) {
        _currentProperty.value = property
    }

    fun getTenant(): String? {
        return _currentProperty.value.currentTenant;
    }

    fun getId(): UUID {
        return _currentProperty.value.id;
    }
}