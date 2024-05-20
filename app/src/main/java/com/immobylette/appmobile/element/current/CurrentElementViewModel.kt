package com.immobylette.appmobile.element.current

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.immobylette.appmobile.room.elements.ElementState

class CurrentElementViewModel: ViewModel() {
    private val _currentElement = mutableStateOf(ElementState())
    val currentElement: State<ElementState> get() = _currentElement

    fun getName(): String {
        return _currentElement.value.name;
    }

    fun setCurrentElement(element: ElementState) {
        _currentElement.value = element
    }
}