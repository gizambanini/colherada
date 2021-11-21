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

    @GET("/api/Avaliacao/{id}") // pega os comentários da receita
    Call<List<Avaliacao>> getAvaliacaoByIdReceita(@Path("id") Integer id);

    @POST("/api/Avaliacao/") // adiciona um comentário
    Call<Comentario> criarComentario(@Body Comentario comentario);

    @GET("/api/ReceitaFiltro/{filtro}") //pega as receitas pelo filtro
    Call<List<Receitas>> getReceitasByFiltro(@Path("filtro") String filtro);

    @GET("/api/ReceitaSalva/{id}") // pega as receitas que o usuário salvou
    Call<List<Receitas>> getReceitaSalvaByIdUser(@Path("id") Integer id);

    @GET("/api/ReceitaSalva/{idUser}/{receita}") // ver se o usuário logado salvou uma receita específica
    Call<List<ReceitaSalva>> verSeSalvou(@Path("idUser") Integer idUser,@Path("receita") Integer receita);

    @POST("/api/ReceitaSalva/") // salva a receita selecionada
    Call<ReceitaSalva> salvarReceita(@Body ReceitaSalva receitasalva);

    @DELETE("/api/ReceitaSalva/{idReceita}/{idUsuario}") // deixa de salvar a receita
    Call<ReceitaSalva> excluirReceitaSalva(@Path("idReceita") Integer idReceita,@Path("idUsuario") Integer idUsuario);

    @PUT("/api/usuario/{idUser}") //atualiza os dados do usuário
    Call<Usuarios> atualizarUsuario(@Path("idUser")Integer idUser, @Body Usuarios usuario);


}