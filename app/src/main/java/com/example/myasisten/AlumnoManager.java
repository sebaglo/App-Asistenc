package com.example.myasisten;

import java.util.ArrayList;
import java.util.List;

public class AlumnoManager {

    private static AlumnoManager instancia;
    private final List<AlumnoC> alumnos;

    private AlumnoManager() {
        alumnos = new ArrayList<>();
    }

    public static AlumnoManager getInstance() {
        if (instancia == null) {
            instancia = new AlumnoManager();
        }
        return instancia;
    }

    public void agregarAlumno(AlumnoC alumno) {
        alumnos.add(alumno); // ← aquí estaba el error: se escribía "Alumno" con mayúscula
    }

    public List<AlumnoC> obtenerAlumnos() {
        return alumnos;
    }
}
