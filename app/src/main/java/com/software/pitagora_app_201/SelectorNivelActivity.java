package com.software.pitagora_app_201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.software.pitagora_app_201.model.Persona;
import com.software.pitagora_app_201.model.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class SelectorNivelActivity extends AppCompatActivity {


    String contenido1="Numeros";
    String contenido2="Geometria";
    String contenido3="Algebra";
    String contenido4="Probabilidad";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Integer Cant_alg;
    Integer Cant_pro;
    Integer Cant_num;
    Integer Cant_geo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector_nivel);
        inicializarFirebase();

        Button btn_geo = (Button) findViewById(R.id.btn_geometria_selector);
        Button btn_alg = (Button) findViewById(R.id.btn_algebra_selector);
        Button btn_pro = (Button) findViewById(R.id.btn_probabilidad_selector);
        Button btn_num = (Button) findViewById(R.id.btn_numeros_selector);

        Persona P = (Persona) getIntent().getSerializableExtra("usuario");

        if(P.getCorrectas_en_geo() == 20){
            btn_geo.setClickable(false);
            btn_geo.setTextColor(Color.parseColor("#FFFFFF"));
            btn_geo.setBackgroundColor(Color.parseColor("#1AFF00"));
        }else{
            btn_geo.setText("Geometria "+String.format("%d",P.getCorrectas_en_geo())+"/20");
        }

        if(P.getCorrectas_en_num() == 20){
            btn_num.setClickable(false);
            btn_num.setTextColor(Color.parseColor("#FFFFFF"));
            btn_num.setBackgroundColor(Color.parseColor("#1AFF00"));
        }else{
            btn_num.setText("Numeros "+String.format("%d",P.getCorrectas_en_num())+"/20");
        }

        if(P.getCorrectas_en_alg() == 20){
            btn_alg.setClickable(false);
            btn_alg.setTextColor(Color.parseColor("#FFFFFF"));
            btn_alg.setBackgroundColor(Color.parseColor("#1AFF00"));
        }else{
            btn_alg.setText("Algebra "+String.format("%d",P.getCorrectas_en_alg())+"/20");
        }

        if(P.getCorrectas_en_pro() == 20){
            btn_pro.setClickable(false);
            btn_pro.setTextColor(Color.parseColor("#FFFFFF"));
            btn_pro.setBackgroundColor(Color.parseColor("#1AFF00"));
        }else{
            btn_pro.setText("Probabilidad "+String.format("%d",P.getCorrectas_en_pro())+"/20");
        }
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_geometria_selector:{
                Persona usuario = (Persona) getIntent().getSerializableExtra("usuario");
                Intent intent = new Intent (v.getContext(), SelectorPreguntaActivity.class);
                intent.putExtra("usuario", usuario);
                intent.putExtra("Correctas",usuario.getCorrectas_en_geo());
                intent.putExtra("id",contenido2);
                startActivityForResult(intent, 0);
                break;
            }

            case R.id.btn_numeros_selector:{
                Persona usuario = (Persona) getIntent().getSerializableExtra("usuario");

                if(usuario.getCorrectas_en_geo() > 5){
                    Intent intent = new Intent (v.getContext(), SelectorPreguntaActivity.class);
                    intent.putExtra("usuario", usuario);
                    intent.putExtra("Correctas",usuario.getCorrectas_en_num());
                    intent.putExtra("id",contenido1);
                    startActivityForResult(intent, 0);
                }else{
                    Toast.makeText(this, "Nivel Bloqueado", Toast.LENGTH_LONG).show();
                }
                break;
            }

            case R.id.btn_algebra_selector: {
                Persona usuario = (Persona) getIntent().getSerializableExtra("usuario");

                if((usuario.getCorrectas_en_geo()+usuario.getCorrectas_en_num())>15){
                    Intent intent = new Intent (v.getContext(), SelectorPreguntaActivity.class);
                    intent.putExtra("usuario", usuario);
                    intent.putExtra("Correctas",usuario.getCorrectas_en_alg());
                    intent.putExtra("id",contenido3);
                    startActivityForResult(intent, 0);
                }else{
                    Toast.makeText(this, "Nivel Bloqueado", Toast.LENGTH_LONG).show();
                }
                break;
            }

            case R.id.btn_probabilidad_selector:{
                Persona usuario = (Persona) getIntent().getSerializableExtra("usuario");

                if((usuario.getCorrectas_en_geo()+usuario.getCorrectas_en_alg()+usuario.getCorrectas_en_num())>30){
                    Intent intent = new Intent (v.getContext(), SelectorPreguntaActivity.class);
                    intent.putExtra("Correctas",usuario.getCorrectas_en_pro());
                    intent.putExtra("usuario", usuario);
                    intent.putExtra("id",contenido4);
                    startActivityForResult(intent, 0);
                }else{
                    Toast.makeText(this, "Nivel Bloqueado", Toast.LENGTH_LONG).show();
                }
                break;
            }

            case R.id.btn_random_selector:{
                Persona usuario = (Persona) getIntent().getSerializableExtra("usuario");

                if((usuario.getCorrectas_en_geo()+usuario.getCorrectas_en_alg()+usuario.getCorrectas_en_num()+usuario.getCorrectas_en_pro())>50){
                    Intent intent = new Intent (v.getContext(), PreguntarandomActivity.class);
                    intent.putExtra("usuario", usuario);
                    startActivityForResult(intent, 0);
                }else{
                    Toast.makeText(this, "Random Bloqueado", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
}