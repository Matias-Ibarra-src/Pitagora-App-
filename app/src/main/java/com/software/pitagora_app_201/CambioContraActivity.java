package com.software.pitagora_app_201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.software.pitagora_app_201.model.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class   CambioContraActivity extends AppCompatActivity {
    public List<String> listCorreos = new ArrayList<String>();
    private List<Persona> listPerson = new ArrayList<Persona>();
    EditText codigo, Contra_nueva, Contra_confirmacion;
    String correo,codEnviado;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambio_contra);

        Contra_nueva=findViewById(R.id.text_contraseña_recuperar_confirmar);
        Contra_confirmacion=findViewById(R.id.text_contraseña_recuperar);
        codigo = findViewById(R.id.text_codigo);
        recogerExtras();
        inicializarFirebase();


        /*
        cambio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent button1 = new Intent(CambioContraActivity.this,MainActivity.class);
                startActivity(button1);
                Persona usuarioLog = (Persona) getIntent().getSerializableExtra("usuario");

            }
        });*/
    }



    public void onClick(View v) {
        String code = codigo.getText().toString();
        String Nueva = Contra_nueva.getText().toString();
        String Confirmacion = Contra_confirmacion.getText().toString();

        switch (v.getId()) {

            case R.id.btn_cambiar_contra: {

                if(code.equals("") || Nueva.equals("") || Confirmacion.equals("")){
                    validacion();
                }else{
                    databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String code1 = codigo.getText().toString();
                            String Nueva1 = Contra_nueva.getText().toString();
                            String Confirmacion1 = Contra_confirmacion.getText().toString();

                            listCorreos.clear();
                            listPerson.clear();
                            for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                                Persona p = objSnaptshot.getValue(Persona.class);
                                listCorreos.add(p.getCorreo());
                                listPerson.add(p);

                            }
                            if(codEnviado.equals(code1)) {
                                if (Nueva1.equals(Confirmacion1)) {
                                    for (Persona str : listPerson) {
                                        if (str.getCorreo().equals(correo)) {

                                            Persona p = new Persona();
                                            p.setLocalid(str.getLocalid());
                                            p.setCorrectas_en_alg(str.getCorrectas_en_alg());
                                            p.setCorrectas_en_geo(str.getCorrectas_en_geo());
                                            p.setCorrectas_en_num(str.getCorrectas_en_num());
                                            p.setCorrectas_en_pro(str.getCorrectas_en_pro());
                                            p.setCorreo(str.getCorreo());
                                            p.setPuntajeTotal(str.getPuntajeTotal());
                                            p.setNombre_usuario(str.getNombre_usuario());
                                            p.setNombre(str.getNombre());
                                            p.setApellido(str.getApellido());
                                            p.setNumero(str.getNumero());
                                            p.setPassword(Nueva1);
                                            databaseReference.child("Persona").child(p.getLocalid()).setValue(p);
                                            cambiado();
                                            limpiarCajas();
                                            Intent intent = new Intent(CambioContraActivity.this,MainActivity.class);
                                            startActivity(intent);




                                                /*Intent intent = new Intent(MainActivity.this,MainDespuesDeLoginActivity.class);
                                                intent.putExtra("usuario", str);
                                                startActivity(intent);*/

                                        }

                                    }


                                } else {
                                    Contra_confirmacion.setError("Contraseñas son distintas");
                                }
                            }
                            else{
                                codigo.setError("Codigo invalido");
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    break;
                }
                break;
            }
        }
    }

    private void cambiado() {
        Toast.makeText(this, "Datos Cambiados", Toast.LENGTH_LONG).show();
    }












    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void limpiarCajas() {
        codigo.setText("");
        Contra_nueva.setText("");
        Contra_confirmacion.setText("");
    }

    private void validacion() {
        String code = codigo.getText().toString();
        String Nueva = Contra_nueva.getText().toString();
        String Confirmacion = Contra_confirmacion.getText().toString();

        if (code.equals("")) {
            codigo.setError("Required");
        } else if (Nueva.equals("")) {
            Contra_nueva.setError("Required");
        } else if (Confirmacion.equals("")) {
            Contra_confirmacion.setError("Required");
        }
    }
    public void recogerExtras() {
        //Aquí recogemos y tratamos los parámetros
        Bundle extras= getIntent().getExtras();
        String datox= extras.getString("correo");
        String datoy= extras.getString("cod");
        correo=datox;
        codEnviado=datoy;

    }
}