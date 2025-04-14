package com.example.myasisten;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Asistencia extends AppCompatActivity {

    //Intanciamos varibles
    private Button btnsi, btnno, btnatras, btnVerAlumnos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asistencia);

        //Buscamos variables e Instanciamos
        btnVerAlumnos = findViewById(R.id.btnVerlista);
        btnatras = findViewById(R.id.btnAtras);
        btnsi = findViewById(R.id.btnSi);
        btnno = findViewById(R.id.btnNo);

        TextView tvRutResult = findViewById(R.id.tvRutResult);
        String rut = getIntent().getStringExtra("rut");
        String nombre = getIntent().getStringExtra("name");

        if (rut != null && !rut.isEmpty()) {
            tvRutResult.setText("Nombre: " + nombre + "\nRUT: " + (rut.isEmpty() ? "No válido" : rut));
        } else {
            tvRutResult.setText("RUT: No válido");
        }

        //Boton para volver al menu principal
        btnatras.setOnClickListener(v -> onBackPressed());

        //Boton para agregar si el alumnos esta presente
        btnsi.setOnClickListener(v -> {

            // Registra la asistencia en MySQL
            registrarAsistencia(nombre, rut, true);
        });

        //Boton para agregar si el alumnos esta ausente
        btnno.setOnClickListener(v -> {

            // Registra la ausencia en MySQL
            registrarAsistencia(nombre, rut, false);
        });

        //Boton para ver la lista de alumnos
        btnVerAlumnos.setOnClickListener(v -> {
            Intent intent = new Intent(Asistencia.this, ListadoAlumnos.class);
            startActivity(intent);
        });
    }

    // Método para registrar la asistencia en MySQL
    private void registrarAsistencia(String name, String rut, boolean presente) {
        new Thread(() -> {
            try {
                // URL de la API PHP
                URL url = new URL("http://localhost/phpmyadmin/index.php?route=/sql&db=develop&table=asistencia&pos=0/guardar_asistencia.php");

                // Configurar la conexión HTTP
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Crear los parámetros de la petición
                String data = "name=" + name + "&rut=" + rut + "&presente=" + presente;

                // Enviar los datos
                OutputStream os = urlConnection.getOutputStream();
                os.write(data.getBytes());
                os.flush();
                os.close();

                // Obtener la respuesta del servidor
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    runOnUiThread(() -> {
                        String mensaje = presente ? "Asistencia registrada" : "Ausencia registrada";
                        Toast.makeText(Asistencia.this, mensaje, Toast.LENGTH_SHORT).show();
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(Asistencia.this, "Error al registrar la asistencia", Toast.LENGTH_SHORT).show());
                }

            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(Asistencia.this, "Error de conexión", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
