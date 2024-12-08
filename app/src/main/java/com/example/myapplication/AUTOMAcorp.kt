package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class AUTOMAcorp : ComponentActivity() {
    companion object {
        const val ROOM_PARAM = "ROOM_PARAM"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Action to do when the button is clicked
        val onSayHelloButtonClick: (name: String) -> Unit = { name ->
            if (name.isBlank()) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("AUTOMAcorp", "Room name passed to Intent: $name")
                val intent = Intent(this, RoomActivity::class.java).apply {
                putExtra(AUTOMAcorp.ROOM_PARAM, name)
                }
                startActivity(intent)
            }

        }

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting4(
                        onClick = onSayHelloButtonClick,

                        modifier = Modifier.padding(innerPadding),

                        )
                }
            }
        }
    }
}

@Composable
fun Greeting4(onClick: (name: String) -> Unit, modifier: Modifier = Modifier) {
    Column {
        // ...
        AppLogo(Modifier.padding(top = 32.dp).fillMaxWidth())
        Text(
            stringResource(R.string.act_main_welcome),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        var name by remember { mutableStateOf("") }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .testTag("nameField"),
            placeholder = {
                Text(stringResource(R.string.act_main_fill_name))
            }
        )

            Button(
                onClick = { onClick(name) },
                modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally)
                    .testTag("openButton")
            ) {
                Text(stringResource(R.string.act_main_open))
            }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    MyApplicationTheme {
        MyApplicationTheme {
            Greeting4(onClick = {}, modifier = Modifier)
        }

    }
}

