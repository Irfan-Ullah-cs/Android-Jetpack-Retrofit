package com.example.myapplication

import RoomDto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.service.RoomService
import com.example.myapplication.ui.theme.MyApplicationTheme
/**
 * Represents the main activity that displays a list of rooms.
 *
 * The `RoomListActivity` fetches a list of rooms using the `RoomService` and
 * provides a UI for users to view and interact with the rooms. Users can click on a room
 * to navigate to its detail page.
 */
class RoomListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("RoomListActivity", "onCreate: RoomListActivity started.") // INFO

        setContent {
            MyApplicationTheme {
                val roomsState = remember { mutableStateOf<List<RoomDto>>(emptyList()) }

                // Load the list of rooms
                LaunchedEffect(Unit) {
                    try {
                        Log.d("RoomListActivity", "LaunchedEffect: Attempting to load room data.") // DEBUG
                        roomsState.value = RoomService.findAll()
                        Log.i("RoomListActivity", "LaunchedEffect: Successfully loaded ${roomsState.value.size} rooms.") // INFO
                    } catch (e: Exception) {
                        if (!isFinishing) {
                            Log.e("RoomListActivity", "Error loading rooms", e) // ERROR
                        }
                    }
                }

                // UI layout with the list of rooms
                Scaffold(
                    topBar = {
                        AutomacorpTopAppBar(
                            title = "Room List",
                            returnAction = {
                                Log.w("RoomListActivity", "User triggered return action from the top bar.") // WARN
                                finish()
                            }
                        )
                    }
                ) { innerPadding ->
                    RoomList(
                        rooms = roomsState.value,
                        modifier = Modifier.padding(innerPadding)
                    ) { room ->
                        Log.v("RoomListActivity", "Navigating to RoomDetailActivity for room: ${room.name}") // VERBOSE
                        val intent = Intent(this, RoomDetailActivity::class.java).apply {
                            putExtra("room_id", room.id)
                            putExtra("room_name", room.name)
                            putExtra("target_temperature", room.targetTemperature)
                            putExtra("current_temperature", room.currentTemperature)
                        }
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

/**
 * A custom text style for room names in the room list.
 *
 * This style increases the font size and sets the font weight to bold.
 */
val customRoomNameStyle = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold
)

/**
 * Displays a list of rooms in a scrollable view.
 *
 * @param rooms The list of rooms to display. Each room is represented by a [RoomDto].
 * @param modifier A [Modifier] to customize the layout of the list.
 * @param onItemClick A callback invoked when a room is clicked. The clicked room is passed as an argument.
 */
@Composable
fun RoomList(
    rooms: List<RoomDto>,
    modifier: Modifier = Modifier,
    onItemClick: (RoomDto) -> Unit
) {
    if (rooms.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No rooms available",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(rooms) { room ->
                RoomListItem(
                    room = room,
                    onItemClick = { onItemClick(room) }
                )
            }
        }
    }
}

/**
 * Represents a single item in the room list.
 *
 * This composable displays the room's name, target temperature, and current temperature.
 * Clicking on the item triggers the provided `onItemClick` callback.
 *
 * @param room The room details to display, provided as a [RoomDto].
 * @param onItemClick A callback invoked when the item is clicked.
 */
@Composable
fun RoomListItem(room: RoomDto, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = room.name,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Target Temp: ${room.targetTemperature}°C",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "${room.currentTemperature}°C",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
