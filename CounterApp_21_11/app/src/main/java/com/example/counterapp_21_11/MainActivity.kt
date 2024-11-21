package com.example.counterapp_21_11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.counterapp_21_11.ui.theme.CounterApp_21_11Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterApp_21_11Theme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    CounterApp(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CounterApp (name: String, modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(0.0) }
    var input by remember { mutableStateOf("") }
    var currentValue = input

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Resultado: $result",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Digite um número") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    result += input.toDoubleOrNull()
                        ?: 0.0
                    currentValue = input
                    input = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("+ $currentValue")
            }

            Button(
                onClick = {
                    result -= input.toDoubleOrNull() ?: 0.0
                    currentValue = input
                    input = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("- $currentValue")
            }

        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {

            Button(
                onClick = {
                    result *= input.toDoubleOrNull()
                        ?: 1.0
                    currentValue = input
                    input = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("* $currentValue")
            }

            Button(
                onClick = {
                    val value = input.toDoubleOrNull() ?: 1.0
                    if (value != 0.0) {
                        result /= value
                    }
                    currentValue = input
                    input = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("/ $currentValue")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = 0.0
                input = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpar")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CounterApp_21_11Theme {
        CounterApp("Android")
    }
}