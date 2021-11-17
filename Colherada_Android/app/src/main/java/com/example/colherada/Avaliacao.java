package com.example.colherada;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Avaliacao implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("usuario")
    private Integer usuario;
    @SerializedName("receita")
    private Integer receita;
    @SerializedName("curtida")
    private Integer curtida;
    @SerializedName("comentario")
    private String comentario;


    public  Avaliacao (Integer id, Integer receita, Integer usuario, Integer curtida, String comentario)
    {
        this.id = id;
        this.usuario = usuario;
        this.receita = receita;
        this.curtida = curtida;
        this.comentario = comentario;


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getReceita() {
        return receita;
    }

    public void setReceita(Integer receita) {
        this.receita = receita;
    }

    public Integer getCurtida() {
        return curtida;
    }

    public void setCurtida(Integer curtida) { this.curtida = curtida; }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


}