package com.example.colherada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainReceitas extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btnHome, btnCalorias;
    private GridView receitaGridView;
    private GridViewViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_receitas);
        btnHome = (Button) findViewById(R.id.btnHome3);
        btnCalorias = (Button) findViewById(R.id.btnCalorias3);

        // filtro
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filtro_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainReceitas.this, MainActivity.class);

                startActivity(intent);
                finish();
            }
        });
        btnCalorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainReceitas.this, MainCalorias.class);

                startActivity(intent);
                finish();
            }
        });

        //Download JSON via Retrofit
        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);

        //Pegar a rota do Json
        Call<List<Receitas>> call = service.getReceita();
        call.enqueue(new Callback<List<Receitas>>() {
            @Override
            public void onResponse(Call<List<Receitas>> call, Response<List<Receitas>> response) {

                if(response.isSuccessful()){
                    Toast.makeText(MainReceitas.this, "deu certo", Toast.LENGTH_LONG).show();
                    populateGridView(response.body());
                }else{
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(MainReceitas.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(MainReceitas.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Receitas>> call, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(MainReceitas.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainReceitas.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateGridView(List<Receitas> listaReceita){
        receitaGridView = (GridView) findViewById(R.id.receitaGridView);
        adapter = new GridViewViewAdapter(this,listaReceita);
        receitaGridView.setAdapter(adapter);
    }

    @Override // filtro
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String txt = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),txt,Toast.LENGTH_SHORT).show();
    }

    @Override // filtro
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}