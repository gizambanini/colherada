package com.example.colherada;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainCriarReceita extends AppCompatActivity {
    Button btnSalvarNovaReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_criar_conta);
        btnSalvarNovaReceita = (Button)findViewById(R.id.btnSalvarNovaReceita);
    }
}