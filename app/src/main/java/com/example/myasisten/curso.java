package com.example.myasisten;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class curso extends AppCompatActivity {

    private EditText etCursoId, etCursoNombre, etProfesorNombre, etParadocenteNombre;
    private Button btnGuardarCurso;
    private TextView tvCursoId;

    private static final String PREFS_NAME = "CoursePrefs"; // Nombre del archivo de preferencias
    private static final String COURSE_ID_KEY = "currentCourseId";
    private static int currentCourseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curso);

        tvCursoId= findViewById(R.id.tvCursoId);
        etCursoId = findViewById(R.id.etCursoId);
        etCursoNombre = findViewById(R.id.etCursoNombre);
        etProfesorNombre = findViewById(R.id.etProfesorNombre);
        etParadocenteNombre = findViewById(R.id.etParadocenteNombre);
        btnGuardarCurso = findViewById(R.id.btnGuardarCurso);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        currentCourseId = sharedPreferences.getInt(COURSE_ID_KEY, 1000);

        tvCursoId.setText("ID del curso: " + currentCourseId);

        btnGuardarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCurso();
            }
        });
    }

    private void guardarCurso() {

        String cursoNombre = etCursoNombre.getText().toString();
        String profesorNombre = etProfesorNombre.getText().toString();
        String paradocenteNombre = etParadocenteNombre.getText().toString();

        if (cursoNombre.isEmpty() || profesorNombre.isEmpty() || paradocenteNombre.isEmpty()) {
            // Mostrar un mensaje de error si algún campo está vacío
            Toast.makeText(curso.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            currentCourseId--;

            tvCursoId.setText("Id del curso: " + currentCourseId);

            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(COURSE_ID_KEY, currentCourseId);
            editor.apply();

            Toast.makeText(curso.this, "Curso Guardado: \n" +
                    "ID: " + currentCourseId + "\n" +
                    "Nombre: " + cursoNombre + "\n" +
                    "Profesor: " + profesorNombre + "\n" +
                    "Paradocente: " + paradocenteNombre, Toast.LENGTH_LONG).show();
        }
    }
}
