package com.example.bookstoreapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookScreen(navController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Adicionar Livro", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = title, onValueChange = { title = it }, label = { Text("Título") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = author, onValueChange = { author = it }, label = { Text("Autor") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = description, onValueChange = { description = it }, label = { Text("Descrição") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val db = FirebaseFirestore.getInstance()
            val book = hashMapOf("title" to title, "author" to author, "description" to description)
            db.collection("books").add(book)
            navController.navigate("home")
        }) {
            Text("Adicionar Livro")
        }
    }
}
