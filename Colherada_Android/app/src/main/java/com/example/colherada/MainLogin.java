package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.Serializable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLogin extends AppCompatActivity {
    Button btnEntrar, btnCriarConta;
    EditText edtEmail, edtSenha;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);

        btnCriarConta = (Button)findViewById(R.id.btnCriarConta);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSenha = (EditText)findViewById(R.id.edtSenha);

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
                login(MainLogin.this);
            }
        });

    }

    //Método para login
    private void login(Context esseContext) {

        String strEmail = edtEmail.getText().toString();
        String strSenha = edtSenha.getText().toString();
        this.context = esseContext;
        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
        Call<Usuarios> call = service.getUsuarioByEmail(strEmail);

        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                if (response.isSuccessful()) {
                   if(response.body().getSenha() != null) {
                        if (response.body().getSenha().equals(strSenha)) {

                            Usuarios objUser = new Usuarios(response.body().getId(), response.body().getNome(), strEmail, strSenha, response.body().getFoto());
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("userSerializable", objUser);
                            context.startActivity(intent);

                            // MUDAR PARA LEVAR PRA HOME
                        }
                    }
                    else {
                        String errorMessage = "[ERROR] Dados incorretos!";
                        Toast.makeText(MainLogin.this, errorMessage, Toast.LENGTH_LONG).show();
                    }

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