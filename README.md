# ♻️ Projeto Recycle (Agendamento de Descarte Eletrônico)

Este é um projeto de aplicativo Android nativo, desenvolvido em Java, como parte de [Mencionar o nome da disciplina ou o propósito do projeto]. O objetivo é criar uma plataforma onde os usuários podem se cadastrar, logar e agendar o descarte de lixo eletrônico (e-waste).

O projeto utiliza o Google Firebase como backend para autenticação de usuários e armazenamento de dados em nuvem.

## Status do Projeto

> 🚧 **Em Desenvolvimento** 🚧

## ✨ Funcionalidades

Atualmente, as seguintes funcionalidades estão implementadas:

* **Autenticação de Usuários:**
    * Tela de Login funcional.
    * Integração com **Firebase Authentication** (login por E-mail e Senha).
* **Painel Principal (Home):**
    * Exibição do nome do usuário logado.
    * Botão de Logout (encerra a sessão do Firebase).
    * Navegação para o formulário de agendamento.
* **Formulário de Agendamento:**
    * Formulário completo com seleção de data (DatePicker), hora (Spinner) e tipo de material (ChipGroup).
    * Envio dos dados do agendamento para o **Cloud Firestore**.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java
* **Plataforma:** Android Nativo (SDK)
* **Layout:** XML (ConstraintLayout, CardView, Material Components)
* **Banco de Dados (BaaS):**
    * **Firebase Authentication** (para gerenciamento de usuários)
    * **Cloud Firestore** (para armazenamento de dados dos agendamentos)

---

## 🚀 Como Executar o Projeto

Para clonar e executar este projeto localmente, você precisará do Android Studio e de uma configuração do Firebase.

### 1. Clonar o Repositório

```bash
git clone [https://github.com/VictorSilva7/Recycle.git](https://github.com/VictorSilva7/Recycle.git)
```

### 2. Configuração do Firebase (Passo Essencial)

Este projeto utiliza o Google Firebase para funcionar. Por razões de segurança, o arquivo de configuração `google-services.json` (que contém as chaves de API) não está incluído no repositório (ele está no `.gitignore`).

**Para o projeto compilar, você deve gerar seu próprio arquivo:**

1.  Acesse o [Console do Firebase](https://console.firebase.google.com/).
2.  Crie um novo projeto no Firebase.
3.  Dentro do projeto, clique em "Adicionar aplicativo" e selecione o ícone do Android.
4.  No campo **Nome do pacote Android**, insira exatamente:
    ```
    com.example.recycle
    ```
    *(Nota: Este é o `applicationId` atual do projeto. Se você o refatorar, precisará registrar o novo nome do pacote aqui.)*
5.  Registre o app e **baixe o arquivo `google-services.json`**.
6.  **Copie** o arquivo `google-services.json` que você baixou e **cole-o** na pasta `app/` do seu projeto no Android Studio.
    * O caminho final deve ser: `Recycle/app/google-services.json`
7.  No Console do Firebase, vá para a seção **Authentication**:
    * Clique em "Começar".
    * Habilite o provedor de login **"E-mail/senha"**.
8.  No Console do Firebase, vá para a seção **Cloud Firestore**:
    * Clique em "Criar banco de dados".
    * Inicie em **Modo de Teste** (para permitir leituras e gravações durante o desenvolvimento).

### 3. Sincronizar e Executar

Após adicionar o `google-services.json`, o Android Studio deve sincronizar o Gradle. Você pode então executar o aplicativo em um emulador ou dispositivo físico.

*(Lembre-se de criar um usuário de teste no painel de Autenticação do Firebase para poder fazer o login!)*

## 🖼️ Telas do Aplicativo

| Tela de Login | Tela Home (Painel) | Tela de Agendamento |
| :---: | :---: | :---: |
| *[Em desenvolvimento...]* | *[Em desenvolvimento...]* | *[Em desenvolvimento...]* |

## 👨‍💻 Autor

Feito por [Victor Gabriel Luiz da Silva](https://github.com/VictorSilva7).
