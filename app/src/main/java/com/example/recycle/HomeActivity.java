package com.example.recycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private CardView cardSchedule;
    private CardView cardMySchedules;
    private ImageView ivLogout;
    private TextView tvGreeting;
    private TextView tvUserNameToolbar; // NOVO: Para o nome na Toolbar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Inicializa elementos da UI
        cardSchedule = findViewById(R.id.card_schedule);
        cardMySchedules = findViewById(R.id.card_my_schedules);
        ivLogout = findViewById(R.id.iv_logout);

        // Inicializa os TextViews
        tvGreeting = findViewById(R.id.tv_greeting);
        tvUserNameToolbar = findViewById(R.id.tv_user_name); // Mapeia o TextView da Toolbar

        // NOVO: Recuperar o nome do usuário do LoginActivity
        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");

        // Atualiza os textos se o nome foi passado
        if (userName != null && !userName.isEmpty()) {
            // 1. Atualiza a saudação principal
            tvGreeting.setText("Bem-vindo, " + userName + " !");

            // 2. Atualiza o nome na Toolbar
            tvUserNameToolbar.setText(userName);
        } else {
            // Caso não receba o nome, usa um nome padrão
            tvGreeting.setText("Bem-vindo(a)!");
            tvUserNameToolbar.setText("Usuário");
        }


        // Lógica de Clique: Agendar Descarte
        cardSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navegar para a tela de Agendamento (próxima tela)
                Toast.makeText(HomeActivity.this, "Indo para Agendar Descarte...", Toast.LENGTH_SHORT).show();
            }
        });

        // Lógica de Clique: Meus Agendamentos
        cardMySchedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navegar para a tela de Meus Agendamentos
                Toast.makeText(HomeActivity.this, "Indo para Meus Agendamentos...", Toast.LENGTH_SHORT).show();
            }
        });

        // Lógica de Clique: Logout
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volta para a tela de Login
                Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginIntent);
                finish();
            }
        });
    }
}