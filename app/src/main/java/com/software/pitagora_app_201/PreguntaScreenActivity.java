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

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PreguntaScreenActivity extends AppCompatActivity {
    private int id_preguntas[] = {
            R.id.btn_respuesta_1,R.id.btn_respuesta_2,R.id.btn_respuesta_3,R.id.btn_respuesta_4
    };


    String categoria;
    String correcta;

    TextView texto;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public List<Pregunta> listPreguntas = new ArrayList<Pregunta>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregunta_screen);
        texto = (TextView) findViewById(R.id.textView2);
        recogerExtras();
        inicializarFirebase();
        llenarListaPreguntas();
    }

    public void recogerExtras() {
        //Aquí recogemos y tratamos los parámetros
        Bundle extras= getIntent().getExtras();
        String datox= extras.getString("cate");
        categoria=datox;
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


        public void llenarListaPreguntas() {
            Random random = new Random();
            final Persona usuarioLog = (Persona) getIntent().getSerializableExtra("usuario");
            final String categoria1 = categoria;
            Button btn_salir = (Button) findViewById(R.id.btn_quit_pregunta);
            Button btn_glosario = (Button) findViewById(R.id.btn_pregunta_glosario);
            Button btn_siguiente = (Button) findViewById(R.id.btn_next);
            Boolean flag = false;

            if(categoria1.equals("Geometria")){
                if(usuarioLog.getCorrectas_en_geo() == 20){
                    Intent intent = new Intent (PreguntaScreenActivity.this , NivelfinalizadoActivity.class);
                    intent.putExtra("usuario",usuarioLog);
                    startActivityForResult(intent, 0);
                    finish();
                }
                flag = true;
            }
            if(categoria1.equals("Numeros")){
                if(usuarioLog.getCorrectas_en_num() == 20){
                    Intent intent = new Intent (PreguntaScreenActivity.this , NivelfinalizadoActivity.class);
                    intent.putExtra("usuario",usuarioLog);
                    startActivityForResult(intent, 0);
                    finish();
                }
                flag = true;
            }
            if(categoria1.equals("Algebra")){
                if(usuarioLog.getCorrectas_en_alg() == 20){
                    Intent intent = new Intent (PreguntaScreenActivity.this , NivelfinalizadoActivity.class);
                    intent.putExtra("usuario",usuarioLog);
                    startActivityForResult(intent, 0);
                    finish();
                }
                flag = true;
            }
            if(categoria1.equals("Probabilidad")){
                if(usuarioLog.getCorrectas_en_pro() == 20){
                    Intent intent = new Intent (PreguntaScreenActivity.this , NivelfinalizadoActivity.class);
                    intent.putExtra("usuario",usuarioLog);
                    startActivityForResult(intent, 0);
                    finish();
                }
                flag = true;
            }

            Log.d("123412314",flag.toString());
            if(flag) {
                databaseReference.child("preguntas").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        listPreguntas.clear();
                        int length_lista = 0;
                        for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                            Pregunta pta = objSnaptshot.getValue(Pregunta.class);
                            if (categoria.equals(pta.getCategoria())) {
                                listPreguntas.add(pta);
                                length_lista = length_lista + 1;
                            }

                        }
                        int rando = random.nextInt(length_lista);
                        Pregunta pregunta = (Pregunta) listPreguntas.get(rando);
                        texto.setText(pregunta.getPregunta());

                        Button btn_1 = (Button) findViewById(id_preguntas[0]);
                        Button btn_2 = (Button) findViewById(id_preguntas[1]);
                        Button btn_3 = (Button) findViewById(id_preguntas[2]);
                        Button btn_4 = (Button) findViewById(id_preguntas[3]);

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
                                    if (categoria1.equals("Geometria")) {
                                        usuarioLog.setCorrectas_en_geo(usuarioLog.getCorrectas_en_geo() + 1);
                                    } else if (categoria1.equals("Numeros")) {
                                        usuarioLog.setCorrectas_en_num(usuarioLog.getCorrectas_en_num() + 1);
                                    } else if (categoria1.equals("Algebra")) {
                                        usuarioLog.setCorrectas_en_alg(usuarioLog.getCorrectas_en_alg() + 1);
                                    } else if (categoria1.equals("Probabilidad")) {
                                        usuarioLog.setCorrectas_en_pro(usuarioLog.getCorrectas_en_pro() + 1);
                                    }

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

                                    if (categoria1.equals("Geometria")) {
                                        usuarioLog.setCorrectas_en_geo(usuarioLog.getCorrectas_en_geo() + 1);
                                    } else if (categoria1.equals("Numeros")) {
                                        usuarioLog.setCorrectas_en_num(usuarioLog.getCorrectas_en_num() + 1);
                                    } else if (categoria1.equals("Algebra")) {
                                        usuarioLog.setCorrectas_en_alg(usuarioLog.getCorrectas_en_alg() + 1);
                                    } else if (categoria1.equals("Probabilidad")) {
                                        usuarioLog.setCorrectas_en_pro(usuarioLog.getCorrectas_en_pro() + 1);
                                    }
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

                                    if (categoria1.equals("Geometria")) {
                                        usuarioLog.setCorrectas_en_geo(usuarioLog.getCorrectas_en_geo() + 1);
                                    } else if (categoria1.equals("Numeros")) {
                                        usuarioLog.setCorrectas_en_num(usuarioLog.getCorrectas_en_num() + 1);
                                    } else if (categoria1.equals("Algebra")) {
                                        usuarioLog.setCorrectas_en_alg(usuarioLog.getCorrectas_en_alg() + 1);
                                    } else if (categoria1.equals("Probabilidad")) {
                                        usuarioLog.setCorrectas_en_pro(usuarioLog.getCorrectas_en_pro() + 1);
                                    }

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
                                    if (categoria1.equals("Geometria")) {
                                        usuarioLog.setCorrectas_en_geo(usuarioLog.getCorrectas_en_geo() + 1);
                                    } else if (categoria1.equals("Numeros")) {
                                        usuarioLog.setCorrectas_en_num(usuarioLog.getCorrectas_en_num() + 1);
                                    } else if (categoria1.equals("Algebra")) {
                                        usuarioLog.setCorrectas_en_alg(usuarioLog.getCorrectas_en_alg() + 1);
                                    } else if (categoria1.equals("Probabilidad")) {
                                        usuarioLog.setCorrectas_en_pro(usuarioLog.getCorrectas_en_pro() + 1);
                                    }
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
                                actualizarPreguntas(usuarioLog);

                                Intent intent = new Intent(v.getContext(), MainDespuesDeLoginActivity.class);
                                intent.putExtra("usuario", usuarioLog);
                                startActivityForResult(intent, 0);
                                finish();
                            }
                        });

                        btn_glosario.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                actualizarPreguntas(usuarioLog);

                                Intent intent = new Intent(v.getContext(), GlosarioInicioActivity.class);
                                intent.putExtra("usuario", usuarioLog);
                                intent.putExtra("id", categoria);
                                startActivity(intent);
                                finish();
                            }
                        });

                        btn_siguiente.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                actualizarPreguntas(usuarioLog);

                                Intent intent = new Intent(v.getContext(), PreguntaScreenActivity.class);
                                intent.putExtra("usuario", usuarioLog);
                                intent.putExtra("cate", categoria);
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

    @Override
    public void onBackPressed() {
        Log.d("MainActivity","onBackPressed()");
        finish();
    }

    public void actualizarPreguntas(Persona usuarioLog){
        Persona p = new Persona();
        p.setLocalid(usuarioLog.getLocalid());
        p.setCorrectas_en_alg(usuarioLog.getCorrectas_en_alg());
        p.setCorrectas_en_geo(usuarioLog.getCorrectas_en_geo());
        p.setCorrectas_en_num(usuarioLog.getCorrectas_en_num());
        p.setCorrectas_en_pro(usuarioLog.getCorrectas_en_pro());
        p.setCorreo(usuarioLog.getCorreo());
        p.setPuntajeTotal(usuarioLog.getPuntajeTotal());
        p.setNombre_usuario(usuarioLog.getNombre_usuario());
        p.setNombre(usuarioLog.getNombre());
        p.setApellido(usuarioLog.getApellido());
        p.setNumero(usuarioLog.getNumero());
        p.setPassword(usuarioLog.getPassword());
        databaseReference.child("Persona").child(p.getLocalid()).setValue(p);
    }
}