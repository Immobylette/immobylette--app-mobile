package com.immobylette.appmobile.room.current

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel

class CurrentRoomViewModel : ViewModel() {
    private val _currentRoomNumber = mutableStateOf(0)
    val currentRoomNumber: State<Int>
        get() = _currentRoomNumber

    fun setCurrentRoomNumber(roomNumber: Int) {
        _currentRoomNumber.value = roomNumber
    }
}