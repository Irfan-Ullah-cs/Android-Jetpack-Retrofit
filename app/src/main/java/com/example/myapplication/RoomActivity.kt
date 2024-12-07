package com.example.myapplication

import RoomDto
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.model.RoomViewModel
import com.example.myapplication.service.RoomService
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class RoomActivity : ComponentActivity() {
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.i("RoomActity","Start")
        val param = intent.getStringExtra(AUTOMAcorp.ROOM_PARAM)
        val viewModel: RoomViewModel by viewModels()
        val navigateBack: () -> Unit = {
            startActivity(Intent(this, AUTOMAcorp::class.java))
        }
        lifecycleScope.launch {
            val room = RoomService.findByNameOrId(param ?: "")
            Log.i("RoomActity","Value passed in $room" )
            // Do something with the room, e.g., update UI

            setContent {
                MyApplicationTheme {
                    Log.i("RoomActity","Inside Set Contant")


                        Scaffold(
                            topBar = { AutomacorpTopAppBar("Room", navigateBack) },

//                        floatingActionButton = { RoomUpdateButton(onRoomSave) },
                            modifier = Modifier.fillMaxSize()
                        ) { innerPadding ->

                            if (room != null) {
                                RoomDetail(room, Modifier.padding(innerPadding))

                                Log.i("RoomActity","Room is not numm")
                            } else {
                                NoRoom(Modifier.padding(innerPadding))
                            }
                        }
                    }
                }
            }
        }

    }




// Step 4: NoRoom Composable with Logging
@Composable
fun NoRoom(modifier: Modifier = Modifier) {
    Log.w("NoRoom", "Displaying 'No Room Found' message.")
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.act_room_none),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

// Step 5: RoomDetail Composable with Logging
@Composable
fun RoomDetail(room: RoomDto, modifier: Modifier = Modifier) {
    var roomState by remember { mutableStateOf(room) }
    Log.d("RoomDetail", "Rendering RoomDetail for room: $roomState")

    Column(modifier = modifier.padding(16.dp)) {
        // Room Name
        OutlinedTextField(
            value = roomState.name,
            onValueChange = { newValue ->
                Log.d("RoomDetail", "Room name updated to: $newValue")
                roomState = roomState.copy(name = newValue)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(R.string.act_room_name)) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Current Temperature (Read-Only)
        Text(
            text = stringResource(R.string.act_room_current_temp, roomState.currentTemperature ?: 0.0),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Target Temperature (Slider)
        Slider(
            value = roomState.targetTemperature?.toFloat() ?: 18.0f,
            onValueChange = { newValue ->
                Log.d("RoomDetail", "Target temperature updated to: $newValue")
                roomState = roomState.copy(targetTemperature = newValue.toDouble())
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            valueRange = 10f..28f
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Display Target Temperature Value
        Text(
            text = stringResource(R.string.act_room_target_temp, roomState.targetTemperature ?: 0.0),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}





@Preview(showBackground = true)
@Composable
fun RoomDetailPreview() {
    Log.d("Preview", "Previewing RoomDetail Composable")
    MyApplicationTheme {
        RoomDetail(
            RoomDto(name = "Sample Room", currentTemperature = 22.5, targetTemperature = 24.0)
        )
    }
}

