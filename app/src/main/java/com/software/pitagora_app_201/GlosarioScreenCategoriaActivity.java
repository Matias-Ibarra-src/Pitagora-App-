package com.software.pitagora_app_201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.software.pitagora_app_201.model.Persona;

public class GlosarioScreenCategoriaActivity extends AppCompatActivity {
    TextView contenido;
    TextView titulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glosario_screen_categoria);

        titulo = (TextView) findViewById(R.id.titulo_selccionado);
        contenido = (TextView) findViewById(R.id.text_text);
        recogerExtras();
        Persona usuarioLog = (Persona) getIntent().getSerializableExtra("usuario");
        Button btn_salir = (Button) findViewById(R.id.btn_back_selector_glosario);

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (GlosarioScreenCategoriaActivity.this, MainDespuesDeLoginActivity.class);
                intent.putExtra("usuario", usuarioLog);
                startActivity(intent);
            }
        });
    }
    public void recogerExtras() {
        //Aquí recogemos y tratamos los parámetros
        Bundle extras= getIntent().getExtras();
        String datox= extras.getString("dato1");
        String datoy= extras.getString("dato2");
        titulo.setText(datoy);
        contenido.setText(datox);
    }


}