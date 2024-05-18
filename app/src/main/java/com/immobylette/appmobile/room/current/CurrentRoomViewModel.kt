package com.immobylette.appmobile.room.current

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.immobylette.appmobile.room.gotoroom.RoomState

class CurrentRoomViewModel : ViewModel() {
    private val _currentRoom = mutableStateOf(RoomState())
    val currentRoom: State<RoomState>
        get() = _currentRoom

    fun setCurrentRoom(room: RoomState) {
        _currentRoom.value = room
    }

    fun getOrderNb(): Int {
        return _currentRoom.value.nbOrder
    }

    fun getNbWalls(): Int {
        return _currentRoom.value.nbWalls
    }

    fun getNbWindows(): Int {
        return _currentRoom.value.nbWindows
    }

    fun getNbDoors(): Int {
        return _currentRoom.value.nbDoors
    }
}