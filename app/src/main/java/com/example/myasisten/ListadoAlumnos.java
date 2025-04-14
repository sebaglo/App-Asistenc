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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListadoAlumnos extends AppCompatActivity {

    //Instanciamos varibles
    private ListView listViewAlumnos;
    private Button btnvolver;
    private List<AlumnoC> alumnosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_alumnos);

        //Buscamos variables y las Instanciamos
        btnvolver = findViewById(R.id.btnRegresar);
        listViewAlumnos = findViewById(R.id.listViewAlumnos);
        alumnosList = new ArrayList<>();

        //Boton para volver al menu principal
        btnvolver.setOnClickListener(v -> onBackPressed());

        cargarAlumnosDesdeServidor();
    }

    //clase para guardar alumnos en sql
    private void cargarAlumnosDesdeServidor() {
        new Thread(() -> {
            try {

                URL url = new URL("http://localhost/phpmyadmin/index.php?route=/sql&db=develop&table=asistencia&pos=0/obtener_alumnos.php");

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
                StringBuilder stringBuilder = new StringBuilder();
                int data = reader.read();
                while (data != -1) {
                    stringBuilder.append((char) data);
                    data = reader.read();
                }

                String jsonResponse = stringBuilder.toString();
                JSONArray jsonArray = new JSONArray(jsonResponse);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject alumnoJson = jsonArray.getJSONObject(i);
                    String nombre = alumnoJson.getString("name");
                    String rut = alumnoJson.getString("rut");
                    boolean presente = alumnoJson.getBoolean("presente");

                    AlumnoC alumno = new AlumnoC(nombre, rut, presente);
                    alumnosList.add(alumno);
                }

                runOnUiThread(() -> {
                    AlumnoAdapter adapter = new AlumnoAdapter(alumnosList);
                    listViewAlumnos.setAdapter(adapter);
                });

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(ListadoAlumnos.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    //Construimos clase para el adaptador
    private class AlumnoAdapter extends ArrayAdapter<AlumnoC> {

        public AlumnoAdapter(List<AlumnoC> data) {
            super(ListadoAlumnos.this, 0, data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AlumnoC alumno = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_alumno, parent, false);
            }

            TextView tvNombre = convertView.findViewById(R.id.tvNombre);
            TextView tvRut = convertView.findViewById(R.id.tvRut);
            TextView tvAsistencia = convertView.findViewById(R.id.tvAsistencia);

            tvNombre.setText("Nombre: " + alumno.getNombre());
            tvRut.setText("RUT: " + alumno.getRut());
            tvAsistencia.setText("Asistencia: " + (alumno.isAsistio() ? "Sí" : "No"));

            return convertView;
        }
    }
}
