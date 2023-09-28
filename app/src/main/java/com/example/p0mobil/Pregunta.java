package com.example.p0mobil;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

public class Pregunta {
    String id;
    String pelicula;
    TextView titulo;
    ArrayList<Respuesta> respuestas;
    String ulrImagen;
    ImageView imagen;
    RadioGroup radiog;
    int correcta;
    ArrayList<RadioButton> radiob = new ArrayList<>();
    public Pregunta(String id, String pelicula, ArrayList<Respuesta> respuestas, String url, Context context){
        this.id=id;
        this.titulo = new TextView(context);
        this.titulo.setLayoutParams((new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)));
        this.titulo.setText(pelicula);
        this.titulo.setTextSize(40);
        this.titulo.setBackgroundColor(Color.rgb(68,206,255));
        this.ulrImagen= url;
        radiog = new RadioGroup(context);
        for (int i = 0; i < respuestas.size();i++){
            this.radiob.add(new RadioButton(context));
            this.radiob.get(i).setText(respuestas.get(i).getRespuesta());
            this.radiob.get(i).setId(Integer.parseInt(respuestas.get(i).getId()));
            if(respuestas.get(i).isCorrecta()){
                this.correcta = Integer.parseInt(respuestas.get(i).getId());
            }
            this.radiob.get(i).setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            radiog.addView(this.radiob.get(i));
        }
    }

    public String getId() {
        return id;
    }

    public int getCorrecta() {
        return correcta;
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
