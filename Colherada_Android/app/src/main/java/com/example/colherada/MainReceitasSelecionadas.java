package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainReceitasSelecionadas extends AppCompatActivity {
    Button btnCalorias, btnHome, btnReceitas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_receitas_selecionadas);
        btnCalorias = (Button) findViewById(R.id.btnCalorias2);
        btnHome = (Button) findViewById(R.id.btnHome2);
        btnReceitas = (Button) findViewById(R.id.btnReceitas2);

        btnCalorias.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainReceitasSelecionadas.this,MainCalorias.class);

                startActivity(intent);

            }
        });

        btnHome.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainReceitasSelecionadas.this,MainActivity.class);

                startActivity(intent);

            }
        });

        btnReceitas.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainReceitasSelecionadas.this,MainReceitas.class);

                startActivity(intent);

            }
        });
    }
}