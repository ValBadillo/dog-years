package com.example.dogsd

import com.example.dogs.ui.theme.DogsTheme
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AñosPerrunos()
                }
            }
        }
    }
}

@Composable
fun AñosPerrunos() {
    PosicionPantalla(
        titulo = "Mis Años Perrunos",
        imagen = painterResource(id = R.drawable.a_os)
    )
}

@Composable
private fun PosicionPantalla(titulo: String, imagen: Painter, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var edad by remember { mutableStateOf("") }
        var resultado by remember { mutableStateOf("") }

        Image(
            painter = imagen,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center
        )

        Text(
            text = titulo,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )

        OutlinedTextField(
            value = edad,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    edad = it
                } else {
                    Toast.makeText(context, "Solo se permiten números", Toast.LENGTH_SHORT).show()
                }
            },
            label = { Text("Mi edad humana") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ElevatedButton(
            onClick = {
                if (edad.isNotEmpty()) {
                    val res = edad.toInt() * 7
                    resultado = res.toString()
                }
            }
        ) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = resultado,
            readOnly = true,
            onValueChange = { resultado = it },
            label = { Text("Edad Perruna") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ElevatedButton(
            onClick = {
                edad = ""
                resultado = ""
            }
        ) {
            Text("Borrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DogsTheme {
        AñosPerrunos()
    }
}
