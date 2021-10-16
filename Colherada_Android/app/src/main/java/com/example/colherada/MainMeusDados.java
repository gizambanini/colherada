package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class MainMeusDados extends AppCompatActivity {

    CardView cdviewImgUser;
    ImageView imgUser;
    ImageButton btnMenu;
    Button btnReceitas, btnHome, btnCalorias, btnSalvar;
    EditText edtxtNome, edtxtEmail, edtxtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_meus_dados);
        btnCalorias = (Button) findViewById(R.id.btnCalorias);
        btnReceitas = (Button) findViewById(R.id.btnReceitas);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        cdviewImgUser = (CardView) findViewById(R.id.cdviewImgUser);
        imgUser = (ImageView) findViewById(R.id.imgUser);
        edtxtNome = (EditText) findViewById(R.id.edtxtNome);
        edtxtEmail = (EditText) findViewById(R.id.edtxtEmail);
        edtxtSenha = (EditText) findViewById(R.id.edtxtSenha);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        cdviewImgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Trocar de foto
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtxtNome.getText().toString() == "" && edtxtEmail .getText().toString() == ""
                        && edtxtSenha.getText().toString() == ""){

                    Snackbar.make(view,"Preencha todos os campos!", Snackbar.LENGTH_SHORT).show();
                }else{
                    // Atualizar dados aqui ....
                }
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
}