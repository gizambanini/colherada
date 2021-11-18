package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCriarConta extends AppCompatActivity {
    Button btnSalvar, btnLogin;
    EditText edtEmail, edtSenha, edtNome, edtUrlFoto;
    ImageView imgUser;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_criar_conta);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSenha = (EditText)findViewById(R.id.edtSenha);
        edtUrlFoto = (EditText)findViewById(R.id.edtUrlFoto);
        imgUser = (ImageView) findViewById(R.id.imgUser);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainCriarConta.this,MainLogin.class);
                startActivity(intent);
            }
        });
        edtUrlFoto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Picasso.get().load(edtUrlFoto.getText().toString()).into(imgUser);
                }
            }
        });
        this.context = MainCriarConta.this;
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if(edtNome.getText().toString().equals("")&&edtEmail.getText().toString().equals("")&&
                        edtSenha.getText().toString().equals("")) {
                    Toast.makeText(MainCriarConta.this, "Preencha todas os dados!", Toast.LENGTH_LONG).show();
                }else{
                    String strNome = edtNome.getText().toString();
                    String strEmail = edtEmail.getText().toString();
                    String strSenha = edtSenha.getText().toString();
                    String strImage = edtUrlFoto.getText().toString();
                    Usuarios usuario = new Usuarios(null,strNome,strEmail,strSenha, strImage);
                    Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
                    Call<Usuarios> call = service.criarUsuario(usuario);
                    call.enqueue(new Callback<Usuarios>() {
                        @Override
                        public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                            if(response.isSuccessful()){
                                Intent intent = new Intent(MainCriarConta.this,MainLogin.class);
                                intent.putExtra("userSerializable", usuario);
                                context.startActivity(intent);
                            }
                        }
                        @Override
                        public void onFailure(Call<Usuarios> call, Throwable t) {
                            Toast.makeText(MainCriarConta.this, "ERRO criação não efetuada: "+ strNome + "/" + strEmail + "/" + strSenha + "/" + strImage , Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}