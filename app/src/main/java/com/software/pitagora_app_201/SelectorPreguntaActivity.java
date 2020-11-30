package com.software.pitagora_app_201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class SelectorPreguntaActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView titulo;
    public List<Button> lista_Botonera = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector_pregunta);
        Integer total_correctas = (Integer) getIntent().getSerializableExtra("Correctas");

        setiarBotones();
        Integer Control = 0;
        for (Button Btn : lista_Botonera) {
            if (Control == total_correctas) {
            }else if(Control < total_correctas){
                Btn.setEnabled(false);
                Btn.setTextColor(Color.parseColor("#FFFFFF"));
                Btn.setBackgroundColor(Color.parseColor("#1AFF00"));
            }else{
                Btn.setEnabled(false);
            }
            Control=Control+1;
        }

        titulo = (TextView) findViewById(R.id.titulo_selector_preguntas);
        inicializarFirebase();
        recogerExtras();

    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void setiarBotones(){
        Button btn1 = (Button) findViewById(R.id.btn_pregunta_1_1);
        btn1.setEnabled(true);
        lista_Botonera.add(btn1);
        Button btn2 = (Button) findViewById(R.id.btn_pregunta_1_2);
        btn2.setEnabled(true);
        lista_Botonera.add(btn2);
        Button btn3 = (Button) findViewById(R.id.btn_pregunta_1_3);
        btn3.setEnabled(true);
        lista_Botonera.add(btn3);
        Button btn4 = (Button) findViewById(R.id.btn_pregunta_1_4);
        btn4.setEnabled(true);
        lista_Botonera.add(btn4);

        Button btn5 = (Button) findViewById(R.id.btn_pregunta_2_1);
        btn5.setEnabled(true);
        lista_Botonera.add(btn5);
        Button btn6 = (Button) findViewById(R.id.btn_pregunta_2_2);
        btn6.setEnabled(true);
        lista_Botonera.add(btn6);
        Button btn7 = (Button) findViewById(R.id.btn_pregunta_2_3);
        btn7.setEnabled(true);
        lista_Botonera.add(btn7);
        Button btn8 = (Button) findViewById(R.id.btn_pregunta_2_4);
        btn8.setEnabled(true);
        lista_Botonera.add(btn8);

        Button btn9 = (Button) findViewById(R.id.btn_pregunta_3_1);
        btn9.setEnabled(true);
        lista_Botonera.add(btn9);
        Button btn10 = (Button) findViewById(R.id.btn_pregunta_3_2);
        btn10.setEnabled(true);
        lista_Botonera.add(btn10);
        Button btn11 = (Button) findViewById(R.id.btn_pregunta_3_3);
        btn11.setEnabled(true);
        lista_Botonera.add(btn11);
        Button btn12 = (Button) findViewById(R.id.btn_pregunta_3_4);
        btn12.setEnabled(true);
        lista_Botonera.add(btn12);

        Button btn13 = (Button) findViewById(R.id.btn_pregunta_4_1);
        btn13.setEnabled(true);
        lista_Botonera.add(btn13);
        Button btn14 = (Button) findViewById(R.id.btn_pregunta_4_2);
        btn14.setEnabled(true);
        lista_Botonera.add(btn14);
        Button btn15 = (Button) findViewById(R.id.btn_pregunta_4_3);
        btn15.setEnabled(true);
        lista_Botonera.add(btn15);
        Button btn16 = (Button) findViewById(R.id.btn_pregunta_4_4);
        btn16.setEnabled(true);
        lista_Botonera.add(btn16);

        Button btn17 = (Button) findViewById(R.id.btn_pregunta_5_1);
        btn17.setEnabled(true);
        lista_Botonera.add(btn17);
        Button btn18 = (Button) findViewById(R.id.btn_pregunta_5_2);
        btn18.setEnabled(true);
        lista_Botonera.add(btn18);
        Button btn19 = (Button) findViewById(R.id.btn_pregunta_5_3);
        btn19.setEnabled(true);
        lista_Botonera.add(btn19);
        Button btn20 = (Button) findViewById(R.id.btn_pregunta_5_4);
        btn20.setEnabled(true);
        lista_Botonera.add(btn20);
    }

    public void recogerExtras() {
        //Aquí recogemos y tratamos los parámetros
        Bundle extras= getIntent().getExtras();
        String datox= extras.getString("id");
        titulo.setText(datox);
    }

    public void onClick(View v) {
        Persona P = (Persona) getIntent().getSerializableExtra("usuario");
        String categoria = titulo.getText().toString();
        switch (v.getId()) {

            case R.id.btn_pregunta_1_1: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_1_2: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_1_3: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_1_4: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }


            case R.id.btn_pregunta_2_1: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_2_2: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_2_3: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_2_4: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }


            case R.id.btn_pregunta_3_1: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_3_2: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_3_3: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_3_4: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }


            case R.id.btn_pregunta_4_1: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_4_2: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_4_3: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_4_4: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }


            case R.id.btn_pregunta_5_1: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_5_2: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_5_3: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
            case R.id.btn_pregunta_5_4: {
                Intent intent = new Intent(SelectorPreguntaActivity.this, PreguntaScreenActivity.class);
                intent.putExtra("usuario",P);
                intent.putExtra("cate",categoria);
                startActivity(intent);
            }
        }
    }
}