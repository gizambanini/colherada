package com.example.colherada;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Avaliacao implements Serializable {
    @SerializedName("nomeUser")
    private String nomeUser;
    @SerializedName("imagemUser")
    private String imagemUser;
    @SerializedName("comentario")
    private String comentario;
    @SerializedName("estrelas")
    private Integer estrelas;

    public  Avaliacao (String nomeUser, String imagemUser, String comentario, Integer estrelas )
    {
        this.nomeUser = nomeUser;
        this.imagemUser= imagemUser;
        this.comentario= comentario;
        this.estrelas = estrelas ;

    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser (String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getImagemUser() {
        return imagemUser;
    }

    public void setImagemUser(String imagemUser) {
        this.imagemUser= imagemUser;
    }

    public Integer getEstrelas () {
        return estrelas ;
    }

    public void setEstrelas(Integer estrelas ) {
        this.estrelas = estrelas ;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}