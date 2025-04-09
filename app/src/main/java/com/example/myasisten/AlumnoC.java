package com.example.myasisten;

public class AlumnoC {
    private String nombre;
    private String rut;
    private boolean asistio;

    public AlumnoC(String nombre, String rut, boolean asistio) {
        this.nombre = nombre;
        this.rut = rut;
        this.asistio = asistio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public boolean isAsistio() {
        return asistio;
    }
}
