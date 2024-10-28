package com.example.bookstoreapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    var books by remember { mutableStateOf<List<Book>>(emptyList()) }
    val db = Firebase.firestore

    // Carrega os livros ao entrar na tela
    LaunchedEffect(Unit) {
        db.collection("books").get().addOnSuccessListener { result ->
            books = result.map { document ->
                Book(document.id, document.getString("title") ?: "", document.getString("author") ?: "", document.getString("description") ?: "")
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Livros", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("addBook") }) {
            Text("Adicionar Livro")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(books) { book ->
                BookItem(book, navController)
            }
        }
    }
}
