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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainCalorias extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button btnCalcular, btnHome, btnReceitas;
    EditText edtPeso, edtIdade;
    TextView txtRes;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calorias);
        btnCalcular = (Button)findViewById(R.id.btnCalcular);
        btnHome = (Button)findViewById(R.id.btnHome);
        btnReceitas = (Button)findViewById(R.id.btnReceitas);
        edtPeso = (EditText) findViewById(R.id.edtPeso);
        edtIdade = (EditText) findViewById(R.id.edtIdade);
        txtRes = (TextView) findViewById(R.id.txtResposta);
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
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                double peso = Integer.parseInt(edtPeso.getText().toString());
                double idade = Integer.parseInt(edtIdade.getText().toString());
                //Para emagrecer - multiplique 20 ou 25 pelo peso atual
                //Para manter o peso - multiplique 25 ou 30 pelo peso atual
                //Para engordar - multiplique 30 ou 35 pelo peso atual
                txtRes.setText("Você deve ingerir: \n " +
                        "De " + peso*20 + " até " + peso*25 + " para perder peso! \n" +
                        "De " + peso*25 + " até " + peso*30 + " para manter o peso! \n" +
                        "De " + peso*30 + " até " + peso*35 + " para ganhar peso!");

            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainCalorias.this,MainActivity.class);

                startActivity(intent);
            }
        });
        btnReceitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainCalorias.this,MainReceitas.class);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String str = item.toString();
        if(str.equals("Receitas Salvas")){
            Intent intent = new Intent(MainCalorias.this,MainMinhasReceitas.class);
            startActivity(intent);
            finish();
        }
        else if(str.equals("Criar Receita")){
            Intent intent = new Intent(MainCalorias.this,CriarReceita.class);
            startActivity(intent);
            finish();
        }
        else if(str.equals("Entrar")){
            Intent intent = new Intent(MainCalorias.this,MainLogin.class);
            startActivity(intent);
            finish();
        }
        return false;
    }
}