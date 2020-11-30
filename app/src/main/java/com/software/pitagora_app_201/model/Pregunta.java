package com.software.pitagora_app_201.model;

import java.io.Serializable;

public class Pregunta implements Serializable {

    String LocalId;


    public Integer getRespuestaC() {
        return RespuestaC;
    }

    public void setRespuestaC(Integer respuestaC) {
        RespuestaC = respuestaC;
    }

    Integer RespuestaC;
    String Respuesta1;
    String Respuesta2;
    String Respuesta3;
    String Respuesta4;
    Integer Puntaje;

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    String Categoria;


    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    String pregunta;

    public String getLocalId() {
        return LocalId;
    }

    public void setLocalId(String localId) {
        LocalId = localId;
    }





    public String getRespuesta1() {
        return Respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        Respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        return Respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        Respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        return Respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        Respuesta3 = respuesta3;
    }

    public String getRespuesta4() {
        return Respuesta4;
    }

    public void setRespuesta4(String respuesta4) {
        Respuesta4 = respuesta4;
    }

    public Integer getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        Puntaje = puntaje;
    }

    @Override
    public String toString() {
        return LocalId;
    }

}
