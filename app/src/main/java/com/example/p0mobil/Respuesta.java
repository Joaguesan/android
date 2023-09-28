package com.example.p0mobil;

import java.util.ArrayList;

public class Respuesta {
    String id;
    String respuesta;
    boolean correcta;
    public Respuesta(String id, String respuesta, boolean correcta){
        this.id=id;
        this.respuesta=respuesta;
        this.correcta=correcta;
    }
    public String getId() {
        return id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public boolean isCorrecta() {
        return correcta;
    }
}
