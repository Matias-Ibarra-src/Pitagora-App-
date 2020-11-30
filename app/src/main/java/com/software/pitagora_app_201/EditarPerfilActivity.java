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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EditarPerfilActivity extends AppCompatActivity {
    public List<String> listCorreos = new ArrayList<String>();
    public List<String> listNumeros = new ArrayList<String>();
    EditText nombre, apellido, numero, nombre_usuario;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_perfil);

        nombre = findViewById(R.id.text_editar_Nombre);
        apellido = findViewById(R.id.text_editar_apellido);
        numero = findViewById(R.id.text_editar_Numero);
        nombre_usuario = findViewById(R.id.text_editar_Nombre_usuario);

        inicializarFirebase();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void limpiarCajas() {
        nombre.setText("");
        numero.setText("");
        nombre_usuario.setText("");
        apellido.setText("");
    }

    private void validacion() {
        String nombre_persona = nombre.getText().toString();
        String apellido_persona = apellido.getText().toString();
        String numero_persona = numero.getText().toString();
        String usuario = nombre_usuario.getText().toString();

        if (nombre_persona.equals("")) {
            nombre.setError("Required");
        } else if (apellido_persona.equals("")) {
            apellido.setError("Required");
        } else if (numero_persona.equals("")) {
            numero.setError("Required");
        }else if (usuario.equals("")){
            nombre_usuario.setError("Required");
        }
    }

    public void onClick(View v) {
        String nombre_persona = nombre.getText().toString();
        String numero_persona = numero.getText().toString();
        String apellido_persona = apellido.getText().toString();
        String Nombre_usuario = nombre_usuario.getText().toString();
        Persona Usuario = (Persona) getIntent().getSerializableExtra("usuario");
        switch (v.getId()) {

            case R.id.btn_cambiar_perfil: {
                if (nombre_persona.equals("") || Nombre_usuario.equals("") || numero_persona.equals("") || apellido_persona.equals("")) {
                    validacion();
                }else{
                    databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            listCorreos.clear();
                            listNumeros.clear();

                            for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                                Persona p = objSnaptshot.getValue(Persona.class);
                                listCorreos.add(p.getCorreo());
                                listNumeros.add(p.getNumero());
                            }

                            for(String src : listCorreos ) {
                                for (String rcs : listNumeros) {
                                    if (rcs.equals(Usuario.getNumero()) && src.equals(Usuario.getCorreo())) {
                                        Persona p = new Persona();
                                        Log.d("tagw", "si entra");

                                        p.setLocalid(Usuario.getLocalid());
                                        p.setCorrectas_en_alg(Usuario.getCorrectas_en_alg());
                                        p.setCorrectas_en_geo(Usuario.getCorrectas_en_geo());
                                        p.setCorrectas_en_num(Usuario.getCorrectas_en_num());
                                        p.setCorrectas_en_pro(Usuario.getCorrectas_en_pro());
                                        p.setCorreo(Usuario.getCorreo());
                                        p.setPuntajeTotal(Usuario.getPuntajeTotal());
                                        p.setNombre_usuario(Nombre_usuario);
                                        p.setNombre(nombre_persona);
                                        p.setApellido(apellido_persona);
                                        p.setNumero(numero_persona);
                                        p.setPassword(Usuario.getPassword());
                                        databaseReference.child("Persona").child(p.getLocalid()).setValue(p);
                                        cambiado();
                                        limpiarCajas();
                                        Intent intent = new Intent(EditarPerfilActivity.this, PerfilActivity.class);
                                        intent.putExtra("usuario", p);
                                        startActivity(intent);
                                    }
                                }
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
}