package br.unicamp.retrofitdogmatutino;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GridView dogGridView;
    private FloatingActionButton fab;
    ProgressBar myProgessBar;
    private GridViewViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      myProgessBar = (ProgressBar) findViewById(R.id.myProgressBar);
      myProgessBar.setVisibility(View.VISIBLE);
      fab = (FloatingActionButton) findViewById(R.id.fab);

      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this,CadastroPetActivity.class);
              startActivity(intent);
              finish();
          }
      });


      //Download JSON via Retrofit
      Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
      //Pegar a rota do Json
      Call<List<Dog>> call = service.getDog();
      call.enqueue(new Callback<List<Dog>>() {
          @Override
          public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {

              if(response.isSuccessful()){
                  Toast.makeText(MainActivity.this, "deu certo", Toast.LENGTH_LONG).show();
                  myProgessBar.setVisibility(View.GONE);
                  populateGridView(response.body());
              }else{
                  String errorMessage = response.errorBody().toString();
                  Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                  Toast.makeText(MainActivity.this, "entrou no else do response", Toast.LENGTH_LONG).show();
              }

          }

          @Override
          public void onFailure(Call<List<Dog>> call, Throwable t) {
              myProgessBar.setVisibility(View.GONE);
              String messageProblem = t.getMessage().toString();
              Toast.makeText(MainActivity.this, messageProblem, Toast.LENGTH_SHORT).show();
              Toast.makeText(MainActivity.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
          }
      });
    }

    private void populateGridView(List<Dog> listaDog){
        dogGridView = (GridView) findViewById(R.id.dogGridView);
        adapter = new GridViewViewAdapter(this,listaDog);
        dogGridView.setAdapter(adapter);
    }

}