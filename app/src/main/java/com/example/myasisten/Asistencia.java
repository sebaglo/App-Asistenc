package com.example.myasisten;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Asistencia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asistencia);

        TextView tvRutResult = findViewById(R.id.tvRutResult);
        String rut = getIntent().getStringExtra("rut");
        String nombre = getIntent().getStringExtra("Nombre");

        if (rut != null && !rut.isEmpty()) {
            tvRutResult.setText("Nombre: " + nombre + "\nRUT: " + (rut.isEmpty() ? "No válido" : rut));
        } else {
            tvRutResult.setText("RUT: No válido");
        }
    }
}
