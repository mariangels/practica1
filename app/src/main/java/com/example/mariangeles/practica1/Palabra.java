package com.example.mariangeles.practica1;


public class Palabra
{
    private String nombre, idioma, traduccion, significado;


    public Palabra() {
    }

    public Palabra(String nombre, String idioma, String traduccion, String significado) {
        this.nombre = nombre;
        this.idioma = idioma;
        this.traduccion = traduccion;
        this.significado = significado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getTraduccion() {
        return traduccion;
    }

    public String getSignificado() {
        return significado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setTraduccion(String traduccion) {
        this.traduccion = traduccion;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public String mostrar(){
        return  traduccion + "  Idioma: "+idioma;
    }
}
