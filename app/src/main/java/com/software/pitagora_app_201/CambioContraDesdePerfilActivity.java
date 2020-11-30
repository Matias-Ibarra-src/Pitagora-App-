package com.software.pitagora_app_201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class CambioContraDesdePerfilActivity extends AppCompatActivity {
    public List<String> listCorreos = new ArrayList<String>();
    public List<String> listNumeros = new ArrayList<String>();
    EditText Contra_anterior, Contra_nueva, Contra_confirmacion;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambio_contra_desde_perfil);

        Contra_anterior =findViewById(R.id.text_contra_antigua);
        Contra_nueva=findViewById(R.id.text_contra_cambiar);
        Contra_confirmacion=findViewById(R.id.text_contra_cambiar_confirmar);

        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void limpiarCajas() {
        Contra_anterior.setText("");
        Contra_nueva.setText("");
        Contra_confirmacion.setText("");
    }

    private void validacion() {
        String Antigua = Contra_anterior.getText().toString();
        String Nueva = Contra_nueva.getText().toString();
        String Confirmacion = Contra_confirmacion.getText().toString();

        if (Antigua.equals("")) {
            Contra_anterior.setError("Required");
        } else if (Nueva.equals("")) {
            Contra_nueva.setError("Required");
        } else if (Confirmacion.equals("")) {
            Contra_confirmacion.setError("Required");
        }
    }

    public void onClick(View v) {
        String Antigua = Contra_anterior.getText().toString();
        String Nueva = Contra_nueva.getText().toString();
        String Confirmacion = Contra_confirmacion.getText().toString();

        Persona Usuario = (Persona) getIntent().getSerializableExtra("usuario");
        switch (v.getId()) {

            case R.id.btn_cambiar_contra_desde_perfil: {

                if(Antigua.equals("") || Nueva.equals("") || Confirmacion.equals("")){
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

                            for(String src : listCorreos ){
                                for (String rcs: listNumeros){
                                    if(rcs.equals(Usuario.getNumero())&&src.equals(Usuario.getCorreo())){
                                        if(Antigua.equals(Usuario.getPassword())){
                                            if(Nueva.equals(Confirmacion)) {
                                                Persona p = new Persona();
                                                p.setLocalid(Usuario.getLocalid());
                                                p.setCorrectas_en_alg(Usuario.getCorrectas_en_alg());
                                                p.setCorrectas_en_geo(Usuario.getCorrectas_en_geo());
                                                p.setCorrectas_en_num(Usuario.getCorrectas_en_num());
                                                p.setCorrectas_en_pro(Usuario.getCorrectas_en_pro());
                                                p.setCorreo(Usuario.getCorreo());
                                                p.setPuntajeTotal(Usuario.getPuntajeTotal());
                                                p.setNombre_usuario(Usuario.getNombre_usuario());
                                                p.setNombre(Usuario.getNombre());
                                                p.setApellido(Usuario.getApellido());
                                                p.setNumero(Usuario.getNumero());
                                                p.setPassword(Nueva);
                                                databaseReference.child("Persona").child(p.getLocalid()).setValue(p);
                                                cambiado();
                                                limpiarCajas();
                                                Intent intent = new Intent(CambioContraDesdePerfilActivity.this,MainActivity.class);
                                                startActivity(intent);
                                            }else{
                                                Contra_confirmacion.setError("Contraseñas no son iguales");
                                            }
                                        }else{
                                            Contra_anterior.setError("Contraseña Incorrecta");
                                        }
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