package com.immobylette.appmobile.confirmation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immobylette.appmobile.utils.RetrofitHelper
import kotlinx.coroutines.launch
import java.util.UUID

class ConfirmationViewModel: ViewModel() {
    fun startInventory(propertyId: UUID) {
        viewModelScope.launch {
            val result = RetrofitHelper.inventoryService.startInventory(propertyId)
            if (!result.isSuccessful) {
                Log.e("RequestError", "Error starting inventory")
            }
        }
    }
}