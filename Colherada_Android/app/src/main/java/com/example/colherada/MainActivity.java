package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnHome, btnCalorias, btnReceitas;
    CardView cardView1, cardView2, cardView3;
    TextView tvImg1, tvImg2, tvImg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnCalorias = (Button) findViewById(R.id.btnCalorias);
        btnReceitas = (Button) findViewById(R.id.btnReceitas);
        cardView1 = (CardView) findViewById(R.id.CardView1);
        cardView2 = (CardView) findViewById(R.id.CardView2);
        cardView3 = (CardView) findViewById(R.id.CardView3);
        tvImg1 = (TextView) findViewById(R.id.tvImg1);
        tvImg2 = (TextView) findViewById(R.id.tvImg2);
        tvImg3 = (TextView) findViewById(R.id.tvImg3);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvImg1.getText() == "Sobre a iniciativa")
                {
                    tvImg2.setText("Bem-vinda(o) ao mundo da comida!");
                    tvImg3.setText("Conheça todas os tipos de receita desponíveis");
                    tvImg1.setText("Sobre a iniciativa\n\nBem-vindo(a) á Colherada!  \"Uma colherada de sabor!\"" +
                            "Projeto criados pela Giovana Zamabanini, " +
                            "Isabela Clementino e Milena Shishito do Colégio Técnico de Campinas - COTUCA");
                } else {
                    tvImg1.setText("Sobre a iniciativa");
                }

            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvImg2.getText() == "Bem-vinda(o) ao mundo da comida!")
                {
                    tvImg1.setText("Sobre a iniciativa");
                    tvImg3.setText("Conheça todas os tipos de receita desponíveis");
                    tvImg2.setText("Bem-vinda(o) ao mundo da comida!\n\nEncontre receitas práticas pro seu dia-a-dia!!\n " +
                            "Na Colherada qualquer um pode compartilhara suas melhores receitas dígnas de uma boa fama!!! " +
                            "Além de ajudar muitos que estavam precisando daquela ajudinha na cozinha.");
                } else {
                    tvImg2.setText("Bem-vinda(o) ao mundo da comida!");
                }
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvImg3.getText() == "Conheça todas os tipos de receita desponíveis")
                {
                    tvImg1.setText("Sobre a iniciativa");
                    tvImg2.setText("Bem-vinda(o) ao mundo da comida!");
                    tvImg3.setText("Conheça todas os tipos de receita desponíveis\n\n- Carne/Frango/Peixe" +
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
                    tvImg3.setText("Conheça todas os tipos de receita desponíveis");
                }
            }
        });
        btnCalorias.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainCalorias.class);
                startActivity(intent);
                finish();
            }
        });
        btnReceitas.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainReceitas.class);
                startActivity(intent);
                finish();
            }
        });
        btnHome.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainReceitasSelecionadas.class);
                startActivity(intent);
                finish();
            }
        });
    }
}