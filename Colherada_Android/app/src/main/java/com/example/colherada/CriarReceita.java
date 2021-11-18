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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriarReceita extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Context context;
    Usuarios user;
    Button btnHome, btnCalorias, btnReceitas, btnSalvarNovaReceita;
    EditText edtNome, edtIngrediente, edtPreparo, edtUrlFt, edtCaloria;
    ImageView imgReceita;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_receita);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnCalorias = (Button) findViewById(R.id.btnCalorias);
        btnReceitas = (Button) findViewById(R.id.btnReceitas);
        btnSalvarNovaReceita = (Button) findViewById(R.id.btnSalvarNovaReceita);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtIngrediente = (EditText) findViewById(R.id.edtIngrediente);
        edtPreparo = (EditText) findViewById(R.id.edtPreparo);
        edtUrlFt = (EditText) findViewById(R.id.edtUrlFt);
        edtCaloria = (EditText) findViewById(R.id.edtCaloria);
        imgReceita = (ImageView) findViewById(R.id.imgReceita);


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
        this.context = CriarReceita.this; //***********************************************************
        Intent intent = getIntent();
        user = (Usuarios) intent.getSerializableExtra("userSerializable");
        btnCalorias.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CriarReceita.this,MainCalorias.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        });
        btnReceitas.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CriarReceita.this,MainReceitas.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        });
        btnHome.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CriarReceita.this,MainActivity.class);
                startActivity(intent);
            }
        });
        edtUrlFt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Picasso.get().load(edtUrlFt.getText().toString()).into(imgReceita);
                }
            }
        });
        btnSalvarNovaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if(edtNome.getText().toString().equals("")&&edtIngrediente.getText().toString().equals("")&&
                        edtPreparo.getText().toString().equals("")&&
                        edtUrlFt.getText().toString().equals("")&&
                        edtCaloria.getText().toString().equals("")) {
                    Toast.makeText(CriarReceita.this, "Preencha todas os campos!", Toast.LENGTH_LONG).show();
                }else{
                    String strNome = edtNome.getText().toString();
                    String strIng = edtIngrediente.getText().toString();
                    String strPrep = edtPreparo.getText().toString();
                    String strFoto = edtUrlFt.getText().toString();
                    Integer intCaloria = Integer.parseInt(edtCaloria.getText().toString());

                    Receitas receita = new Receitas(null,strNome,strIng,strPrep,intCaloria,strFoto,user.getId(),0);
                    Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
                    Call<Receitas> call = service.incluirReceita(receita);
                    call.enqueue(new Callback<Receitas>() {
                        @Override
                        public void onResponse(Call<Receitas> call, Response<Receitas> response) {
                            if(response.isSuccessful()){
                                Intent intent = new Intent(CriarReceita.this,MainReceitas.class);
                                intent.putExtra("userSerializable", receita);
                                context.startActivity(intent);
                            }
                        }
                        @Override
                        public void onFailure(Call<Receitas> call, Throwable t) {
                            Toast.makeText(CriarReceita.this, "ERRO criação não efetuada: ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String str = item.toString();
        if(user != null)
        {
            if(str.equals("Receitas Salvas")){
                Intent intent = new Intent(CriarReceita.this,MainMinhasReceitas.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
            else if(str.equals("Criar Receita")){
                Intent intent = new Intent(CriarReceita.this,CriarReceita.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
            else if(str.equals("Meus dados")){
                Intent intent = new Intent(CriarReceita.this,MainMeusDados.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        }
        if(str.equals("Entrar")){
            Intent intent = new Intent(CriarReceita.this,MainLogin.class);
            intent.putExtra("userSerializable", user);
            context.startActivity(intent);
        }
        else if(str.equals("Meus dados") || str.equals("Criar Receita") ||str.equals("Receitas Salvas")){
            if(user == null)
                Toast.makeText(CriarReceita.this, "[ERROR] Faça seu login para acessar essas telas!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}