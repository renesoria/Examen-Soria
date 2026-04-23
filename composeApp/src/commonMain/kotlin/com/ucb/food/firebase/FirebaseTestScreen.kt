package com.ucb.food.firebase

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FirebaseTestScreen(
    viewModel: FirebaseTestViewModel = koinViewModel()
) {
    var textToSave by remember { mutableStateOf("") }
    val localTodos by viewModel.localTodos.collectAsState()
    val cachedConfig by viewModel.cachedConfig.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 32.dp)
    ) {
        Text("Examen Movil", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Card neutral (antes era morada)
        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Sincronización & Caché Local", style = MaterialTheme.typography.titleMedium)
                Text("Valor en Room (Caché):", style = MaterialTheme.typography.labelLarge)
                Text(cachedConfig, style = MaterialTheme.typography.bodyLarge, color = Color(0xFF2E7D32)) // Verde oscuro para el texto
                Text("Si apagas el internet, este valor persistirá.", style = MaterialTheme.typography.bodySmall)
            }
        }

        // Realtime Database con fondo verde (invertido y cambio de color)
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)) // Verde claro
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Realtime Database", style = MaterialTheme.typography.titleMedium)
                TextField(
                    value = textToSave,
                    onValueChange = { textToSave = it },
                    label = { Text("Mensaje a guardar") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = { viewModel.testRealtimeDatabase(textToSave) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Botón Verde
                ) {
                    Text("Guardar en Firebase", color = Color.White)
                }
                Text("Estado: ${viewModel.realtimeDbStatus}")
            }
        }
    }
}
