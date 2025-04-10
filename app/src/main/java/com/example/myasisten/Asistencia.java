package com.example.myasisten;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Asistencia extends AppCompatActivity {

    private Button btnsi, btnno, btnatras, btnVerAlumnos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asistencia);

        btnVerAlumnos = findViewById(R.id.btnVerlista);
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
            AlumnoC alumno = new AlumnoC(nombre, rut, true);
            AlumnoManager.getInstance().agregarAlumno(alumno);

            // Mostrar mensaje local con código generado en la app (opcional)
            Toast.makeText(Asistencia.this, "Asistencia local registrada para: " + nombre + "\nCódigo local: " + alumno.getCodigo(), Toast.LENGTH_SHORT).show();

            // 🔽 Enviar a servidor PHP (Ferozo)
            String url = "https://tusitio.com/insertar_alumno.php"; // Reemplazá con tu URL real

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        if (!response.equals("error")) {
                            Toast.makeText(getApplicationContext(), "Alumno registrado en servidor\nCódigo MySQL: " + response, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error al registrar en servidor", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> Toast.makeText(getApplicationContext(), "Error de red: " + error.getMessage(), Toast.LENGTH_LONG).show()
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("nombre", alumno.getNombre());
                    params.put("rut", alumno.getRut());
                    params.put("presente", "1");
                    return params;
                }
            };

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(stringRequest);
        });
            btnno.setOnClickListener(v -> {
                Toast.makeText(Asistencia.this, "Ausencia registrada para: " + nombre, Toast.LENGTH_SHORT).show();
            });

            btnVerAlumnos.setOnClickListener(v -> {
                Intent intent = new Intent(Asistencia.this, ListadoAlumnos.class);
                startActivity(intent);
            });

        }
}

