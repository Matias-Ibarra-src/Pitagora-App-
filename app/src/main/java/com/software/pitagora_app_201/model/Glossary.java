package com.software.pitagora_app_201.model;

public class Glossary {
    private String Categoria;
    private String Contenido;
    public Glossary(){
    }
    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        Contenido = contenido;
    }


    public String getCategoria() {
        return Categoria;
    }
    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }
    @Override
    public String toString() {
        return Categoria;
    }
}
