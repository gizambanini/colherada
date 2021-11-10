package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMinhasReceitas extends AppCompatActivity {
    Button btnCalorias, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_minhas_receitas);
        btnCalorias = (Button) findViewById(R.id.btnCalorias);
        btnHome = (Button) findViewById(R.id.btnHome);

        btnCalorias.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainMinhasReceitas.this,MainCalorias.class);

                startActivity(intent);

            }
        });

        btnHome.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainMinhasReceitas.this,MainActivity.class);

                startActivity(intent);

            }
        });
    }
}