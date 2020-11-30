package com.software.pitagora_app_201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.software.pitagora_app_201.model.Persona;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Persona> listPerson = new ArrayList<Persona>();
    public List<String> listCorreos = new ArrayList<String>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText correoP,passwordP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correoP = findViewById(R.id.text_correo);
        passwordP = findViewById(R.id.text_contraseña);

        inicializarFirebase();

    }
    private void limpiarCajas() {
        correoP.setText("");
        passwordP.setText("");

    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }
    private void validacion() {

        String correo = correoP.getText().toString();
        String password = passwordP.getText().toString();

        if (correo.equals("")){
            correoP.setError("Required");
        }
        else{
            passwordP.setError("Required");
        }
    }

    private void bienvenido(){
        Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();
    }
    public void onClick(View v) {

        String correo = correoP.getText().toString();
        String password = passwordP.getText().toString();

        switch (v.getId()) {

            case R.id.btn_login: {


                if (correo.equals("") || password.equals("")) {
                    validacion();
                } else {

                    databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String correo1 = correoP.getText().toString();
                            String password1 = passwordP.getText().toString();
                            listPerson.clear();
                            listCorreos.clear();
                            //listPass.clear();
                            for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                                Persona p = objSnaptshot.getValue(Persona.class);
                                listPerson.add(p);
                                listCorreos.add(p.getCorreo());
                                //listPass.add(p.getNumero());

                            }
                            Log.d("entra sii",password1);
                            if (listCorreos.contains(correo1)) {
                                for(Persona str: listPerson){
                                    if(str.getCorreo().equals(correo1)){
                                        if(str.getPassword().equals(password1)){
                                            //Log.d("tagpass",str.getPassword());
                                            limpiarCajas();
                                            bienvenido();

                                            Intent intent = new Intent(MainActivity.this,MainDespuesDeLoginActivity.class);
                                            intent.putExtra("usuario", str);
                                            startActivity(intent);


                                        }
                                        else{
                                            passwordP.setError("Password Invalida");
                                        }
                                        //break;
                                    }
                                    else{
                                        //correoP.setError("Correo Invalido");
                                    }
                                }


                            } else {
                                correoP.setError("Correo Invalido");
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    //Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();
                    break;
                }
                break;
            }
            case R.id.btn_to_register: {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_olvide_contra: {
                Intent button2 = new Intent(MainActivity.this, RecuperarContraActivity.class);
                startActivity(button2);
                break;
            }
        }
    }
}