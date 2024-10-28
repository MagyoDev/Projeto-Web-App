# Projeto Web e Aplicativo de Bookstore

Este projeto consiste em um sistema de gerenciamento de uma livraria, com uma aplicação web para administração de livros e um aplicativo Android para interação com o catálogo e gerenciamento de contas. O back-end utiliza Firebase para autenticação e armazenamento dos dados, enquanto a interface web é desenvolvida com Node.js, Express e Handlebars. O aplicativo Android é construído com Jetpack Compose e Material Design.

## Índice

- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Instalação e Configuração](#instalação-e-configuração)
- [Executando o Projeto](#executando-o-projeto)
  - [Web](#executando-a-aplicação-web)
  - [Android](#executando-o-aplicativo-android)
- [Estrutura do Código](#estrutura-do-código)

---

## Funcionalidades

### Aplicativo Web
- Listagem de todos os livros cadastrados.
- Cadastro de novos livros com título, autor e descrição.
- Edição de informações de livros existentes.
- Exclusão de livros do catálogo.
  
### Aplicativo Android
- Registro de novos usuários.
- Autenticação de usuários com Firebase Auth.
- Listagem de livros do catálogo com visualização de detalhes.
- Adição, edição e exclusão de livros (para usuários autenticados).
- Sincronização com a aplicação web.

## Tecnologias Utilizadas

### Web
- **Node.js** com **Express**: Servidor para rotas e lógica de back-end.
- **Firebase Admin SDK**: Conexão com Firestore para gerenciar dados.
- **Express-Handlebars**: Engine de templates para renderizar páginas dinâmicas.
- **Bootstrap/Tailwind CSS**: Estilização de interfaces para melhorar a experiência do usuário.

### Android
- **Jetpack Compose**: UI declarativa para desenvolvimento Android.
- **Firebase Firestore e Firebase Auth**: Armazenamento e autenticação de usuários.
- **Material Design**: Interface visual do aplicativo, seguindo as diretrizes do Material Design.

## Instalação e Configuração

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/MagyoDev/Projeto-Web-App.git
   cd Projeto-Web-App
   ```

2. **Configuração do Firebase**:
   - Configure um projeto no [Firebase Console](https://console.firebase.google.com/).
   - Habilite o Firestore e a Autenticação com Email/Senha.
   - Gere um arquivo de credenciais de administração (`firebasefirestore.json`) e coloque-o no diretório do projeto web.

3. **Configuração do Aplicativo Web**:
   - Instale as dependências do Node.js:
     ```bash
     cd web
     npm install
     npm install express express-handlebars firebase-admin body-parser path
     ```

4. **Configuração do Aplicativo Android**:
   - No projeto Android, substitua o arquivo `google-services.json` na pasta `app` pelo seu arquivo de configuração baixado do Firebase Console.

## Executando o Projeto

### Executando a Aplicação Web

1. **Inicialize o servidor**:
   ```bash
   node app.js
   ```
   
2. **Acesse a aplicação**:
   - Acesse `http://localhost:3000` no navegador para visualizar a interface.

3. **Rotas principais**:
   - `/books`: Visualização de todos os livros.
   - `/books/:id/edit`: Edição de um livro específico.
   - `/books/:id/delete`: Exclusão de um livro específico.

### Executando o Aplicativo Android

1. **Abra o projeto Android**: Use o Android Studio.
2. **Sincronize as dependências**: Clique em "Sync Project with Gradle Files" no Android Studio.
3. **Execute o aplicativo**:
   - Conecte um dispositivo Android ou use um emulador e clique em "Run" para iniciar o aplicativo.

## Estrutura do Código

### Web

```plaintext
web/
├── public/                 # Arquivos estáticos (CSS, imagens)
├── views/                  # Templates Handlebars
├── firebasefirestore.json  # Arquivo de credenciais Firebase
├── app.js                  # Configuração principal do servidor Express
└── package.json            # Dependências e scripts
```

### Android

```plaintext
android/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/bookstoreapp/  # Código fonte do app
│   │   │   └── res/                            # Recursos (layouts, ícones)
│   └── build.gradle                            # Configurações do projeto
└── google-services.json                        # Configuração do Firebase
```
