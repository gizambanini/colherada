package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainReceitas extends AppCompatActivity {
    Button btnHome, btnCalorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_receitas);
        btnHome = (Button)findViewById(R.id.btnHome3);
        btnCalorias = (Button)findViewById(R.id.btnCalorias3);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainReceitas.this,MainActivity.class);

                startActivity(intent);
            }
        });
        btnCalorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainReceitas.this,MainCalorias.class);

                startActivity(intent);
            }
        });
    }
}