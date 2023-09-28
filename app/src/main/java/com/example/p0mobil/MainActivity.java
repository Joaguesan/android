package com.example.p0mobil;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private Button boton;
    private TextView titulo;
    private ArrayList<RadioGroup> radioG = new ArrayList<>();
    private ArrayList<Pregunta> preguntas = new ArrayList<>();
    private ImageView imagen;
    private int cont=0;
    private LinearLayout layout;

    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.linear);
        boton = new Button(this);
        CharSequence text = "Faltan preguntas por responder";
        int duration = Toast.LENGTH_SHORT;
        TextView textView = findViewById(R.id.titulo);
        CharSequence textoTitulo = textView.getText();
        try {
            JSONObject obj = new JSONObject(CargarJSON());
            JSONArray m_jArry = obj.getJSONArray("peliculas");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String id_pregunta = jo_inside.getString("id");
                String pregunta = jo_inside.getString("pregunta");
                JSONArray respuestas = jo_inside.getJSONArray("respostes");
                ArrayList<Respuesta> respuestas1 = new ArrayList<>();
                for (int j = 0; j < respuestas.length();j++){
                    JSONObject jo_dentro = respuestas.getJSONObject(j);
                    String id_respuesta = jo_dentro.getString("id");
                    String respuesta = jo_dentro.getString("resposta");
                    boolean correcta = jo_dentro.getBoolean("correcta");
                    Respuesta respuesta1 = new Respuesta(id_respuesta,respuesta,correcta);
                    respuestas1.add(respuesta1);
                }
                String url = jo_inside.getString("imatge");
                Pregunta pregunta1 = new Pregunta(id_pregunta,pregunta,respuestas1,url, this);
                radioG.add(pregunta1.getRadiog());
                CrearPregunta(pregunta1);
                preguntas.add(pregunta1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        boton.setText("Terminar");
        boton.setLayoutParams((new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)));
        layout.addView(boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check1 = false;
                for (int i=0; i < radioG.size();i++){
                    if(radioG.get(i).getCheckedRadioButtonId()==-1){
                        check1=true;
                    }else{
                        check1=false;
                    }
                }
                String texto = "Todo contestado\n" ;
                RespuestasCorrectas();
                if (cont==preguntas.size()){
                    texto+="TODAS CORRECTAS";
                }else{
                    texto+= cont+" respuestas correctas";
                }
                Toast toast;
                if(!check1){
                    toast = Toast.makeText(getApplicationContext(), texto, duration);
                }else{
                    toast = Toast.makeText(getApplicationContext(), "Faltan preguntas por responder", duration);
                }
                toast.show();
            }
        });
    }

    public void RespuestasCorrectas(){
        cont=0;
        for (int i=0; i < preguntas.size();i++) {
            if (preguntas.get(i).getCorrecta() == radioG.get(i).getCheckedRadioButtonId()) {
                cont++;
            }
        }
    }
    public String CargarJSON() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("preguntas.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public void CrearPregunta(Pregunta pr){
        layout.addView(pr.getTitulo());
        layout.addView(pr.getRadiog());
    }



}