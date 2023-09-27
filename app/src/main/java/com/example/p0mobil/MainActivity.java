package com.example.p0mobil;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    private RadioGroup radioG;
    private ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = findViewById(R.id.boton);
        CharSequence text = "Faltan preguntas por responder";
        int duration = Toast.LENGTH_SHORT;

        try {
            JSONObject obj = new JSONObject(CargarJSON());
            JSONArray m_jArry = obj.getJSONArray("peliculas");
            ArrayList<Respuesta> respuestas1 = new ArrayList<>();

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                int id_pregunta = jo_inside.getInt("id");
                String pregunta = jo_inside.getString("pregunta");
                JSONArray respuestas = jo_inside.getJSONArray("respostes");
                for (int j = 0; j < respuestas.length();j++){
                    JSONObject jo_dentro = m_jArry.getJSONObject(j);
                    int id_respuesta = jo_dentro.getInt("id");
                    String respuesta = jo_dentro.getString("resposta");
                    boolean correcta = jo_dentro.getBoolean("correcta");
                    Respuesta respuesta1 = new Respuesta(id_respuesta,respuesta,correcta);
                    respuestas1.add(respuesta1);
                }
                String url = jo_inside.getString("imatge");
                Pregunta pregunta1 = new Pregunta(id_pregunta,pregunta,respuestas1,url, this);
                radioG = pregunta1.getRadiog();
                CrearPregunta(pregunta1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check1 = radioG.getCheckedRadioButtonId() == -1;
                if(!check1){
                    Toast toast = Toast.makeText(getApplicationContext(), "Todo contestado", duration);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Faltan preguntas por responder", duration);
                    toast.show();
                }
                Log.d("PREGUNTAS","--------------");
                Log.d("Pregunta 1",Respuesta(radioG));
            }
        });
    }
    public String Respuesta(RadioGroup rg){
        if(!(rg.getCheckedRadioButtonId()==-1)) {
            RadioButton rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
            return String.valueOf(rb.getText());
        }else{
            return "Sin respuesta";
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
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
        layout.addView(pr.getTitulo());
        layout.addView(pr.getRadiog());
    }



}