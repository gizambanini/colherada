package com.example.colherada;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Receitas implements Serializable{

    @SerializedName("id")
    private Integer id;             // MUDOU DE STRING PARA INTEGER <----------
    @SerializedName("nome")
    private String nome;
    @SerializedName("ingredientes")
    private String ingredientes;
    @SerializedName("modoPreparo")
    private String modoPreparo;
    @SerializedName("calorias")
    private Integer calorias;
    @SerializedName("foto")
    private String foto;
    @SerializedName("criador")
    private Integer criador;
    @SerializedName("avaliacao")
    private Integer avaliacao;

    public Receitas(Integer id, String nome,String ingredientes,String modoPreparo, Integer calorias, String foto, Integer criador, Integer avaliacao){

        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.modoPreparo = modoPreparo;
        this.calorias = calorias;
        this.foto = foto;
        this.criador = criador;
        this.avaliacao = avaliacao;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo ) {
        this.modoPreparo = modoPreparo;
    }
    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }
    public String getFoto() {
        return foto ;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    public Integer getCriador() {
        return criador;
    }

    public void setCriador(Integer criador) {
        this.criador = criador;
    }
    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }
}
