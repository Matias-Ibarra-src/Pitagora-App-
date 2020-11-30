package com.software.pitagora_app_201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.software.pitagora_app_201.model.Persona;

public class GlosarioInicioActivity extends AppCompatActivity {

    String contenido1="Numeros";
    String contenido2="Geometria";
    String contenido3="Algebra";
    String contenido4="Probabilidad";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glosario_inicio);
        Persona usuarioLog = (Persona) getIntent().getSerializableExtra("usuario");
        Button btn_1 = (Button) findViewById(R.id.btn_numeros);
        Button btn_2 = (Button) findViewById(R.id.btn_geometria);
        Button btn_3 = (Button) findViewById(R.id.btn_algebra);
        Button btn_4 = (Button) findViewById(R.id.btn_Probabilidad);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), SubcategoriasScreenGlosarioActivity.class);
                intent.putExtra("id",contenido1);
                intent.putExtra("usuario", usuarioLog);
                startActivityForResult(intent, 0);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), SubcategoriasScreenGlosarioActivity.class);
                intent.putExtra("usuario", usuarioLog);
                intent.putExtra("id",contenido2);
                startActivityForResult(intent, 0);
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), SubcategoriasScreenGlosarioActivity.class);
                intent.putExtra("usuario", usuarioLog);
                intent.putExtra("id",contenido3);
                startActivityForResult(intent, 0);
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), SubcategoriasScreenGlosarioActivity.class);
                intent.putExtra("usuario", usuarioLog);
                intent.putExtra("id",contenido4);
                startActivityForResult(intent, 0);
            }
        });



    }

}