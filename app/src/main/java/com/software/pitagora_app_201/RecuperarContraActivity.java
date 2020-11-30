package com.software.pitagora_app_201;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.software.pitagora_app_201.model.Persona;


public class RecuperarContraActivity extends Activity implements OnClickListener{
    private List<Persona> listPerson = new ArrayList<Persona>();
    public List<String> listCorreos = new ArrayList<String>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String codigo="gato123";
    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText reciep;
    String rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperar_contra);

        context = this;

        Button login = (Button) findViewById(R.id.btn_recuperar);
        reciep = (EditText) findViewById(R.id.text_contraseña);

        inicializarFirebase();
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        rec = reciep.getText().toString();
        switch (v.getId()) {

            case R.id.btn_recuperar: {


                if (reciep.equals("")) {
                    validacion();
                } else {

                    databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String correo1 = reciep.getText().toString();
                            listCorreos.clear();
                            listPerson.clear();
                            //listPass.clear();
                            for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                                Persona p = objSnaptshot.getValue(Persona.class);
                                listPerson.add(p);
                                listCorreos.add(p.getCorreo());

                            }

                            if (listCorreos.contains(correo1)) {
                                for(Persona str: listPerson){
                                    if(str.getCorreo().equals(correo1)){
                                        limpiarCajas();


                                        Properties props = new Properties();
                                        props.put("mail.smtp.host", "smtp.gmail.com");
                                        props.put("mail.smtp.socketFactory.port", "465");
                                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                                        props.put("mail.smtp.auth", "true");
                                        props.put("mail.smtp.port", "465");

                                        session = Session.getDefaultInstance(props, new Authenticator() {
                                            protected PasswordAuthentication getPasswordAuthentication() {
                                                return new PasswordAuthentication("pitagorapp2020@gmail.com", "app12345678");
                                            }
                                        });

                                        pdialog = ProgressDialog.show(context, "", "Sending Mail...", true);

                                        RetreiveFeedTask task = new RetreiveFeedTask();
                                        task.execute();
                                        Intent intent = new Intent(RecuperarContraActivity.this, CambioContraActivity.class);
                                        intent.putExtra("cod", codigo);
                                        intent.putExtra("correo", correo1);
                                        startActivity(intent);

                                            /*Intent intent = new Intent(MainActivity.this,MainDespuesDeLoginActivity.class);
                                            intent.putExtra("usuario", str);
                                            startActivity(intent);*/

                                    }

                                }


                            }
                            else {
                                reciep.setError("Correo Invalido");
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

        }
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    private void limpiarCajas() {
        reciep.setText("");
    }


    private void validacion() {

        String correo = reciep.getText().toString();


        if (correo.equals("")){
            reciep.setError("Required");
        }

    }




















    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("pitagorapp2020@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject("Codigo de cambio de contraseña");
                message.setContent("Su codigo es: gato123", "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            reciep.setText("");
            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }
}

