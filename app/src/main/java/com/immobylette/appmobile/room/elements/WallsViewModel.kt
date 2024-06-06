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

class WallsViewModel: ViewModel() {

    private val _walls = MutableStateFlow(ElementListState())

    val walls: StateFlow<ElementListState>
        get() {
            return _walls.asStateFlow()
        }

    fun fetchWallList(inventoryId: UUID){
        viewModelScope.launch {
            val result = RetrofitHelper.inventoryService.getWalls(inventoryId)

            if (result.isSuccessful) {
                val walls = ElementListState(elements = result.body()!!.map { it.toState() })

                _walls.update { current ->
                    current.copy(elements = walls.elements)
                }
            }else {
                Log.e("RequestError", "Error fetching walls")
            }
        }
    }

    fun fetchWall(inventoryId: UUID, wallId: UUID){
        viewModelScope.launch {
            val result = RetrofitHelper.inventoryService.getElement(inventoryId, wallId)

            if (result.isSuccessful) {
                val wall = result.body()!!.toState()

                _walls.update { current ->
                    current.copy(
                        elements = current.elements.map { element ->
                            if (element.id == wallId) {
                                ElementState(
                                    id = element.id,
                                    name = wall.name,
                                    description = wall.description,
                                    type = wall.type,
                                    attributes = wall.attributes,
                                    photo = wall.photo,
                                    basePhotos = wall.basePhotos,
                                    previousPhotos = wall.previousPhotos,
                                    state = element.state,
                                    checked = element.checked,
                                    nbBasePhotos = element.nbBasePhotos,
                                    nbPreviousPhotos = element.nbPreviousPhotos,
                                    fetched = true
                                )
                            } else {
                                element
                            }
                        }
                    )
                }
            }else {
                Log.e("RequestError", "Error fetching wall")
            }
        }
    }


    fun check(elementId: UUID, state: ElementStateEnum, inventoryId: UUID) {
            viewModelScope.launch {
                val result = RetrofitHelper.inventoryService.postSameElementStep(
                    inventoryId,
                    elementId
                )

                if (result.isSuccessful) {
                    _walls.update { current ->
                        current.copy(
                            elements = current.elements.map { element ->
                                if (element.id == elementId) {
                                    element.copy(state = state, checked = true)
                                } else {
                                    element
                                }
                            }
                        )
                    }
                } else {
                    Log.e("RequestError", "Error adding same step")
                }
            }
    }

    fun checkAll(inventoryId: UUID) {
        _walls.value.elements.forEach { element ->
            if (!element.checked) {
                check(element.id, element.state, inventoryId) // We apply the previous state as the new one
            }
        }
    }
}