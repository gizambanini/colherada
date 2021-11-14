package com.example.colherada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class MainReceitasSelecionadas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button btnCalorias, btnHome, btnReceitas;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_receitas_selecionadas);
        btnCalorias = (Button) findViewById(R.id.btnCalorias2);
        btnHome = (Button) findViewById(R.id.btnHome2);
        btnReceitas = (Button) findViewById(R.id.btnReceitas2);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String str = item.toString();
        if(str.equals("Receitas Salvas")){
            Intent intent = new Intent(MainReceitasSelecionadas.this,MainMinhasReceitas.class);
            startActivity(intent);
            finish();
        }
        else if(str.equals("Criar Receita")){
            Intent intent = new Intent(MainReceitasSelecionadas.this,CriarReceita.class);
            startActivity(intent);
            finish();
        }
        else if(str.equals("Entrar")){
            Intent intent = new Intent(MainReceitasSelecionadas.this,MainLogin.class);
            startActivity(intent);
            finish();
        }
        return false;
    }
}