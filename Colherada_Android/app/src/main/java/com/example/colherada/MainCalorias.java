package com.example.colherada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
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

    private Context context;
    Usuarios user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calorias);

        this.context = MainCalorias.this;

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

        // Dados do usuário logado
        Intent intent = getIntent();
        user = (Usuarios) intent.getSerializableExtra("userSerializable");

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

        //Calcula os intevalos de consumo de calorias;
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                double peso = Integer.parseInt(edtPeso.getText().toString());
                //Para emagrecer - multiplique 20 ou 25 pelo peso atual
                //Para manter o peso - multiplique 25 ou 30 pelo peso atual
                //Para engordar - multiplique 30 ou 35 pelo peso atual
                txtRes.setText("Você deve ingerir:\n" +
                        "De " + peso*20 + " kcal até " + peso*25 + " kcal para PERDER peso! \n\n" +
                        "De " + peso*25 + " kcal até " + peso*30 + " kcal para MANTER o peso! \n\n" +
                        "De " + peso*30 + " kcal até " + peso*35 + " kcal para GANHAR peso!");

            }
        });

        //Botões canto inferior tela (Home/Receitas)
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainCalorias.this,MainActivity.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        });
        btnReceitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainCalorias.this,MainReceitas.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String str = item.toString();
        //Menu lateral com as telas inacessíveis para usuários sem login
        if(user != null)
        {
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
            else if(str.equals("Meus dados")){
                Intent intent = new Intent(MainCalorias.this,MainMeusDados.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        }
        if(str.equals("Entrar")){
            Intent intent = new Intent(MainCalorias.this,MainLogin.class);
            intent.putExtra("userSerializable", user);
            context.startActivity(intent);
        }
        else if(str.equals("Meus dados") || str.equals("Criar Receita") ||str.equals("Receitas Salvas")){
            if(user == null)
                Toast.makeText(MainCalorias.this, "[ERROR] Faça seu login para acessar essas telas!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}