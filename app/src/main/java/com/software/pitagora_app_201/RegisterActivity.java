package com.software.pitagora_app_201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    public List<String> listCorreos = new ArrayList<String>();
    public List<String> listNumeros = new ArrayList<String>();
    EditText nomP, appP, correoP, passwordP, numP, nombre_usuario, passwordC;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        nomP = findViewById(R.id.text_Nombre);
        appP = findViewById(R.id.text_Apellido);
        numP = findViewById(R.id.text_Numero);
        nombre_usuario = findViewById(R.id.text_Nombre_de_usuario);
        correoP = findViewById(R.id.text_correo_registro);
        passwordP = findViewById(R.id.text_contraseña_registro);
        passwordC = findViewById(R.id.text_Confirmar_contraseña_registro);
        inicializarFirebase();


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void limpiarCajas() {
        nomP.setText("");
        correoP.setText("");
        passwordP.setText("");
        numP.setText("");
        nombre_usuario.setText("");
        appP.setText("");
        passwordC.setText("");
    }

    private void validacion() {
        String nombre = nomP.getText().toString();
        String correo = correoP.getText().toString();
        String password = passwordP.getText().toString();
        String passwordConf = passwordC.getText().toString();
        String app = appP.getText().toString();
        String numero = numP.getText().toString();
        String usuario = nombre_usuario.getText().toString();

        if (nombre.equals("")) {
            nomP.setError("Escribe un Nombre");
        } else if (app.equals("")) {
            appP.setError("Escribe un Apellido");
        } else if (correo.equals("")) {
            correoP.setError("Escribe un Correo");
        } else if (password.equals("")) {
            passwordP.setError("Escribe tu contraseña");
        } else if (numero.equals("")) {
            numP.setError("Escribe tu Numero");
        } else if(usuario.equals("")){
            nombre_usuario.setError("Escribe un nick name");
        } else if(passwordConf.equals("")){
            passwordC.setError("Escribe tu contraseña");
        }
    }

    public void onClick(View v) {
        String nombre = nomP.getText().toString();
        String correo = correoP.getText().toString();
        String password = passwordP.getText().toString();
        String passwordConf = passwordC.getText().toString();
        String numero = numP.getText().toString();
        String app = appP.getText().toString();
        String Nombre_usuario = nombre_usuario.getText().toString();

        switch (v.getId()) {

            case R.id.btn_register: {

                if (nombre.equals("") || correo.equals("") || password.equals("") || Nombre_usuario.equals("") || numero.equals("") || app.equals("") || passwordConf.equals("")) {
                    validacion();
                } else {

                    databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String nombre1 = nomP.getText().toString();
                            String correo1 = correoP.getText().toString();
                            String password1 = passwordP.getText().toString();
                            String numero1 = numP.getText().toString();
                            String app1 = appP.getText().toString();
                            boolean flag=true;
                            listCorreos.clear();
                            listNumeros.clear();
                            for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                                Persona p = objSnaptshot.getValue(Persona.class);
                                listCorreos.add(p.getCorreo());
                                listNumeros.add(p.getNumero());

                            }

                            /*for (String src : listCorreos){ Log.d("correo es:", src);}
                            for (String src : listNumeros){ Log.d("numero es:", src);}*/

                            if (listNumeros.contains(numero1) || listCorreos.contains(correo1)) {
                                if (listNumeros.contains(numero1)) {
                                    numP.setError("Numero existente");
                                } else {
                                    correoP.setError("Correo existente");
                                }


                            } else {
                                if (numP.getText().toString().equals("") || correoP.getText().toString().equals("")) {
                                    //comprueba que los datos aun existan en las cajas
                                } else {

                                    /*if(!validarNumero(numP.getText().toString())){
                                        numP.setError("Numero no válido");
                                        flag=false;
                                    }*/

                                    if (!validarEmail(correo1)){
                                        correoP.setError("Email no válido");
                                        flag=false;
                                    }
                                    if (!password1.equals(passwordConf)) {
                                        passwordC.setError("Contraseñas no son iguales");
                                        flag=false;
                                    }
                                    if(password1.length()<12){
                                        passwordP.setError("Contrasela muy corta");
                                        flag=false;
                                    }

                                    if(flag){
                                        Persona p = new Persona();
                                        List<String> listPreguntas = new ArrayList<String>();
                                        String nombr = nomP.getText().toString();
                                        String corre = correoP.getText().toString();
                                        String passwor = passwordP.getText().toString();
                                        String numer = numP.getText().toString();
                                        String ap = appP.getText().toString();
                                        String Nombre_usuario = nombre_usuario.getText().toString();

                                        p.setLocalid(UUID.randomUUID().toString());
                                        p.setNombre(nombr);
                                        p.setNumero(numer);
                                        p.setCorreo(corre);
                                        p.setPassword(passwor);
                                        p.setApellido(ap);
                                        p.setNombre_usuario(Nombre_usuario);
                                        p.setCorrectas_en_alg(0);
                                        p.setCorrectas_en_geo(0);
                                        p.setCorrectas_en_pro(0);
                                        p.setCorrectas_en_num(0);
                                        p.setPuntajeTotal(0);
                                        //p.setListpreguntasContestadas(listPreguntas);
                                        databaseReference.child("Persona").child(p.getLocalid()).setValue(p);
                                        agregar();
                                        limpiarCajas();
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    break;
                }
                break;
            }

        }

    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    /*private boolean validarNumero(String Numero){
        if(Numero.indexOf("9") == 1){
            Log.d("numero", "igual 9");
            if(Numero.length() == 9){
                Log.d("cantidad", "si tiene 9");
                return true;
            }
        }
        return false;
    }*/

    private void agregar() {
        Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();
    }

}