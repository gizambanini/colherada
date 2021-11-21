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
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMinhasReceitas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button btnCalorias, btnHome, btnReceitas;
    private GridView receitaGridView;
    private GridViewViewAdapter adapter;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Context context;
    Usuarios user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_minhas_receitas);

        btnCalorias = (Button) findViewById(R.id.btnCalorias);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnReceitas = (Button) findViewById(R.id.btnReceitas);

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

        this.context = MainMinhasReceitas.this;

        // Pega os dados do usuário logado
        Intent intent = getIntent();
        user = (Usuarios) intent.getSerializableExtra("userSerializable");

        // Botões inferiores da tela
        btnReceitas.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMinhasReceitas.this,MainReceitas.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);

            }
        });
        btnCalorias.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMinhasReceitas.this,MainCalorias.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);

            }
        });
        btnHome.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMinhasReceitas.this,MainActivity.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        });


        //Download JSON via Retrofit
        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
        // Leva o id do usuário para a API pegar as receitas salvas por ele
        Call<List<Receitas>> call = service.getReceitaSalvaByIdUser(user.getId());
        call.enqueue(new Callback<List<Receitas>>() {
            @Override
            public void onResponse(Call<List<Receitas>> call, Response<List<Receitas>> response) {
                if(response.isSuccessful()){
                    //chama o método de mostrar as receitas salvas pelo user
                    populateGridView(response.body());

                }else{
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(MainMinhasReceitas.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(MainMinhasReceitas.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Receitas>> call, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(MainMinhasReceitas.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainMinhasReceitas.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateGridView(List<Receitas> listaReceitaSalva){
        // Método para inserir as receitas na tela como cards
        receitaGridView = (GridView) findViewById(R.id.receitaGridView);
        adapter = new GridViewViewAdapter(this,listaReceitaSalva, user);
        receitaGridView.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String str = item.toString();
        // Menu lateral com algumas telas inacessíveis para usuários sem login
        if(user != null)
        {
            if(str.equals("Receitas Salvas")){
                Intent intent = new Intent(MainMinhasReceitas.this,MainMinhasReceitas.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
            else if(str.equals("Criar Receita")){
                Intent intent = new Intent(MainMinhasReceitas.this,CriarReceita.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
            else if(str.equals("Meus dados")){
                Intent intent = new Intent(MainMinhasReceitas.this,MainMeusDados.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        }
        if(str.equals("Entrar")){
            Intent intent = new Intent(MainMinhasReceitas.this,MainLogin.class);
            intent.putExtra("userSerializable", user);
            context.startActivity(intent);
        }
        else if(str.equals("Meus dados") || str.equals("Criar Receita") ||str.equals("Receitas Salvas")){
            if(user == null)
                Toast.makeText(MainMinhasReceitas.this, "[ERROR] Faça seu login para acessar essas telas!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}