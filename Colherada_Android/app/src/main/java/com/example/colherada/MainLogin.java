package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLogin extends AppCompatActivity {
    Button btnEntrar, btnCriarConta;
    EditText edtEmail, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btnCriarConta = (Button)findViewById(R.id.btnCriarConta);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSenha = (EditText)findViewById(R.id.edtxtSenha);

        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainLogin.this,MainCriarConta.class);

                startActivity(intent);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                login();
            }
        });

    }

    //Método para login
    private void login() {

        String strEmail = edtEmail.getText().toString();
        String strSenha = edtSenha.getText().toString();

        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
        Call<Usuarios> call = service.getUsuarioByEmail(strEmail);

        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                if (response.isSuccessful()) {
                    // pegar o response.body().email ? e comparar com o que digitamos
                    // se for igual a gnt vê a senha
                    // se td bater a gnt leva o id do usuário ou email para as outras telas
                } else {
                    String errorMessage = "[ERROR] Dados incorretos!";
                    Toast.makeText(MainLogin.this, errorMessage, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                Toast.makeText(MainLogin.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}