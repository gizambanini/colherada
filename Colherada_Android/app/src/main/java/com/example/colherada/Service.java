package com.example.colherada;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.PUT;
import retrofit2.http.Path;

interface Service {

    @GET("/api/Receitas/")
    Call<List<Receitas>> getReceita();

    @POST("/api/Receitas/")
    Call<Receitas> incluirReceita(@Body Receitas receitas);

    @GET("/api/usuario/{email}")
    Call<Usuarios> getUsuarioByEmail(@Path("email") String email);

}