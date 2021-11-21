package com.example.colherada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.android.material.snackbar.Snackbar;

public class MainMeusDados extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imgUser;
    ImageButton btnMenu;
    Button btnReceitas, btnHome, btnCalorias, btnSalvar;
    EditText edtxtNome, edtxtEmail, edtxtSenha, edtxtUrlFoto;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Usuarios user;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_meus_dados);

        btnCalorias = (Button) findViewById(R.id.btnCalorias);
        btnReceitas = (Button) findViewById(R.id.btnReceitas);
        btnHome = (Button) findViewById(R.id.btnHome);

        imgUser = (ImageView) findViewById(R.id.imgUser);
        edtxtNome = (EditText) findViewById(R.id.edtxtNome);
        edtxtEmail = (EditText) findViewById(R.id.edtxtEmail);
        edtxtSenha = (EditText) findViewById(R.id.edtxtSenha);
        edtxtUrlFoto = (EditText) findViewById(R.id.edtxtUrlFoto);
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

        this.context = MainMeusDados.this;

        // Pega os dados do usuário logado
        Intent intent = getIntent();
        user = (Usuarios) intent.getSerializableExtra("userSerializable");

        if(user != null){
            //se tem um usuário logado

            //Limpa os editTexts
            edtxtNome.setText("");
            edtxtEmail.setText("");
            edtxtSenha.setText("");
            edtxtUrlFoto.setText("");

            //Coloca os dados do usuário
            edtxtNome.setText(user.getNome());
            edtxtEmail.setText(user.getEmail());
            edtxtSenha.setText(user.getSenha());
            edtxtUrlFoto.setText(user.getFoto());

            if((user.getFoto() != null) && (user.getFoto().length()>0)){
                //Se tiver uma imagem coloca no ImageView
                Picasso.get().load(user.getFoto()).into(imgUser);
            }
            else
            {
                // caso ainda não tenha uma imagem, coloca o ícone de user
                Picasso.get().load(R.drawable.user).into(imgUser);
            }
        }

        //Muda o ImageView quando o usuário digitar uma URL
        edtxtUrlFoto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(edtxtUrlFoto.getText().toString() != "")
                        Picasso.get().load(edtxtUrlFoto.getText().toString()).into(imgUser);
                }
            }
        });

        //Chama método para atualizar os dados do usuário
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarUsuario();
            }
        });

        //Botõs inferiores na tela
        btnCalorias.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMeusDados.this,MainCalorias.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        });
        btnReceitas.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMeusDados.this,MainReceitas.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        });
        btnHome.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMeusDados.this,MainActivity.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        });

    }

    //Método para atualizar os dados
    public void alterarUsuario(){
        // Guarda os dados em variáveis
        Integer strId = user.getId();
        String strNome = edtxtNome.getText().toString();
        String strEmail = edtxtEmail.getText().toString();
        String strSenha = edtxtSenha.getText().toString();
        String strImage = edtxtUrlFoto.getText().toString();

        //Cria um Usuário com os dados passados
        Usuarios usuario = new Usuarios(strId,strNome,strEmail,strSenha, strImage);

        //Passa o user criado acima para a API atualizar no BD
        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
        Call<Usuarios> call = service.atualizarUsuario(strId,usuario);
        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                if(response.isSuccessful()){
                    // Se deu tudo certo, abre a tela de meus dados novamente
                    // agora com os dados atualizados
                    Intent intent = new Intent(MainMeusDados.this,MainMeusDados.class);
                    intent.putExtra("userSerializable", usuario);
                    context.startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                Toast.makeText(MainMeusDados.this, "Atualização não efetuada: " +strId + "/" + strNome + "/" + strEmail + "/" + strSenha + "/" + strImage , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String str = item.toString();
        //Menu lateral com algumas telas inacessíveis para usuários sem login
        if(user != null)
        {
            if(str.equals("Receitas Salvas")){
                Intent intent = new Intent(MainMeusDados.this,MainMinhasReceitas.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
            else if(str.equals("Criar Receita")){
                Intent intent = new Intent(MainMeusDados.this,CriarReceita.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
            else if(str.equals("Meus dados")){
                Intent intent = new Intent(MainMeusDados.this,MainMeusDados.class);
                intent.putExtra("userSerializable", user);
                context.startActivity(intent);
            }
        }
        if(str.equals("Entrar")){
            Intent intent = new Intent(MainMeusDados.this,MainLogin.class);
            intent.putExtra("userSerializable", user);
            context.startActivity(intent);
        }
        else if(str.equals("Meus dados") || str.equals("Criar Receita") ||str.equals("Receitas Salvas")){
            if(user == null)
                Toast.makeText(MainMeusDados.this, "[ERROR] Faça seu login para acessar essas telas!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}