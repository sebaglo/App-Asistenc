package com.example.myasisten;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Almuerzo extends AppCompatActivity {

    private EditText etIDAlmuerzo, etIDUsuario, etFechaAlmuerzo;
    private Spinner spinnerMenuAlmuerzo;
    private Button btnGuardarAlmuerzo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almuerzo);

        etIDAlmuerzo = findViewById(R.id.etIDAlmuerzo);
        etIDUsuario = findViewById(R.id.etIDUsuario);
        etFechaAlmuerzo = findViewById(R.id.etFechaAlmuerzo);
        spinnerMenuAlmuerzo = findViewById(R.id.spinnerMenuAlmuerzo);
        btnGuardarAlmuerzo = findViewById(R.id.btnGuardarAlmuerzo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.menus_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMenuAlmuerzo.setAdapter(adapter);

        btnGuardarAlmuerzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarAlmuerzo();
            }
        });
    }

    private void guardarAlmuerzo() {
        String idAlmuerzo = etIDAlmuerzo.getText().toString();
        String idUsuario = etIDUsuario.getText().toString();
        String fechaAlmuerzo = etFechaAlmuerzo.getText().toString();
        String menuAlmuerzo = spinnerMenuAlmuerzo.getSelectedItem().toString();

        if (idAlmuerzo.isEmpty() || idUsuario.isEmpty() || fechaAlmuerzo.isEmpty()) {
            // Mostrar mensaje de error si algún campo está vacío
            Toast.makeText(Almuerzo.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Almuerzo.this, "Almuerzo Guardado: \n" +
                    "ID Almuerzo: " + idAlmuerzo + "\n" +
                    "ID Usuario: " + idUsuario + "\n" +
                    "Fecha Almuerzo: " + fechaAlmuerzo + "\n" +
                    "Menú del Almuerzo: " + menuAlmuerzo, Toast.LENGTH_LONG).show();


        }
    }
}
