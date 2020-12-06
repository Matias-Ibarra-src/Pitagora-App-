package com.software.pitagora_app_201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import java.util.Random;

public class PreguntarandomActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public List<Pregunta> listPreguntas = new ArrayList<Pregunta>();
    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preguntarandom);
        texto = (TextView) findViewById(R.id.textView2_random);
        inicializarFirebase();
        preguntas();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void preguntas(){
        Random random = new Random();
        Button btn_salir = (Button) findViewById(R.id.btn_quit_pregunta_random);
        Button btn_glosario = (Button) findViewById(R.id.btn_pregunta_glosario_random);
        Button btn_siguiente = (Button) findViewById(R.id.btn_next_random);
        final Persona usuarioLog = (Persona) getIntent().getSerializableExtra("usuario");

        databaseReference.child("preguntas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPreguntas.clear();
                int length_lista = 0;
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    Pregunta pta = objSnaptshot.getValue(Pregunta.class);
                    listPreguntas.add(pta);
                    length_lista = length_lista + 1;
                }
                int rando = random.nextInt(length_lista);
                Pregunta pregunta = (Pregunta) listPreguntas.get(rando);
                texto.setText(pregunta.getPregunta());

                Button btn_1 = (Button) findViewById(R.id.btn_respuesta_random_1);
                Button btn_2 = (Button) findViewById(R.id.btn_respuesta_random_2);
                Button btn_3 = (Button) findViewById(R.id.btn_respuesta_random_3);
                Button btn_4 = (Button) findViewById(R.id.btn_respuesta_random_4);

                btn_1.setText(pregunta.getRespuesta1());
                btn_2.setText(pregunta.getRespuesta2());
                btn_3.setText(pregunta.getRespuesta3());
                btn_4.setText(pregunta.getRespuesta4());

                final Integer respuestaC = pregunta.getRespuestaC();

                btn_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (respuestaC == 1) {

                            btn_1.setClickable(false);
                            btn_2.setClickable(false);
                            btn_3.setClickable(false);
                            btn_4.setClickable(false);
                            btn_1.setTextColor(Color.parseColor("#FFFFFF"));
                            btn_1.setBackgroundColor(Color.parseColor("#1AFF00"));

                        } else {
                            btn_1.setClickable(false);
                            btn_2.setClickable(false);
                            btn_3.setClickable(false);
                            btn_4.setClickable(false);
                            btn_1.setTextColor(Color.parseColor("#FFFFFF"));
                            btn_1.setBackgroundColor(Color.parseColor("#FF0000"));
                        }
                    }
                });


                btn_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (respuestaC == 2) {
                            btn_1.setClickable(false);
                            btn_2.setClickable(false);
                            btn_3.setClickable(false);
                            btn_4.setClickable(false);
                            btn_2.setTextColor(Color.parseColor("#FFFFFF"));
                            btn_2.setBackgroundColor(Color.parseColor("#1AFF00"));
                        } else {
                            btn_1.setClickable(false);
                            btn_2.setClickable(false);
                            btn_3.setClickable(false);
                            btn_4.setClickable(false);
                            btn_2.setTextColor(Color.parseColor("#FFFFFF"));
                            btn_2.setBackgroundColor(Color.parseColor("#FF0000"));

                        }
                    }
                });

                btn_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (respuestaC == 3) {
                            btn_1.setClickable(false);
                            btn_2.setClickable(false);
                            btn_3.setClickable(false);
                            btn_4.setClickable(false);
                            btn_3.setTextColor(Color.parseColor("#FFFFFF"));
                            btn_3.setBackgroundColor(Color.parseColor("#1AFF00"));

                        } else {
                            btn_1.setClickable(false);
                            btn_2.setClickable(false);
                            btn_3.setClickable(false);
                            btn_4.setClickable(false);
                            btn_3.setTextColor(Color.parseColor("#FFFFFF"));
                            btn_3.setBackgroundColor(Color.parseColor("#FF0000"));

                        }
                    }
                });

                btn_4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (respuestaC == 4) {
                            btn_1.setClickable(false);
                            btn_2.setClickable(false);
                            btn_3.setClickable(false);
                            btn_4.setClickable(false);
                            btn_4.setTextColor(Color.parseColor("#FFFFFF"));
                            btn_4.setBackgroundColor(Color.parseColor("#1AFF00"));
                        } else {
                            btn_1.setClickable(false);
                            btn_2.setClickable(false);
                            btn_3.setClickable(false);
                            btn_4.setClickable(false);
                            btn_4.setTextColor(Color.parseColor("#FFFFFF"));
                            btn_4.setBackgroundColor(Color.parseColor("#FF0000"));
                        }
                    }
                });


                btn_salir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(v.getContext(), MainDespuesDeLoginActivity.class);
                        intent.putExtra("usuario", usuarioLog);
                        startActivityForResult(intent, 0);
                        finish();
                    }
                });

                btn_glosario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(v.getContext(), GlosarioInicioActivity.class);
                        intent.putExtra("usuario", usuarioLog);
                        startActivity(intent);
                        finish();
                    }
                });

                btn_siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(v.getContext(), PreguntarandomActivity.class);
                        intent.putExtra("usuario", usuarioLog);
                        startActivity(intent);
                        finish();

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}