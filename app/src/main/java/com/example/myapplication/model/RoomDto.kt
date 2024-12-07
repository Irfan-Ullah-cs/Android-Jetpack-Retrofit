import com.example.myapplication.model.WindowDto
data class RoomDto(
    val id: Long = 0L, // Default value
    var name: String,
    val currentTemperature: Double?,
    val targetTemperature: Double?,
    val windows: List<WindowDto> = emptyList()
)

data class RoomCommandDto(
    val name: String,
    val currentTemperature: Double?,
    val targetTemperature: Double?,
    val floor: Int = 1,
    // Set to the default building ID (useful when you have not created screens to manage buildings)
    val buildingId: Long = -10
)




