const express = require('express');
const bodyParser = require('body-parser');
const admin = require('firebase-admin');
const path = require('path');
const exphbs = require('express-handlebars');

const app = express();
const port = 3000;

// Configurar o Firebase Admin
const serviceAccount = require('./firebasefirestore.json');
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
});

const db = admin.firestore();

// Middleware
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.json());
app.use(express.static(path.join(__dirname, 'public')));

// Configuração do Handlebars como view engine
app.engine('handlebars', exphbs.engine());
app.set('view engine', 'handlebars');
app.set('views', path.join(__dirname, 'views'));

// Rota para a página inicial
app.get('/', (req, res) => {
    res.redirect('/books');
});

// Listagem de livros
app.get('/books', async (req, res) => {
    try {
        const booksSnapshot = await db.collection('books').get();
        const books = booksSnapshot.docs.map(doc => ({ id: doc.id, ...doc.data() }));
        res.render('books', { books });
    } catch (error) {
        res.status(500).send(error.message);
    }
});

// Formulário para adicionar livro
app.post('/books', async (req, res) => {
    try {
        const { title, author, description } = req.body;
        const newBookRef = db.collection('books').doc();
        const newBook = { title, author, description, id: newBookRef.id };
        await newBookRef.set(newBook);
        res.redirect('/books');
    } catch (error) {
        res.status(500).send(error.message);
    }
});

// Rota para renderizar edição de livro
app.get('/books/:id/edit', async (req, res) => {
    try {
        const { id } = req.params;
        const bookSnapshot = await db.collection('books').doc(id).get();
        if (!bookSnapshot.exists) {
            res.status(404).send("Livro não encontrado");
            return;
        }
        const book = { id, ...bookSnapshot.data() };
        res.render('edit', { book });
    } catch (error) {
        res.status(500).send(error.message);
    }
});

// Atualizar livro
app.post('/books/:id', async (req, res) => {
    try {
        const { id } = req.params;
        const { title, author, description } = req.body;
        await db.collection('books').doc(id).set({ title, author, description }, { merge: true });
        res.redirect('/books');
    } catch (error) {
        res.status(500).send(error.message);
    }
});

// Deletar livro
app.post('/books/:id/delete', async (req, res) => {
    try {
        const { id } = req.params;
        await db.collection('books').doc(id).delete();
        res.redirect('/books');
    } catch (error) {
        res.status(500).send(error.message);
    }
});

// Iniciar o servidor
app.listen(port, () => {
    console.log(`Servidor rodando em http://localhost:${port}`);
});
