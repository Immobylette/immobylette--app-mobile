package com.immobylette.appmobile.room.elements

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immobylette.appmobile.utils.RetrofitHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import com.immobylette.appmobile.data.enum.ElementState as ElementStateEnum

class ElementsViewModel: ViewModel() {

    private val _elements = MutableStateFlow(ElementListState())

    val elements: StateFlow<ElementListState>
        get() {
            return _elements.asStateFlow()
        }

    fun fetchElementList(inventoryId: UUID){
        viewModelScope.launch {
            val result = RetrofitHelper.inventoryService.getElements(inventoryId)

            if (result.isSuccessful) {
                val elements = ElementListState(elements = result.body()!!.map { it.toState() })

                _elements.update { current ->
                    current.copy(elements = elements.elements)
                }
            }else {
                Log.e("RequestError", "Error fetching elements")
            }
        }
    }

    fun fetchElement(inventoryId: UUID, elementId: UUID){
        viewModelScope.launch {
            val result = RetrofitHelper.inventoryService.getElement(inventoryId, elementId)

            if (result.isSuccessful) {
                val wall = result.body()!!.toState()

                _elements.update { current ->
                    current.copy(
                        elements = current.elements.map { element ->
                            if (element.id == elementId) {
                                ElementState(
                                    id = element.id,
                                    name = wall.name,
                                    description = wall.description,
                                    type = wall.type,
                                    attributes = wall.attributes,
                                    photo = wall.photo,
                                    basePhotos = wall.basePhotos,
                                    previousPhotos = wall.previousPhotos,
                                    step = wall.step,
                                    state = element.state,
                                    checked = element.checked,
                                    nbBasePhotos = element.nbBasePhotos,
                                    nbPreviousPhotos = element.nbPreviousPhotos
                                )
                            } else {
                                element
                            }
                        }
                    )
                }
            }else {
                Log.e("RequestError", "Error fetching element")
            }
        }
    }

    fun check(id: UUID, state: ElementStateEnum){
        //TODO: Check the element with the given id when the backend's endpoint is ready

        _elements.update { current ->
            current.copy(
                elements = current.elements.map { element ->
                    if (element.id == id) {
                        element.copy(state = state, checked = true)
                    } else {
                        element
                    }
                }
            )
        }
    }

    fun checkAll() {
        _elements.value.elements.forEach { element ->
            if (!element.checked) {
                check(element.id, element.state) // We apply the previous state as the new one
            }
        }
    }
}