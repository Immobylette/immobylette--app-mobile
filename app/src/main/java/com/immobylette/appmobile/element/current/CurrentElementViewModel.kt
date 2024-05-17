package com.immobylette.appmobile.element.current

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.immobylette.appmobile.element.ElementState

class CurrentElementViewModel: ViewModel() {
    private val _currentElement = mutableStateOf(ElementState(name="Chaise 1"))
    val currentState: State<ElementState> get() = _currentElement

    fun getName(): String {
        return _currentElement.value.name;
    }
}