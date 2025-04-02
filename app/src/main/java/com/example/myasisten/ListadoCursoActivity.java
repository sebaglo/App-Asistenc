package com.example.myasisten;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class ListadoCursoActivity extends AppCompatActivity {

    private TextView tvListaCursos;
    private static final String PREFS_NAME = "CoursePrefs";
    private static final String COURSES_LIST_KEY = "coursesList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_curso);

        tvListaCursos = findViewById(R.id.tvListadoCursos); // Verifica que este ID sea el correcto

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        // Usamos un Set vacío como valor predeterminado si no hay datos
        Set<String> courses = sharedPreferences.getStringSet(COURSES_LIST_KEY, new HashSet<String>());

        if (courses != null && !courses.isEmpty()) {
            StringBuilder cursosTexto = new StringBuilder();
            for (String curso : courses) {
                cursosTexto.append(curso).append("\n\n");
            }
            tvListaCursos.setText(cursosTexto.toString());
        } else {
            tvListaCursos.setText("No hay cursos guardados.");
        }
    }
}
