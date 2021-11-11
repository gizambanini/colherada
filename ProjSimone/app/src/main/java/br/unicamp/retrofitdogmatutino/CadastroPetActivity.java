package br.unicamp.retrofitdogmatutino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroPetActivity extends AppCompatActivity {



    EditText edtId,edtNome,edtRaca,edtImagem;
    Button btnIncluir, btnAlterar, btnLimpar, btnExcluir;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtId = (EditText) findViewById(R.id.edtId);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtRaca = (EditText) findViewById(R.id.edtRaca);
        edtImagem = (EditText) findViewById(R.id.edtImagem);

        Intent intent = getIntent();
        Dog dog = (Dog) intent.getSerializableExtra("dogSerializable");
        if(dog != null){
            edtId.setText(dog.getId());
            edtNome.setText(dog.getNome());
            edtRaca.setText(dog.getRaca());
            edtImagem.setText(dog.getImagem());
        }

        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtId.setText("");
                edtNome.setText("");
                edtRaca.setText("");
                edtImagem.setText("");
            }
        });

        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirPet();
            }
        });

        btnIncluir = (Button) findViewById(R.id.btnIncluir);
        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirPet();
            }
        });

        btnAlterar = (Button) findViewById(R.id.btnAlterar);
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarPet();
            }
        });
    }

    private void excluirPet(){

        String strId = edtId.getText().toString();

        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
        Call<Dog> call = service.excluirDog(strId);

        call.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(CadastroPetActivity.this,MainActivity.class);
                    finish();
                    startActivity(intent);

                }
            }
            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                Toast.makeText(CadastroPetActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void inserirPet(){

        String strId = edtId.getText().toString();
        String strNome = edtNome.getText().toString();
        String strRaca = edtRaca.getText().toString();
        String strImage = edtImagem.getText().toString();
        Dog dog = new Dog(strId,strNome,strRaca,strImage);

        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
        Call<Dog> call = service.incluirDog(dog);

        call.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(CadastroPetActivity.this,MainActivity.class);
                    finish();
                    startActivity(intent);

                }

            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                Toast.makeText(CadastroPetActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void alterarPet(){
        String strId = edtId.getText().toString();
        String strNome = edtNome.getText().toString();
        String strRaca = edtRaca.getText().toString();
        String strImage = edtImagem.getText().toString();
        Dog dog = new Dog(strId,strNome,strRaca,strImage);
        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
        Call<Dog> call = service.alterarDog(strId,dog);
        call.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(CadastroPetActivity.this,MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                Toast.makeText(CadastroPetActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}