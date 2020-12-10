package com.software.pitagora_app_201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.software.pitagora_app_201.model.Persona;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        Button btn_editar = (Button) findViewById(R.id.btn_Editar_Perfil);
        Button btn_cambiar_contra = (Button) findViewById(R.id.btn_Cambiar_Contra_Perfil);

        TextView Nombre_usuario = (TextView) findViewById(R.id.text_nombre_Usuario_perfil);
        TextView Nombre = (TextView) findViewById(R.id.Text_nombre_Perfil);
        TextView Apellido = (TextView) findViewById(R.id.Text_apellido_Perfil);
        TextView Numero = (TextView) findViewById(R.id.Text_numero_perfil);
        TextView Correo = (TextView) findViewById(R.id.Text_Correo_Perfil);
        TextView Puntaje = (TextView) findViewById(R.id.Text_Puntaje_Perfil);

        Persona P = (Persona) getIntent().getSerializableExtra("usuario");

        Nombre_usuario.setText(P.getNombre_usuario());
        Nombre.setText("Nombre: "+P.getNombre());
        Apellido.setText("Apellido: "+P.getApellido());
        Numero.setText(" "+P.getNumero());
        Numero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.phone, 0, 0, 0);
        Correo.setText(" "+P.getCorreo());
        Correo.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mail, 0, 0, 0);
        Puntaje.setText(String.format("Puntos: %d",P.getPuntajeTotal()));

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), EditarPerfilActivity.class);
                intent.putExtra("usuario", P);
                startActivityForResult(intent, 0);
            }
        });

        btn_cambiar_contra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), CambioContraDesdePerfilActivity.class);
                intent.putExtra("usuario", P);
                startActivityForResult(intent, 0);
            }
        });
    }
}