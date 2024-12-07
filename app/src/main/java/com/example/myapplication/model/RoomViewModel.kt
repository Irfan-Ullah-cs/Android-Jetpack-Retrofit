package com.example.myapplication.model

import RoomDto
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.service.RoomService
import kotlinx.coroutines.launch


class RoomViewModel : ViewModel() {
    var room: RoomDto? by mutableStateOf(null)

    fun loadRoom(param: String) {
        viewModelScope.launch {
            room = RoomService.findByNameOrId(param)
        }
    }

    fun saveRoom(updatedRoom: RoomDto) {
        viewModelScope.launch {
            room?.let {
                RoomService.updateRoom(it.id.toString(), updatedRoom)
            }
        }
    }
}

