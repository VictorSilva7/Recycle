package com.example.recyclebit;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View; // NOVO: Necessário para o OnClickListener
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.Calendar;
import java.util.Locale; // NOVO: Necessário para formatação de data DD/MM/AAAA

public class AgendamentoActivity extends AppCompatActivity {

    private EditText etData;
    private Spinner spinnerColeta, spinnerHora;
    private Button btnAgendar, btnCancelar;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        // 1. Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // 2. Inicializar Componentes de UI
        etData = findViewById(R.id.et_data);
        spinnerColeta = findViewById(R.id.spinner_ponto_coleta);
        spinnerHora = findViewById(R.id.spinner_hora);
        btnAgendar = findViewById(R.id.btn_agendar);
        btnCancelar = findViewById(R.id.btn_cancelar);
        calendar = Calendar.getInstance();

        // 3. Configurar Clique no Campo de Data (DatePicker)
        // CORREÇÃO: Usando a sintaxe de View.OnClickListener para maior robustez
        etData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePickerDialog();
            }
        });

        // 4. Configurar Botões de Ação
        btnAgendar.setOnClickListener(v -> agendarDescarte());
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void mostrarDatePickerDialog() {
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        // CORREÇÃO: Usando AgendarDescarteActivity.this para garantir o contexto
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                AgendarDescarteActivity.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    // CORREÇÃO: Formatação para DD/MM/AAAA usando Locale e String.format
                    String dataSelecionada = String.format(
                            Locale.getDefault(),
                            "%02d/%02d/%d",
                            dayOfMonth,
                            monthOfYear + 1, // Mês é base zero, então somamos 1
                            year
                    );

                    etData.setText(dataSelecionada);
                    // Opcional: Atualiza o objeto Calendar
                    calendar.set(year, monthOfYear, dayOfMonth);
                }, ano, mes, dia);

        datePickerDialog.show();
    }

    private void agendarDescarte() {
        String ponto = spinnerColeta.getSelectedItem().toString();
        String data = etData.getText().toString();
        String hora = spinnerHora.getSelectedItem().toString();

        if (data.isEmpty() || ponto.contains("Selecione") || hora.contains("Selecione")) {
            Toast.makeText(this, "Preencha a Data, Hora e Ponto de Coleta.", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Descarte agendado para " + data + " às " + hora, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            Toast.makeText(this, "Fazendo logout...", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}