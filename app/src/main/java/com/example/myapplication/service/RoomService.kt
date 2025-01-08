package com.example.myapplication.service

import RoomDto
import android.util.Log
import androidx.core.text.isDigitsOnly
import com.example.myapplication.model.WindowDto
import com.example.myapplication.model.WindowStatus
import kotlin.math.log



object RoomService {

    private val api = ApiServices.roomsApiService

    suspend fun findAll(): List<RoomDto> {
        return try {
            api.getRooms() // Assuming this makes a network call

        } catch (e: Exception) {
            Log.e("RoomService", "Error fetching rooms", e)
            emptyList()
        }
    }

    suspend fun findByNameOrId(param: String): RoomDto? {
        return try {

            Log.i("RoomActity","Inside RoomService $param")
            val rooms = api.getRooms()
            rooms.find {
                param.toLongOrNull()?.let { id -> it.id == id } == true || it.name == param
            }
        } catch (e: Exception) {
            Log.e("RoomService", "Error fetching rooms", e)
            null
        }
    }

    suspend fun updateRoom(id: String, room: RoomDto) {
        try {
            api.updateRoom(id, room)
        } catch (e: Exception) {
            Log.e("RoomService", "Error updating room", e)
        }
    }
}


