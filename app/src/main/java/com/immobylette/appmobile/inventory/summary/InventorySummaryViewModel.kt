package com.immobylette.appmobile.inventory.summary

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immobylette.appmobile.utils.RetrofitHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class InventorySummaryViewModel: ViewModel() {
    private val _inventorySummary = MutableStateFlow(InventorySummaryState())

    val inventorySummary: InventorySummaryState get() = _inventorySummary.value

    fun fetchInventorySummary(inventoryId: UUID) {
        viewModelScope.launch {
            val result = RetrofitHelper.inventoryService.getSummary(inventoryId)

            if (result.isSuccessful) {
                _inventorySummary.value = result.body()!!.toState()
            }else {
                Log.e("RequestError", "Error fetching inventory summary")
            }
        }
    }

    fun getNbPhotos(): Int = inventorySummary.nbPhotos
    fun getNbRooms(): Int = inventorySummary.nbRooms
    fun getDate(): Date = inventorySummary.date
}