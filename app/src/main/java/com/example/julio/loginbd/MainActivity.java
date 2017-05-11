package com.example.julio.loginbd;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import registro.RegisterUserClasss;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtusuario,txtpass;
    private Button btnlogin;
    private Button registrarbtn;

    public static final String USER_NAME="USER_NAME";
    public static final String PASSWORD="PASSWORD";
    private static final String LOGIN_URL = "http://192.168.1.10/rutasUtec/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtusuario = (EditText)findViewById(R.id.txtusuario);
        txtpass=(EditText)findViewById(R.id.txtpass);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        registrarbtn=(Button)findViewById(R.id.registrarbtn);
        btnlogin.setOnClickListener(this);
        registrarbtn.setOnClickListener(this);
    }
    private void login(){
        String username=txtusuario.getText().toString().trim();
        String password= txtpass.getText().toString().trim();
        //aqui va el metodo de login
        userLogin(username,password);
    }
    private void userLogin(final String username,final String password){
        class UserLoginClass extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading=ProgressDialog.show(MainActivity.this,"espere porfavor",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equalsIgnoreCase("success")){
                    Intent intent = new Intent(getApplicationContext(),Menu.class);
                    intent.putExtra(USER_NAME,username);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String>data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);
                RegisterUserClasss registerUserClasss = new RegisterUserClasss();
                String result = registerUserClasss.sedPostRequest(LOGIN_URL,data);

                return result;
            }
        }
        UserLoginClass userLoginClass = new UserLoginClass();
        userLoginClass.execute(username,password);
    }
    @Override
    public void onClick(View v) {
        if(v ==btnlogin){
            login();
        }
        if (v==registrarbtn){
            Intent intent = new Intent(getApplicationContext(),Registro.class);
            startActivity(intent);
        }
    }
}
