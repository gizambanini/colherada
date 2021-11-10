package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainCriarConta extends AppCompatActivity {
    Button btnSalvar, btnLogin;
    EditText edtEmail, edtSenha, edtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_criar_conta);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSenha = (EditText)findViewById(R.id.edtSenha);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainCriarConta.this,MainLogin.class);
                startActivity(intent);
            }
        });
    }
}