package com.example.recycle; // ATENÇÃO: Verifique se este é o nome correto do seu pacote!

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Oculta a Action Bar padrão se estiver presente
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // 1. Inicializa os elementos da UI (deve corresponder aos IDs do XML)
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        // 2. Define o listener de clique para o botão de Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    /**
     * Realiza a lógica de validação de login e navegação.
     */
    private void performLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lógica simples de validação (apenas para teste)
        if (email.equals("teste@email.com") && password.equals("123456")) {
            // Login bem-sucedido
            Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();

            // Cria a intenção de ir para HomeActivity
            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);

            // ENVIANDO O NOME DE TESTE "Trump" PARA A PRÓXIMA TELA
            homeIntent.putExtra("USER_NAME", "Trump");

            // Inicia a nova Activity
            startActivity(homeIntent);

            // Finaliza a LoginActivity para que o usuário não possa voltar
            finish();
        } else {
            // Credenciais inválidas
            Toast.makeText(this, "Credenciais inválidas. Tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
}