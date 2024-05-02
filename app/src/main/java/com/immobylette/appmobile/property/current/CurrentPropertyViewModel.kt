package com.immobylette.appmobile.property.current

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.immobylette.appmobile.property.selection.PropertyState

class CurrentPropertyViewModel: ViewModel() {
    private val _currentProperty = mutableStateOf(PropertyState())
    val currentProperty: State<PropertyState> get() = _currentProperty

    fun changeCurrentProperty(property: PropertyState) {
        Log.d("CurrentPropertyViewModel", "changeCurrentProperty: $property")
        _currentProperty.value = property
    }
}