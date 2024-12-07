package com.example.myapplication.service
import RoomDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RoomsApiService {
    @GET("rooms")
    suspend fun getRooms(): List<RoomDto>

    @GET("rooms/{id}")
    suspend fun getRoomById(@Path("id") id: String): RoomDto

    @PUT("rooms/{id}")
    suspend fun updateRoom(@Path("id") id: String, @Body room: RoomDto): Response<Unit>
}