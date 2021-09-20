package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btnCalorias;
    //LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btnCalorias = (Button) findViewById(R.id.btnCalorias);
//        layout = (LinearLayout) findViewById(R.id.layout);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn1.getText() == "")
                {
                    btn2.setLayoutParams(new LinearLayout.LayoutParams(810, 450));
                    btn3.setLayoutParams(new LinearLayout.LayoutParams(810, 400));
                    btn2.setText("");
                    btn3.setText("");

                    btn1.setLayoutParams(new LinearLayout.LayoutParams(810, 780));
                    btn1.setText("Bem-vindo(a) á Colherada! \n \"Uma colherada de sabor!\"" +
                            "Projeto criados pela Giovana Zamabanini, " +
                            "Isabela Clementino e Milena Shishito");
                } else {
                    btn1.setText("");
                    btn1.setLayoutParams(new LinearLayout.LayoutParams(810, 430));
                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn2.getText() == "")
                {
                    btn1.setLayoutParams(new LinearLayout.LayoutParams(810, 430));
                    btn3.setLayoutParams(new LinearLayout.LayoutParams(810, 400));
                    btn1.setText("");
                    btn3.setText("");

                    btn2.setLayoutParams(new LinearLayout.LayoutParams(810, 680));
                    btn2.setText("Encontre receitas práticas pro seu dia-a-dia!!");
                } else {
                    btn2.setText("");
                    btn2.setLayoutParams(new LinearLayout.LayoutParams(810, 450));
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn3.getText() == "")
                {
                    btn1.setLayoutParams(new LinearLayout.LayoutParams(810, 430));
                    btn2.setLayoutParams(new LinearLayout.LayoutParams(810, 450));
                    btn1.setText("");
                    btn2.setText("");

                    btn3.setLayoutParams(new LinearLayout.LayoutParams(810, 880));
                    btn3.setText("- Carne/Frango/Peixe" +
                            "- Massas" +
                            "- Lanches" +
                            "- Sobremesas" +
                            "- Estrangeira (Italiana, Japonesa, Francesa ...)" +
                            "- Básicos (arroz,feijão, macarrão ao sugo …)" +
                            "- Saudável" +
                            "- Vegano/Vegetariano" +
                            "- Sem glúten/Sem lactose/Sem açúcar" +
                            "- Outros (Sucos/vitaminas/...)");
                } else {
                    btn3.setText("");
                    btn3.setLayoutParams(new LinearLayout.LayoutParams(810, 450));
                }
            }
        });
        btnCalorias.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,MainCalorias.class);

                startActivity(intent);

            }
        });
    }
}