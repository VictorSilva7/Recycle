package com.example.recycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private CardView cardSchedule;
    private CardView cardMySchedules;
    private TextView tvUserName;
    private TextView tvGreeting;

    // Instância do Firebase Auth
    private FirebaseAuth mAuth;

    // Variáveis para armazenar dados do usuário REAL
    private String userEmail;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        // Oculta a barra de título padrão se estiver usando a customizada no XML
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        initializeUI();
        loadUserData();
        setupListeners();
    }

    private void initializeUI() {
        cardSchedule = findViewById(R.id.card_schedule);
        cardMySchedules = findViewById(R.id.card_my_schedules);
        tvUserName = findViewById(R.id.tv_user_name);
        tvGreeting = findViewById(R.id.tv_greeting);
        // Não é necessário inicializar o ivLogout novamente aqui, pois ele será acessado no Listener
    }

    private void loadUserData() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Pega o email do Firebase se o usuário estiver logado
        if (currentUser != null) {
            userEmail = currentUser.getEmail();
            // Pega o nome do Intent (que vem do Login) ou usa a primeira parte do email
            userName = getIntent().getStringExtra("USER_NAME");
            if (userName == null || userName.isEmpty()) {
                userName = userEmail.split("@")[0];
            }
        } else {
            // Deve ser impossível se o Login funcionar, mas garante um fallback
            userName = "Usuário";
            userEmail = "";
        }

        // Atualiza a UI
        tvUserName.setText(userName);
        tvGreeting.setText("Bem-vindo, " + userName + " !");
    }

    private void setupListeners() {
        // Lógica de Clique: Agendar Descarte (Navegação CORRIGIDA)
        cardSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Abrindo Agendar Descarte...", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(HomeActivity.this, AgendamentoActivity.class);
                // Passa o nome e o email REAL do usuário
                intent.putExtra("USER_NAME", userName);
                intent.putExtra("USER_EMAIL", userEmail);
                startActivity(intent);
            }
        });

        // Lógica de Clique: Meus Agendamentos
        cardMySchedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Indo para Meus Agendamentos...", Toast.LENGTH_SHORT).show();
                // TODO: Navegar para a Meus Agendamentos Activity
            }
        });

        // Lógica de Clique: Logout
        ImageView ivLogout = findViewById(R.id.iv_logout);
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut(); // Desloga o usuário do Firebase

                // Volta para a tela de Login
                Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
                // Estas flags impedem o usuário de voltar para a Home usando o botão "Voltar"
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginIntent);
                finish(); // Finaliza a HomeActivity
            }
        });

        // Lógica de Clique: Meus Agendamentos (AGORA NAVEGA!!! finalmente)
        cardMySchedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MeusAgendamentosActivity.class);
                // Passa os dados do usuário para a próxima tela também
                intent.putExtra("USER_NAME", userName);
                intent.putExtra("USER_EMAIL", userEmail);
                startActivity(intent);
            }
        });
    }
}