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

class RoomListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val roomsState = remember { mutableStateOf<List<RoomDto>>(emptyList()) }

                // Load the list of rooms
                LaunchedEffect(Unit) {
                    try {
                        roomsState.value = RoomService.findAll()
                    } catch (e: Exception) {
                        if (!isFinishing) {
                            Log.e("RoomListActivity", "Error loading rooms", e)
                        }
                    }
                }

                // UI layout with the list of rooms
                Scaffold(
                    topBar = {
                        AutomacorpTopAppBar(
                            title = "Room List",
                            returnAction = { finish() }
                        )
                    }
                ) { innerPadding ->
                    RoomList(
                        rooms = roomsState.value,
                        modifier = Modifier.padding(innerPadding)
                    ) { room ->
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


val customRoomNameStyle = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold // Adjust the font size and weight as needed
)

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
