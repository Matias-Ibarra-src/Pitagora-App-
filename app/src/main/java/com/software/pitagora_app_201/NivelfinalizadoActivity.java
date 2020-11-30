package com.software.pitagora_app_201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.software.pitagora_app_201.model.Persona;

public class NivelfinalizadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nivelfinalizado);

        Button btn_siguiente = (Button) findViewById(R.id.btn_siguiente_nivel);
        final Persona usuarioLog = (Persona) getIntent().getSerializableExtra("usuario");

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (NivelfinalizadoActivity.this, SelectorNivelActivity.class);
                intent.putExtra("usuario",usuarioLog);
                startActivityForResult(intent, 0);
                finish();
            }
        });
    }
}