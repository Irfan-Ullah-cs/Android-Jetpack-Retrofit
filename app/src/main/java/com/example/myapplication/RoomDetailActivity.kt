package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class RoomDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val roomId = intent.getStringExtra("room_id")
        val roomName = intent.getStringExtra("room_name")
        val targetTemperature = intent.getFloatExtra("target_temperature", 0f)
        val currentTemperature = intent.getFloatExtra("current_temperature", 0f)

        setContent {
            MyApplicationTheme {
                RoomDetails(
                    roomId = roomId.orEmpty(),
                    roomName = roomName.orEmpty(),
                    targetTemperature = targetTemperature,
                    currentTemperature = currentTemperature
                )
            }
        }
    }
}

@Composable
fun RoomDetails(
    roomId: String,
    roomName: String,
    targetTemperature: Float,
    currentTemperature: Float
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Room Details", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Room ID: $roomId", fontSize = 16.sp)
        Text(text = "Room Name: $roomName", fontSize = 16.sp)
        Text(text = "Target Temperature: $targetTemperature°C", fontSize = 16.sp)
        Text(text = "Current Temperature: $currentTemperature°C", fontSize = 16.sp)
    }
}
