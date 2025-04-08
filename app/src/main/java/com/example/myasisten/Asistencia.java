package com.example.myasisten;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Asistencia extends AppCompatActivity {

    private Button btnsi, btnno, btnatras, btnVerAlumnos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asistencia);

        btnVerAlumnos = findViewById(R.id.btnVerListado);
        btnatras = findViewById(R.id.btnAtras);
        btnsi = findViewById(R.id.btnSi);
        btnno = findViewById(R.id.btnNo);

        TextView tvRutResult = findViewById(R.id.tvRutResult);
        String rut = getIntent().getStringExtra("rut");
        String nombre = getIntent().getStringExtra("name"); // Corregido

        if (rut != null && !rut.isEmpty()) {
            tvRutResult.setText("Nombre: " + nombre + "\nRUT: " + (rut.isEmpty() ? "No válido" : rut));
        } else {
            tvRutResult.setText("RUT: No válido");
        }
        btnatras.setOnClickListener(v -> onBackPressed());

        btnsi.setOnClickListener(v -> {
            Toast.makeText(Asistencia.this, "Asistencia registrada para: " + nombre, Toast.LENGTH_SHORT).show();
        });

        btnno.setOnClickListener(v -> {
            Toast.makeText(Asistencia.this, "Ausencia registrada para: " + nombre, Toast.LENGTH_SHORT).show();
        });
    }
}
