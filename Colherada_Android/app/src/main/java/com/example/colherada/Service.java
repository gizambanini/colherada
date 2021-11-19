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
    Call<List<Receitas>> getReceita(); //Pegar TDS as receitas

    @POST("/api/Receitas/")
    Call<Receitas> incluirReceita(@Body Receitas receitas);  //Criar nova receita

    @GET("/api/usuario/{email}")
    Call<Usuarios> getUsuarioByEmail(@Path("email") String email); //Login

    @POST("/api/usuario/")
    Call<Usuarios> criarUsuario(@Body Usuarios userNovo);  //Criar novo usuário

    @GET("/api/Avaliacao/") // Avaliacao
    Call<List<Avaliacao>> getAvaliacao(); //////////////

    @GET("/api/Avaliacao/{id}") //Ir na tabela Receitas e fazer +1 em avaliacao!
    Call<List<Avaliacao>> getAvaliacaoByIdReceita(@Path("id") Integer id);

    @POST("/api/Avaliacao/")
    Call<Avaliacao> criarAvaliacao(@Body Avaliacao avaliacao);

    @GET("/api/ReceitaFiltro/{filtro}")
    Call<List<Receitas>> getReceitasByFiltro(@Path("filtro") String filtro);

    @GET("/api/ReceitaSalva/{id}")
    Call<List<Receitas>> getReceitaSalvaByIdUser(@Path("id") Integer id);

    @POST("/api/ReceitaSalva/")
    Call<ReceitaSalva> criarAvaliacao(@Body ReceitaSalva receitasalva);

    @DELETE("/api/ReceitaSalva/{idReceita}/{IdUsuario}")
    Call<ReceitaSalva> excluirReceitaSalva(@Path("idReceita") String idReceita,@Path("idUsuario") String idUsuario);

    @PUT("/api/usuario/{idUser}")
    Call<Usuarios> atualizarUsuario(@Path("idUser")Integer idUser, @Body Usuarios usuario);

    @PUT("/api/Receitas/{idReceita}")
    Call<Receitas> atualizarReceita(Integer idReceita, @Body Receitas receita);
}