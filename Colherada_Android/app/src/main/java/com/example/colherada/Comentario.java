package com.example.colherada;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comentario implements Serializable {
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

    public  Comentario (Integer id,Integer usuario, Integer receita, Integer curtida,String comentario )
    {
        this.id = id;
        this.usuario = usuario;
        this.receita = receita;
        this.curtida = curtida ;
        this.comentario= comentario;
    }

    public Integer getIdComentario() {
        return id;
    }

    public void setIdComentario(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return usuario;
    }

    public void setIdUser (String nomeUser) {
        this.usuario = usuario;
    }

    public Integer getIdReceita() {
        return receita;
    }

    public void setIdReceita(String imagemUser) {
        this.receita= receita;
    }

    public Integer getCurtida() { return curtida ; }

    public void setCurtida(Integer estrelas ) {
        this.curtida = curtida ;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}