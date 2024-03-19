package com.example.carnetv30;

import android.graphics.Bitmap;

public class Estudiante {
    private int id;
    private String nombre, apellido, cargo;
    private Bitmap foto;
    public Estudiante (int i, String n, String a, String c){
        id=i;
        nombre=n;
        apellido=a;
        cargo=c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Bitmap getFoto() {
        return foto;
    }
    public void setFoto(Bitmap foto){
        this.foto=foto;
    }
}
