package com.immobylette.appmobile.room.gotoroom

import android.util.Log
import  androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immobylette.appmobile.toasts.ToastService
import com.immobylette.appmobile.utils.RetrofitHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID


class GoToRoomViewModel : ViewModel() {
    private val _state = MutableStateFlow(RoomState())

    val state: StateFlow<RoomState>
        get() {
            return _state.asStateFlow()
        }

    fun fetchCurrentRoom(id: UUID) {
        viewModelScope.launch {
            val result = RetrofitHelper.roomService.getCurrentRoom(id)

            if (result.isSuccessful) {
                val room = result.body()!!.toState()
                _state.update { room.copy() }
            } else{
                ToastService.showToastError()
                Log.e("RequestError", "Error fetching current room")
            }
        }
    }
}