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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class MainReceitasSelecionadas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button btnCalorias, btnHome, btnReceitas;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Context context;
    Usuarios user;
    Receitas  receita;
    TextView txtNomeReceita;
    TextView txtIngredientes;
    TextView txtPreparo;
    TextView txtDadosComentario;
    ImageView imgReceitaSelecionada;
    TextView txtCalorias;


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
        txtNomeReceita = findViewById(R.id.txtNomeReceita);
        txtIngredientes = findViewById(R.id.txtIngredientes);
        txtPreparo = findViewById(R.id.txtPreparo);
        txtDadosComentario = findViewById(R.id.txtDadosComentario);
        imgReceitaSelecionada = findViewById(R.id.imgReceitaSelecionada);
        txtCalorias = findViewById(R.id.txtCalorias);


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
        this.context = MainReceitasSelecionadas.this; //***********************************************************
        Intent intent = getIntent();
        user = (Usuarios) intent.getSerializableExtra("userSerializable");
        receita = (Receitas) intent.getSerializableExtra("receitaSerializable");
        if(receita != null){
            //Coloca novo dado
            txtNomeReceita.setText(receita .getNome());
            txtIngredientes.setText(receita.getIngredientes());
            txtPreparo.setText(receita.getModoPreparo());
            txtCalorias.setText(("Calorias: " + receita.getCalorias().toString() + " kcal"));

            if((receita .getFoto() != null) && (receita .getFoto().length()>0)){
                Picasso.get().load(receita.getFoto()).into(imgReceitaSelecionada);
            }
            else
            {
                Picasso.get().load(R.drawable.home_img1).into(imgReceitaSelecionada);
            }
        }

        btnCalorias.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainReceitasSelecionadas.this,MainCalorias.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        });

        btnHome.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainReceitasSelecionadas.this,MainActivity.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);

            }
        });

        btnReceitas.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainReceitasSelecionadas.this,MainReceitas.class);
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
                Intent intent = new Intent(MainReceitasSelecionadas.this,MainMinhasReceitas.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
            else if(str.equals("Criar Receita")){
                Intent intent = new Intent(MainReceitasSelecionadas.this,CriarReceita.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
            else if(str.equals("Meus dados")){
                Intent intent = new Intent(MainReceitasSelecionadas.this,MainMeusDados.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        }
        if(str.equals("Entrar")){
            Intent intent = new Intent(MainReceitasSelecionadas.this,MainLogin.class);
            intent.putExtra("userSerializable", user);
            context.startActivity(intent);
        }
        else if(str.equals("Meus dados") || str.equals("Criar Receita") ||str.equals("Receitas Salvas")){
            if(user == null)
                Toast.makeText(MainReceitasSelecionadas.this, "[ERROR] Faça seu login para acessar essas telas!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}