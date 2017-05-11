package com.example.julio.loginbd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import registro.RegisterUserClasss;

public class Registro extends AppCompatActivity implements View.OnClickListener{

    private EditText txtnombre,txtusuarioR,txtemailR,txtpasswordR;
    private Button btnregistro,btncancelarR;

    private static final String REGISTER_URL="http://192.168.1.10/rutasUtec/registro.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtnombre = (EditText)findViewById(R.id.txtnombre);
        txtusuarioR = (EditText)findViewById(R.id.txtusuarioR);
        txtemailR = (EditText)findViewById(R.id.txtemailR);
        txtpasswordR=(EditText)findViewById(R.id.txtpassR);

        btnregistro = (Button)findViewById(R.id.btnregistro);
        btncancelarR=(Button)findViewById(R.id.btncancelarR);

        btnregistro.setOnClickListener(this);
        btncancelarR.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnregistro:
                registrarusuario();
                break;
            case R.id.btncancelarR:
                Intent intent =  new  Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void registrarusuario(){
        String name = txtnombre.getText().toString().trim().toLowerCase();
        String username =txtusuarioR.getText().toString().trim().toLowerCase();
        String password = txtpasswordR.getText().toString().trim().toLowerCase();
        String email = txtemailR.getText().toString().trim().toLowerCase();

        registro(name,username,password,email);
    }
    private void registro(String name,String username,String password,String email){
        class RegisterUser extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            RegisterUserClasss registerUserClasss = new RegisterUserClasss();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading=ProgressDialog.show(Registro.this,"espera porfavor",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String>data = new HashMap<String, String>();
                data.put("name",params[0]);
                data.put("username",params[1]);
                data.put("password",params[2]);
                data.put("email",params[3]);
                String result = registerUserClasss.sedPostRequest(REGISTER_URL,data);
                return result;
            }
        }
        RegisterUser ru  = new RegisterUser();
        ru.execute(name,username,password,email);
    }
}
