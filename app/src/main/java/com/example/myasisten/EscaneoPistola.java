package com.example.myasisten;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EscaneoPistola extends AppCompatActivity {

    //Instanciamos las variables
    private EditText etEscaneo;
    private TextView tvResultado;
    private Button btnVerregistro, btnVolver, btnGuardar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_pistola);

        //Buscamos las variables e instanciamos
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVerregistro = findViewById(R.id.btnVerRegistro);
        btnVolver = findViewById(R.id.btnvolver);
        etEscaneo = findViewById(R.id.etEscaneo);
        tvResultado = findViewById(R.id.tvResultado);

        //Boton para guardar los datos en MYSQL
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Boton para ver el registro de los alumnos
        btnVerregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscaneoPistola.this, ListadoAlumnos.class);
                startActivity(intent);
            }
        });

        //Boton para volver a la pantalla principal
        btnVolver.setOnClickListener(v -> onBackPressed());

        //Script de la pistola de escaneo
        etEscaneo.requestFocus();
        etEscaneo.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                String codigoEscaneado = etEscaneo.getText().toString().trim();
                if (!codigoEscaneado.isEmpty()) {
                    tvResultado.setText("Código escaneado:\n" + codigoEscaneado);
                    etEscaneo.setText("");
                }
                return true;
            }
            return false;
        });
    }
}
