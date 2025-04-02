package com.example.myasisten;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class curso extends AppCompatActivity {

    private EditText etCursoNombre, etProfesorNombre, etParadocenteNombre;
    private Button btnGuardarCurso, btnVerCurso;
    private TextView tvCursoId;

    private static final String PREFS_NAME = "CoursePrefs";
    private static final String COURSE_ID_KEY = "currentCourseId";
    private static final String COURSES_LIST_KEY = "coursesList";
    private int currentCourseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curso);

        tvCursoId = findViewById(R.id.tvCursoId);
        etCursoNombre = findViewById(R.id.etCursoNombre);
        etProfesorNombre = findViewById(R.id.etProfesorNombre);
        etParadocenteNombre = findViewById(R.id.etParadocenteNombre);
        btnGuardarCurso = findViewById(R.id.btnGuardarCurso);
        btnVerCurso = findViewById(R.id.btnVerCurso);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        currentCourseId = sharedPreferences.getInt(COURSE_ID_KEY, 1000);

        tvCursoId.setText("ID del curso: " + currentCourseId);

        btnGuardarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCurso();
            }
        });

        btnVerCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(curso.this, ListadoCursoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void guardarCurso() {
        String cursoNombre = etCursoNombre.getText().toString();
        String profesorNombre = etProfesorNombre.getText().toString();
        String paradocenteNombre = etParadocenteNombre.getText().toString();

        if (cursoNombre.isEmpty() || profesorNombre.isEmpty() || paradocenteNombre.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Guardar curso en la lista
            Set<String> courses = sharedPreferences.getStringSet(COURSES_LIST_KEY, new HashSet<>());
            courses.add("ID: " + currentCourseId + " - " + cursoNombre + " | Prof: " + profesorNombre + " | Paradocente: " + paradocenteNombre);
            editor.putStringSet(COURSES_LIST_KEY, courses);

            // Incrementar ID
            currentCourseId++;
            editor.putInt(COURSE_ID_KEY, currentCourseId);
            editor.apply();

            tvCursoId.setText("ID del curso: " + currentCourseId);
            Toast.makeText(this, "Curso Guardado", Toast.LENGTH_LONG).show();
        }
    }
}
