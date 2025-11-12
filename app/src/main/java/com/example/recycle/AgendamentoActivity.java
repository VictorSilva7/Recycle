package com.example.recycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
// import android.widget.ImageView; // Não é mais necessário

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AgendamentoActivity extends AppCompatActivity {

    // VARIÁVEIS DE INSTÂNCIA
    private Toolbar toolbar;
    // private TextView tvToolbarUserName; // REMOVIDO
    private TextInputEditText etData;
    private TextInputEditText etAgendamentoEmail;

    private Spinner spinnerColeta, spinnerHora;
    private ChipGroup chipGroupMateriais;
    private TextInputEditText etAgendamentoTelefone, etObservacoes;
    private Button btnAgendar, btnCancelar;

    // DADOS FIREBASE
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String currentUserName;
    private String currentUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        initializeUI();
        getUserDataFromIntent();
        setupToolbar();
        setupListeners();
    }

    private void initializeUI() {
        toolbar = findViewById(R.id.toolbar_agendamento);
        // tvToolbarUserName = findViewById(R.id.tv_toolbar_user_name); // REMOVIDO
        etData = findViewById(R.id.et_data);
        etAgendamentoEmail = findViewById(R.id.et_agendamento_email);
        etAgendamentoTelefone = findViewById(R.id.et_agendamento_telefone);
        etObservacoes = findViewById(R.id.et_observacoes);
        spinnerColeta = findViewById(R.id.spinner_coleta);
        spinnerHora = findViewById(R.id.spinner_hora);
        chipGroupMateriais = findViewById(R.id.chip_group_materiais);
        btnAgendar = findViewById(R.id.btn_agendar);
        btnCancelar = findViewById(R.id.btn_cancelar_agendamento);
    }

    // ===============================================
    // DADOS E TOOLBAR
    // ===============================================

    private void getUserDataFromIntent() {
        currentUserName = getIntent().getStringExtra("USER_NAME");
        currentUserEmail = getIntent().getStringExtra("USER_EMAIL");

        // Lógica de 'tvToolbarUserName' REMOVIDA

        if (etAgendamentoEmail != null && currentUserEmail != null) {
            etAgendamentoEmail.setText(currentUserEmail);
        } else if (etAgendamentoEmail != null && mAuth.getCurrentUser() != null) {
            currentUserEmail = mAuth.getCurrentUser().getEmail();
            etAgendamentoEmail.setText(currentUserEmail);
        }
    }

    private void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
            toolbar.setNavigationOnClickListener(v -> finish());
        }
    }

    private void setupListeners() {
        // Listeners dos campos de data e botões (mantidos)
        if (etData != null) {
            etData.setOnClickListener(v -> showDatePickerDialog());
        }
        if (btnAgendar != null) {
            btnAgendar.setOnClickListener(v -> saveScheduling());
        }
        if (btnCancelar != null) {
            btnCancelar.setOnClickListener(v -> finish());
        }

        // Listener de Logout (REMOVIDO, pois o 'iv_logout_agendamento' foi removido do XML)
    }

    private void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AgendamentoActivity.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = String.format(Locale.getDefault(), "%02d/%02d/%d",
                            selectedDay, selectedMonth + 1, selectedYear);
                    etData.setText(date);
                }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    // ===============================================
    // LÓGICA DE SALVAMENTO (FIRESTORE) - (Mantida)
    // ===============================================

    private void saveScheduling() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // 1. VERIFICAÇÃO DE AUTENTICAÇÃO
        if (currentUser == null) {
            Toast.makeText(this, "Erro: Usuário não autenticado. Faça login novamente.", Toast.LENGTH_LONG).show();
            return;
        }

        // Obtém dados de forma segura
        String pontoColeta = spinnerColeta != null && spinnerColeta.getSelectedItem() != null ?
                spinnerColeta.getSelectedItem().toString() : "";
        String data = etData != null ? etData.getText().toString() : "";
        String hora = spinnerHora != null && spinnerHora.getSelectedItem() != null ?
                spinnerHora.getSelectedItem().toString() : "";

        String telefone = etAgendamentoTelefone != null ? etAgendamentoTelefone.getText().toString().trim() : "";
        String observacoes = etObservacoes != null ? etObservacoes.getText().toString().trim() : "";

        List<String> materiaisSelecionados = getSelectedMaterials();

        // 2. VALIDAÇÃO BÁSICA
        if (spinnerColeta == null || spinnerColeta.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Por favor, selecione um ponto de coleta válido.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (data.isEmpty() || hora.equals("Selecione o horário")) {
            Toast.makeText(this, "Data e hora são obrigatórias.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (materiaisSelecionados.isEmpty()) {
            Toast.makeText(this, "Selecione pelo menos um tipo de material.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 3. PREPARA OS DADOS
        Map<String, Object> agendamento = new HashMap<>();
        agendamento.put("userId", currentUser.getUid());
        agendamento.put("userEmail", currentUserEmail);
        agendamento.put("userName", currentUserName);
        agendamento.put("pontoColeta", pontoColeta);
        agendamento.put("dataAgendamento", data);
        agendamento.put("horaAgendamento", hora);
        agendamento.put("materiais", materiaisSelecionados);
        agendamento.put("telefone", telefone);
        agendamento.put("observacoes", observacoes);
        agendamento.put("status", "Pendente");

        // 4. SALVA NO FIRESTORE
        db.collection("agendamentos")
                .add(agendamento)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(AgendamentoActivity.this, "Agendamento realizado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AgendamentoActivity.this, "Erro ao agendar: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

    private List<String> getSelectedMaterials() {
        List<String> selected = new ArrayList<>();
        if (chipGroupMateriais != null) {
            for (int i = 0; i < chipGroupMateriais.getChildCount(); i++) {
                View child = chipGroupMateriais.getChildAt(i);
                if (child instanceof Chip) {
                    Chip chip = (Chip) child;
                    if (chip.isChecked()) {
                        selected.add(chip.getText().toString());
                    }
                }
            }
        }
        return selected;
    }
}