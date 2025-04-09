package com.example.myasisten;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListadoAlumnos extends AppCompatActivity {

    private ListView listViewAlumnos;
    private Button btnvolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_alumnos);

        // Inicializamos el ListView
        btnvolver = findViewById(R.id.btnRegresar);
        listViewAlumnos = findViewById(R.id.listViewAlumnos);

        btnvolver.setOnClickListener(v -> onBackPressed());

        // Obtenemos los alumnos registrados
        List<AlumnoC> alumnos = AlumnoManager.getInstance().obtenerAlumnos();

        // Creamos un adaptador para llenar el ListView
        AlumnoAdapter adapter = new AlumnoAdapter(alumnos);
        listViewAlumnos.setAdapter(adapter);
    }

    // Adapter personalizado para mostrar los alumnos en la lista
    private class AlumnoAdapter extends ArrayAdapter<AlumnoC> {

        public AlumnoAdapter(List<AlumnoC> data) {
            super(ListadoAlumnos.this, 0, data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AlumnoC alumno = getItem(position);

            // Inflamos la vista para cada item del ListView
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_alumno, parent, false);
            }

            // Referenciamos los TextViews
            TextView tvNombre = convertView.findViewById(R.id.tvNombre);
            TextView tvRut = convertView.findViewById(R.id.tvRut);
            TextView tvAsistencia = convertView.findViewById(R.id.tvAsistencia);

            // Asignamos los datos del alumno al item
            tvNombre.setText("Nombre: " + alumno.getNombre());
            tvRut.setText("RUT: " + alumno.getRut());
            tvAsistencia.setText("Asistencia: " + (alumno.isAsistio() ? "Sí" : "No"));

            return convertView;
        }
    }
}
