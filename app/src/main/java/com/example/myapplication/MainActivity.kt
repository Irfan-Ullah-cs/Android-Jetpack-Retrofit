package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val onButtonClick: () -> Unit = {
            // Here you can access to the activity state (ie baseContext)
            Toast.makeText(baseContext, "This is toast generated using Button", Toast.LENGTH_LONG).show()
        }

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        onClick = onButtonClick,

                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column{
        Text(
            text = "Hi  $name!",
            modifier = modifier
        )
        Text(
            stringResource(R.string.act_main_welcome),
            textAlign = TextAlign.Center
        )
        Text(
            stringResource(R.string.my_intro),
            textAlign = TextAlign.Center
        )
        Button(onClick = onClick) {
            Text(
                text = "My first button"
            )
        }
    }



}

