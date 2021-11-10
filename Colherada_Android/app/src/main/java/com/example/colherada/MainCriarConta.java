package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainCriarConta extends AppCompatActivity {
    Button btnSalvar, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_criar_conta);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainCriarConta.this,MainLogin.class);

                startActivity(intent);
            }
        });
    }
}