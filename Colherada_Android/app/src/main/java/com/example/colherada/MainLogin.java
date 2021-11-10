package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainLogin extends AppCompatActivity {
    Button btnEntrar, btnCriarConta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btnCriarConta = (Button)findViewById(R.id.btnCriarConta);

        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainLogin.this,MainCriarConta.class);

                startActivity(intent);
            }
        });
    }
}