package com.software.pitagora_app_201.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Persona implements Serializable {
    private String Localid;
    private String Nombre_usuario;
    private String Nombre;
    private String Apellido;
    private String Numero;
    private String Correo;
    private String Password;

    //preguntas correctas por nivel
    private Integer correctas_en_geo;
    private Integer correctas_en_alg;
    private Integer correctas_en_pro;
    private Integer correctas_en_num;

    private Integer puntajeTotal;

    public Integer getCorrectas_en_geo() {
        return correctas_en_geo;
    }

    public void setCorrectas_en_geo(Integer correctas_en_geo) {
        this.correctas_en_geo = correctas_en_geo;
    }

    public Integer getCorrectas_en_alg() {
        return correctas_en_alg;
    }

    public void setCorrectas_en_alg(Integer correctas_en_alg) {
        this.correctas_en_alg = correctas_en_alg;
    }

    public Integer getCorrectas_en_pro() {
        return correctas_en_pro;
    }

    public void setCorrectas_en_pro(Integer correctas_en_pro) {
        this.correctas_en_pro = correctas_en_pro;
    }

    public Integer getCorrectas_en_num() {
        return correctas_en_num;
    }

    public void setCorrectas_en_num(Integer correctas_en_num) {
        this.correctas_en_num = correctas_en_num;
    }

    public Integer getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(Integer puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public Persona() {
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getLocalid() {
        return Localid;
    }

    public void setLocalid(String Localid) {
        this.Localid = Localid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return Nombre;
    }

    public String getNombre_usuario() {
        return Nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        Nombre_usuario = nombre_usuario;
    }
}
