package com.example.p0mobil;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

public class Pregunta {
    int id;
    String pelicula;
    TextView titulo;
    ArrayList<Respuesta> respuestas;
    String ulrImagen;
    ImageView imagen;
    RadioGroup radiog;
    ArrayList<RadioButton> radiob;
    public Pregunta(int id, String pelicula, ArrayList<Respuesta> respuestas, String url, Context context){
        this.id=id;
        titulo.setText(pelicula);
        this.ulrImagen= url;
        radiog = new RadioGroup(context);
        for (int i = 0; i < respuestas.size();i++){
            this.radiob.get(i).setText(respuestas.get(i).getRespuesta());
            this.radiob.get(i).setId(respuestas.get(i).getId());
            this.radiob.get(i).setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            radiog.addView(this.radiob.get(i));
        }
    }

    public int getId() {
        return id;
    }

    public TextView getTitulo() {
        return titulo;
    }

    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }

    public String getUlrImagen() {
        return ulrImagen;
    }

    public RadioGroup getRadiog() {
        return radiog;
    }

    public void setRadiob(ArrayList<RadioButton> radiob) {
        this.radiob = radiob;
    }
}
