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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainReceitasSelecionadas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button btnCalorias, btnHome, btnReceitas, btnComentar;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Context context;
    Usuarios user;
    Receitas  receita;

    TextView txtNomeReceita;
    TextView txtIngredientes;
    TextView txtPreparo;
    ImageView imgReceitaSelecionada, imgSalvo;
    TextView txtCalorias;
    EditText edtComentarioFeito;

    private GridViewAdapterComentario adapter;
    GridView comentarioGridView;
    // vai auxiliar na hora de salvar as receitas
    Integer salvou = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_receitas_selecionadas);

        //Botões inferiores
        btnCalorias = (Button) findViewById(R.id.btnCalorias2);
        btnHome = (Button) findViewById(R.id.btnHome2);
        btnReceitas = (Button) findViewById(R.id.btnReceitas2);

        // Botão para criar um comentário
        btnComentar = (Button) findViewById(R.id.btnComentar);

        edtComentarioFeito = (EditText)findViewById(R.id.edtComentarioFeito);
        txtNomeReceita = findViewById(R.id.txtNomeReceita);
        txtIngredientes = findViewById(R.id.txtIngredientes);
        txtPreparo = findViewById(R.id.txtPreparo);
        imgReceitaSelecionada = findViewById(R.id.imgReceitaSelecionada);
        imgSalvo = findViewById(R.id.imgSalvo);
        txtCalorias = findViewById(R.id.txtCalorias);

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

        this.context = MainReceitasSelecionadas.this; //***********************************************************


        Intent intent = getIntent();
        // Pega os dados do usuário logado
        user = (Usuarios) intent.getSerializableExtra("userSerializable");
        // Pega os dados da receita selecionada
        receita = (Receitas) intent.getSerializableExtra("receitaSerializable");

        //Se receita não for vazia
        if(receita != null){
            //Coloca os dados nos devidos lugares
            txtNomeReceita.setText(receita .getNome());
            txtIngredientes.setText(receita.getIngredientes());
            txtPreparo.setText(receita.getModoPreparo());
            txtCalorias.setText(("Calorias: " + receita.getCalorias().toString() + " kcal"));

            //Mostra a imagem da receita
            if((receita .getFoto() != null) && (receita .getFoto().length()>0)){
                Picasso.get().load(receita.getFoto()).into(imgReceitaSelecionada);
            }
            else
            {
                Picasso.get().load(R.drawable.home_img1).into(imgReceitaSelecionada);
            }
        }

        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
        if(user != null) //Se tiver um usuário logado
        {
            // verifica se o usuário logado salvou a receita selecionada
            Call<List<ReceitaSalva>> call = service.verSeSalvou(user.getId(),receita.getId());
            call.enqueue(new Callback<List<ReceitaSalva>>() {
                @Override
                public void onResponse(Call<List<ReceitaSalva>> call, Response<List<ReceitaSalva>> responseSalvou) {
                    if(responseSalvou.isSuccessful()){
                        if(!responseSalvou.body().isEmpty())
                        {
                            //Tem dado no BD, ou seja, user já salvou essa receita
                            salvou = 1;
                            imgSalvo.setImageResource(R.drawable.salvou);
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<ReceitaSalva>> call, Throwable t) {
                    String messageProblem = t.getMessage().toString();
                    Toast.makeText(MainReceitasSelecionadas.this, messageProblem, Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainReceitasSelecionadas.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
                }
            });
        }
        else
            imgSalvo.setImageResource(R.drawable.salvar); // Não tem um usuário logado


        //Botões inferiores
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


        //Botão para criar um novo comentário
        btnComentar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){
                    // Se tiver um usuário logado;
                    //Guarda o comentário feito em uma variável
                    String comentario = edtComentarioFeito.getText().toString();

                    // cria um comentário com os dados passados e necessários
                    Comentario novoComentario = new Comentario(null, user.getId(), receita.getId(),0,comentario);
                    // passa o comentário criado para a API inserir no BD
                    Call<Comentario> call = service.criarComentario(novoComentario);
                    call.enqueue(new Callback<Comentario>() {
                        @Override
                        public void onResponse(Call<Comentario> call, Response<Comentario> response) {
                            if(response.isSuccessful()){
                                //deu tudo certo, logo volta a mostrar a tela da receita selecionada
                                //agora com o novo comentário
                                Intent intent = new Intent(MainReceitasSelecionadas.this,MainReceitasSelecionadas.class);
                                intent.putExtra("userSerializable", user);
                                intent.putExtra("receitaSerializable", receita);
                                context.startActivity(intent);
                            }
                        }
                        @Override
                        public void onFailure(Call<Comentario> call, Throwable t) {
                            Toast.makeText(MainReceitasSelecionadas.this, "ERRO criação não efetuada: ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(MainReceitasSelecionadas.this, "[ERROR] Faça login antes de comentar.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Ícone de receita salva
        imgSalvo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null)
                {
                    // Se tiver um usuário logado
                    if(salvou == 0)
                    {
                        // se não tinha salvado;
                        salvou = 1; // guarda que agora está salvo;

                        //cria um novo dado
                        ReceitaSalva salvarReceita = new ReceitaSalva(null, user.getId(), receita.getId());

                        // passa o dado para a API inserir no BD
                        Call<ReceitaSalva> call3 = service.salvarReceita(salvarReceita);
                        call3.enqueue(new Callback<ReceitaSalva>() {
                            @Override
                            public void onResponse(Call<ReceitaSalva> call3, Response<ReceitaSalva> response) {
                                if(response.isSuccessful()){
                                    // muda o ícone para mostrar que a receita está salva
                                    imgSalvo.setImageResource(R.drawable.salvou);
                                }
                            }
                            @Override
                            public void onFailure(Call<ReceitaSalva> call3, Throwable t) {
                                Toast.makeText(MainReceitasSelecionadas.this, "ERRO criação não efetuada: ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else
                    {
                        //Já estava salva
                        salvou = 0; //Guarda que agora não está mais salva

                        //Passa os dados para a API excluir do BD onde relacionada o user com a receita
                        Call<ReceitaSalva> call4 = service.excluirReceitaSalva(receita.getId(), user.getId());
                        call4.enqueue(new Callback<ReceitaSalva>() {
                            @Override
                            public void onResponse(Call<ReceitaSalva> call4, Response<ReceitaSalva> response) {
                                if(response.isSuccessful()){
                                    // Muda o ícone para mostrar que não está salva
                                    imgSalvo.setImageResource(R.drawable.salvar);
                                }
                            }
                            @Override
                            public void onFailure(Call<ReceitaSalva> call4, Throwable t) {
                                Toast.makeText(MainReceitasSelecionadas.this, "ERRO criação não efetuada: ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
                else
                {
                    // Não tem login
                    Toast.makeText(MainReceitasSelecionadas.this, "[ERROR] Faça login antes de salvar essa receita.", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Pega os comentários relacionados a essa receita;
        Call<List<Avaliacao>> call2 = service.getAvaliacaoByIdReceita(receita.getId());
        call2.enqueue(new Callback<List<Avaliacao>>() {
            @Override
            public void onResponse(Call<List<Avaliacao>> call2, Response<List<Avaliacao>> response) {
                if(response.isSuccessful()){
                    //Passa para o método que colocará os dados em um formato específico
                    populateGridView(response.body());
                }else{
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(MainReceitasSelecionadas.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(MainReceitasSelecionadas.this, "entrou no else do response AVALIACAO", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Avaliacao>> call2, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(MainReceitasSelecionadas.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainReceitasSelecionadas.this, "entrou no else do Failure avaliacao", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateGridView(List<Avaliacao> listaAvaliacao){
        //Método que coloca os comentários do formato que queremos
        comentarioGridView = (GridView) findViewById(R.id.comGridView);
        adapter = new GridViewAdapterComentario(this,listaAvaliacao);
        comentarioGridView.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String str = item.toString();
        // menu lateral com as telas inacessíveis para quem não tiver login
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