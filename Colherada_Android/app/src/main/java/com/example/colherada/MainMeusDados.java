package com.example.colherada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class MainMeusDados extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //CardView cdviewImgUser;
    ImageView imgUser;
    ImageButton btnMenu;
    Button btnReceitas, btnHome, btnCalorias, btnSalvar;
    EditText edtxtNome, edtxtEmail, edtxtSenha;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_meus_dados);
        btnCalorias = (Button) findViewById(R.id.btnCalorias);
        btnReceitas = (Button) findViewById(R.id.btnReceitas);
        btnHome = (Button) findViewById(R.id.btnHome);
        //btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        //cdviewImgUser = (CardView) findViewById(R.id.cdviewImgUser);
        imgUser = (ImageView) findViewById(R.id.imgUser);
        edtxtNome = (EditText) findViewById(R.id.edtxtNome);
        edtxtEmail = (EditText) findViewById(R.id.edtxtEmail);
        edtxtSenha = (EditText) findViewById(R.id.edtxtSenha);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
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

        Intent intent = getIntent();
        Usuarios user = (Usuarios) intent.getSerializableExtra("userSerializable");
        if(user != null){
            edtxtNome.setText(user.getNome());
            edtxtEmail.setText(user.getEmail());
            edtxtSenha.setText(user.getSenha());
            if((user.getFoto() != null) && (user.getFoto().length()>0)){
                Picasso.get().load(user.getFoto()).into(imgUser);
            }
            else
            {
                Picasso.get().load(R.drawable.user).into(imgUser);
            }
        }

        /*cdviewImgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Trocar de foto
            }
        });*/

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if(edtxtNome.getText().toString() == "" && edtxtEmail .getText().toString() == ""
                        && edtxtSenha.getText().toString() == ""){

                    Snackbar.make(view,"Preencha todos os campos!", Snackbar.LENGTH_SHORT).show();
                }else{
                    // Atualizar dados aqui ....
                }*/
            }
        });

        btnCalorias.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMeusDados.this,MainCalorias.class);
                startActivity(intent);
                finish();
            }
        });
        btnReceitas.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMeusDados.this,MainReceitas.class);
                startActivity(intent);
                finish();
            }
        });
        btnHome.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMeusDados.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String str = item.toString();
        if(str.equals("Receitas Salvas")){
            Intent intent = new Intent(MainMeusDados.this,MainMinhasReceitas.class);
            startActivity(intent);
            finish();
        }
        else if(str.equals("Criar Receita")){
            Intent intent = new Intent(MainMeusDados.this,CriarReceita.class);
            startActivity(intent);
            finish();
        }
        else if(str.equals("Entrar")){
            Intent intent = new Intent(MainMeusDados.this,MainLogin.class);
            startActivity(intent);
            finish();
        }
        return false;
    }
}