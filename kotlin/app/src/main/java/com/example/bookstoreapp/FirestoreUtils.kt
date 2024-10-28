package com.example.bookstoreapp

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

fun addBookToFirestore(book: Book, onComplete: () -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("books").add(book)
        .addOnSuccessListener {
            Log.d("Firestore", "Livro adicionado com sucesso!")
            onComplete()
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Erro ao adicionar livro", e)
        }
}

fun getBooksFromFirestore(callback: (List<Book>) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("books").get()
        .addOnSuccessListener { result ->
            val books = mutableListOf<Book>()
            for (document in result) {
                val book = document.toObject(Book::class.java).copy(id = document.id)
                books.add(book)
            }
            callback(books)
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Erro ao obter livros", e)
        }
}

fun getBookById(bookId: String, callback: (Book?) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("books").document(bookId).get()
        .addOnSuccessListener { document ->
            if (document != null) {
                callback(document.toObject(Book::class.java)?.copy(id = document.id))
            } else {
                callback(null)
            }
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Erro ao obter livro", e)
        }
}

fun updateBookInFirestore(book: Book, onComplete: () -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("books").document(book.id).set(book)
        .addOnSuccessListener {
            Log.d("Firestore", "Livro atualizado com sucesso!")
            onComplete()
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Erro ao atualizar livro", e)
        }
}

fun deleteBook(bookId: String) {
    val db = Firebase.firestore
    db.collection("books").document(bookId).delete()
}
