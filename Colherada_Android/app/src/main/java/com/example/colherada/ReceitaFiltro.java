package com.example.colherada;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReceitaFiltro implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("receita")
    private Integer receita;
    @SerializedName("filtro")
    private Integer filtro;

    public  ReceitaFiltro (Integer id, Integer receita, Integer filtro)
    {
        this.id = id;
        this.receita = receita;
        this.filtro = filtro;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceita() {
        return receita;
    }

    public void setReceita(Integer receita) {
        this.receita = receita;
    }

    public Integer getFiltro() {
        return filtro;
    }

    public void setFiltro(Integer filtro) {
        this.filtro = filtro;
    }


}