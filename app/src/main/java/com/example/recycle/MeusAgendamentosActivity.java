package com.example.recycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MeusAgendamentosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AgendamentoAdapter adapter;
    private List<Agendamento> agendamentoList;

    private TextInputEditText etDataInicial, etDataFinal;
    private Button btnFiltrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_agendamentos);

        setupToolbar();
        initializeViews();
        setupFilters();

        // Configura o RecyclerView
        recyclerView = findViewById(R.id.recycler_view_agendamentos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // CRIA OS DADOS ESTÁTICOS (DUMMY DATA)
        criarDadosEstaticos();

        // Inicializa e define o adapter
        adapter = new AgendamentoAdapter(this, agendamentoList);
        recyclerView.setAdapter(adapter);
    }

    private void initializeViews() {
        etDataInicial = findViewById(R.id.et_filtro_data_inicial);
        etDataFinal = findViewById(R.id.et_filtro_data_final);
        btnFiltrar = findViewById(R.id.btn_filtrar);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_meus_agendamentos);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // Pega o nome do usuário do Intent e exibe na Toolbar
        String userName = getIntent().getStringExtra("USER_NAME");
        if (userName != null) {
            TextView tvUser = findViewById(R.id.tv_toolbar_user_name);
            if (tvUser != null) {
                tvUser.setText(userName);
            }
        }
    }

    // Define os DatePickers para os filtros
    private void setupFilters() {
        etDataInicial.setOnClickListener(v -> showDatePickerDialog(etDataInicial));
        etDataFinal.setOnClickListener(v -> showDatePickerDialog(etDataFinal));

        btnFiltrar.setOnClickListener(v -> {
            // Lógica de filtro (Banco de Dados) virá depois
            Toast.makeText(this, "Filtrando...", Toast.LENGTH_SHORT).show();
        });
    }

    private void showDatePickerDialog(TextInputEditText editText) {
        final Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, day) -> {
                    String date = String.format(Locale.getDefault(), "%02d/%02d/%d", day, month + 1, year);
                    editText.setText(date);
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    // MÉTODO DE DADOS ESTÁTICOS (PARA USAR NO LUGAR DO FIREBASE POR ENQUANTO)
    private void criarDadosEstaticos() {
        agendamentoList = new ArrayList<>();
        agendamentoList.add(new Agendamento(
                "EcoTech Reciclagem",
                "#1",
                "23/09/2025 09:00",
                "Eletrônicos, Computadores"
        ));
        agendamentoList.add(new Agendamento(
                "Recicla Bem",
                "#2",
                "28/09/2025 14:00",
                "Eletrônicos, Celulares"
        ));
        agendamentoList.add(new Agendamento(
                "Descarte Certo",
                "#3",
                "13/09/2025 11:00",
                "Eletrônicos, Pilhas"
        ));
    }
}