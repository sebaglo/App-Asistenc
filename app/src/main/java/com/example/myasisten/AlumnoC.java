package com.example.myasisten;

public class AlumnoC {

    //Instanciamos las variables
    private int codigo;
    private String nombre;
    private String rut;
    private boolean asistio;

    //Construimos los setters y getters
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
