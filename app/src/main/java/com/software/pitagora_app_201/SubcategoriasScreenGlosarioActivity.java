package com.software.pitagora_app_201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.software.pitagora_app_201.model.Glossary;
import com.software.pitagora_app_201.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class SubcategoriasScreenGlosarioActivity extends AppCompatActivity {

    String texto;
    TextView titulo;

    private List<Glossary> listGlossary = new ArrayList<Glossary>();
    ArrayAdapter<Glossary> arrayAdapterGlossary;

    ListView listV_glossary;
    String x,y;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Glossary glossarySelected;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategorias_screen_glosario);

        titulo = findViewById(R.id.select);
        Persona usuarioLog = (Persona) getIntent().getSerializableExtra("usuario");
        recogerExtras();

        Button btn_salir = (Button) findViewById(R.id.btn_back_tarjeta);

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (SubcategoriasScreenGlosarioActivity.this, MainDespuesDeLoginActivity.class);
                intent.putExtra("usuario", usuarioLog);
                startActivity(intent);
            }
        });

        listV_glossary = findViewById(R.id.lv_datosGlosario);
        inicializarFirebase();
        listarDatos();
        listV_glossary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                glossarySelected = (Glossary) parent.getItemAtPosition(position);
                x=glossarySelected.getContenido();
                y=glossarySelected.getCategoria();
                Intent glossarySelected = new Intent(SubcategoriasScreenGlosarioActivity.this,GlosarioScreenCategoriaActivity.class);
                glossarySelected.putExtra("usuario", usuarioLog);
                glossarySelected.putExtra("dato1",x);
                glossarySelected.putExtra("dato2",y);
                startActivity(glossarySelected);
            }
        });

    }

    public void recogerExtras() {
        //Aquí recogemos y tratamos los parámetros
        Bundle extras= getIntent().getExtras();
        String s= extras.getString("id");
        texto=s;
        titulo.setText(texto);
    }


    private void listarDatos() {
        databaseReference.child(texto).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listGlossary.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Glossary p = objSnaptshot.getValue(Glossary.class);
                    listGlossary.add(p);

                    arrayAdapterGlossary = new ArrayAdapter<Glossary>(SubcategoriasScreenGlosarioActivity.this, android.R.layout.simple_list_item_1, listGlossary);
                    listV_glossary.setAdapter(arrayAdapterGlossary);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }
}