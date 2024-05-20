package com.immobylette.appmobile.property.selection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immobylette.appmobile.utils.LocationHelper
import com.immobylette.appmobile.utils.RetrofitHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class PropertySelectionViewModel: ViewModel() {
    private val _state: MutableStateFlow<PropertyListState> = MutableStateFlow(PropertyListState())

    val state: StateFlow<PropertyListState> get(){ return _state.asStateFlow() }

    fun fetchPropertyList() {
        if (_state.value.pagination.isLastPage){
            return
        }

        viewModelScope.launch {
            val (latitude, longitude) = LocationHelper.getLocation()

            val result = RetrofitHelper.propertyService
                .getProperties(
                    longitude = longitude,
                    latitude = latitude,
                    page = _state.value.pagination.page + 1,
                    perPage = _state.value.pagination.perPage
                )

            if (result.isSuccessful) {
                val paginatedProperties = result.body()!!.toState()

                _state.update { current ->
                    current.copy(
                        properties = _state.value.properties + paginatedProperties.properties,
                        pagination = paginatedProperties.pagination
                    )
                }
            }else{
                Log.e("RequestError", "Error fetching properties")
            }
        }
    }

    fun fetchProperty(id: UUID) {
        viewModelScope.launch {
            val result = RetrofitHelper.propertyService.getProperty(id)

            if (result.isSuccessful) {
                val property = result.body()!!.toState()
                _state.update { current ->
                    current.copy(
                        properties = current.properties.map { prop ->
                            if (prop.id == id) {
                                PropertyState(
                                    id = prop.id,
                                    nbRooms = property.nbRooms,
                                    propertyType = prop.propertyType,
                                    propertyClass = prop.propertyClass,
                                    owner = property.owner,
                                    currentTenant = property.currentTenant,
                                    address = prop.address,
                                    currentInventory = property.currentInventory,
                                    currentInventoryId = property.currentInventoryId,
                                    photo = prop.photo,
                                    distance = prop.distance,
                                )
                            } else {
                                prop
                            }
                        }
                    )
                }
            }else{
                Log.e("RequestError", "Error fetching property")
            }
        }
    }
}