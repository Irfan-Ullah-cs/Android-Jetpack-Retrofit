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



//object RoomService {
//    val ROOM_KIND: List<String> = listOf("Room", "Meeting", "Laboratory", "Office", "Boardroom")
//    val ROOM_NUMBER: List<Char> = ('A'..'Z').toList()
//    val WINDOW_KIND: List<String> = listOf("Sliding", "Bay", "Casement", "Hung", "Fixed")
//
//    fun generateWindow(id: Long, roomId: Long, roomName: String): WindowDto {
//        return WindowDto(
//            id = id,
//            name = "${WINDOW_KIND.random()} Window $id",
//            roomName = roomName,
//            roomId = roomId,
//            windowStatus = WindowStatus.values().random()
//        )
//    }
//
//    fun generateRoom(id: Long): RoomDto {
//        val roomName = "${ROOM_NUMBER.random()}$id ${ROOM_KIND.random()}"
//        val windows = (1..(1..6).random()).map { generateWindow(it.toLong(), id, roomName) }
//        return RoomDto(
//            id = id,
//            name = roomName,
//            currentTemperature = (15..30).random().toDouble(),
//            targetTemperature = (15..22).random().toDouble(),
//            windows = windows
//        )
//    }
//
//    // Create 50 rooms
//    val ROOMS = (1..50).map { generateRoom(it.toLong()) }.toMutableList()
//
//
////    fun findAll(): List<RoomDto> {
////        // Sort rooms by name in ascending order
////        return ROOMS.sortedBy { it.name }
////    }
////
////    fun findById(id: Long): RoomDto? {
////        return ROOMS.find { it.id == id }
////    }
////
////    fun findByName(name: String): RoomDto? {
////        return ROOMS.find {
////            it.name == name
////
////        }
////    }
////
////    fun updateRoom(id: Long, room: RoomDto): RoomDto? {
////        val index = ROOMS.indexOfFirst { it.id == id }
////        val updatedRoom = findById(id)?.copy(
////            name = room.name,
////            targetTemperature = room.targetTemperature,
////            currentTemperature = room.currentTemperature
////        ) ?: throw IllegalArgumentException()
////        return ROOMS.set(index, updatedRoom)
////    }
//
////    fun findByNameOrId(nameOrId: String?): RoomDto? {
////        Log.i("Room Service", "This is findByNameOrId function and Passed value is: $nameOrId")
////        if (nameOrId != null) {
////            return if (nameOrId.isDigitsOnly()) {
////                findById(nameOrId.toLong())
////            } else {
////                return findByName(nameOrId)
////
////            }
////        }
////        return null
////    }
//
//
//}