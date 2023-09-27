package com.example.p0mobil;

import java.util.ArrayList;

public class Respuesta {
    int id;
    String respuesta;
    boolean correcta;
    public Respuesta(int id, String respuesta, boolean correcta){
        this.id=id;
        this.respuesta=respuesta;
        this.correcta=correcta;
    }
    public int getId() {
        return id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public boolean isCorrecta() {
        return correcta;
    }
}
