package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.util.Log
import com.example.greetingcard.ui.theme.GreetingCardTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            GreetingCardTheme {
                GreetingScreen()
            }
        }
    }
}

@Composable
fun GreetingInput(
    name: String,
    hobby: String,
    factText: String,
    count: Int,
    onNameChange: (String) -> Unit,
    onHobbyChange: (String) -> Unit,
    onGreetClick: () -> Unit,
    onShowFactClick: () -> Unit,
    onResetClick: () -> Unit
    )
{
    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Your name") }
        )

        OutlinedTextField(
            value = hobby,
            onValueChange = onHobbyChange,
            label = { Text("Your hobby")}
        )

        Text(
            if (count == 0) {
                "Enter your name and tap the button"
            } else {
                "Hello, $name! Greeted $count times."
            }
        )

        Text(factText)

        Button(onClick = onGreetClick) {
            Text("Say hello")
        }

        Button(onClick = onShowFactClick) {
            Text("Show fact")
        }

        Button(onClick = onResetClick) {
            Text("Reset")
        }
    }
}

@Composable
fun GreetingScreen() {
    var name by remember { mutableStateOf("") }
    var count by remember { mutableStateOf(0) }
    var hobby by remember { mutableStateOf("") }
    var factText by remember { mutableStateOf("") }

    GreetingInput(
        name = name,
        count = count,
        hobby = hobby,
        factText = factText,
        onNameChange = { name = it },
        onHobbyChange = { hobby = it },
        onGreetClick = { count++ },
        onShowFactClick = {
            factText = "$name enjoys $hobby!"
        },
        onResetClick = {
            name = ""
            hobby = ""
            count = 0
            factText = ""
        }
    )
}