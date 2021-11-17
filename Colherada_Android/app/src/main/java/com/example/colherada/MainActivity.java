package com.example.colherada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button btnHome, btnCalorias, btnReceitas;
    CardView cardView1, cardView2, cardView3;
    TextView tvImg1, tvImg2, tvImg3;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Context context;
    Usuarios user;
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
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        this.context = MainActivity.this; //***********************************************************
        Intent intent = getIntent();
        user = (Usuarios) intent.getSerializableExtra("userSerializable");
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        });
        btnReceitas.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainReceitas.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String str = item.toString();
        if(user != null)
        {
            if(str.equals("Receitas Salvas")){
                Intent intent = new Intent(MainActivity.this,MainMinhasReceitas.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
            else if(str.equals("Criar Receita")){
                Intent intent = new Intent(MainActivity.this,CriarReceita.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
            else if(str.equals("Meus dados")){
                Intent intent = new Intent(MainActivity.this,MainMeusDados.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        }
        if(str.equals("Entrar")){
            Intent intent = new Intent(MainActivity.this,MainLogin.class);
            intent.putExtra("userSerializable", user);
            context.startActivity(intent);
        }
        else if(str.equals("Meus dados") || str.equals("Criar Receita") ||str.equals("Receitas Salvas")){
            if(user == null)
                Toast.makeText(MainActivity.this, "[ERROR] Faça seu login para acessar essas telas!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {


    }
}