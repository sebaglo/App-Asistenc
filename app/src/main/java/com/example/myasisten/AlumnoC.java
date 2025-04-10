package com.example.myasisten;

public class AlumnoC {

    private int codigo; // lo podrías usar solo si después recibís el ID desde el servidor
    private String nombre;
    private String rut;
    private boolean asistio;

    public AlumnoC(String nombre, String rut, boolean asistio) {
        this.nombre = nombre;
        this.rut = rut;
        this.asistio = asistio;
    }

    public int getCodigo(){
        return codigo;
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
