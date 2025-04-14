package com.example.myasisten;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myasisten.databinding.ActivityMainBinding;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Instanciamos Variables
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button btnSalir = findViewById(R.id.btnSalir);
        Button btnAlumno = findViewById(R.id.btnAlumno);
        Button btnCurso = findViewById(R.id.btnCurso);
        Button btnAsistencia = findViewById(R.id.btnAsistencia);
        Button btnAlmuerzo = findViewById(R.id.btnAlmuerzo);
        Button btnUsuario = findViewById(R.id.btnUsuario);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button btnPistola = findViewById(R.id.btnPistola);

        //Botono para salir de la aplicacion
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        //boton para ingresae a la seccion escaneo por pistola
        btnPistola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EscaneoPistola.class);
            }
        });

        //Boton para ingresar a la seccion alumno
        btnAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, alumno.class);
                startActivity(intent);
            }
        });

        //Boton para ingresar a la seccion de los cursos
        btnCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, curso.class));
            }
        });

        //Boton para ingresar a la seccion de las asistencias
        btnAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Asistencia.class));
            }
        });

        //Boton para ingresar a la seccion de almuerzo
        btnAlmuerzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Almuerzo.class));
            }
        });

        //Boton para ingresar a la seccion de usuario
        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Usuario.class));
            }
        });

    }
}
