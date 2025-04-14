package com.example.myasisten;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class ListadoCursoActivity extends AppCompatActivity {

    //Instanciamos variables
    private TextView tvListaCursos;
    private Button btnBorrar;
    private static final String PREFS_NAME = "CoursePrefs";
    private static final String COURSES_LIST_KEY = "coursesList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_curso);

        //Instanciamos las variables y las buscamos
        tvListaCursos = findViewById(R.id.tvListadoCursos);
        btnBorrar = findViewById(R.id.btnBorrar);

        //Boton para eliminar los datos de la lista
        btnBorrar.setOnClickListener(v -> {
            AlumnoManager.getInstance().limpiarAlumnos();
            Toast.makeText(this, "Lista Vaciada", Toast.LENGTH_SHORT).show();
        });

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
