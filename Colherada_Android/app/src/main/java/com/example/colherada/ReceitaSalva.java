package com.example.colherada;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReceitaSalva implements Serializable{

    @SerializedName("id")
    private Integer id;
    @SerializedName("user")
    private Integer user;
    @SerializedName("receitas")
    private Integer receitas;

    public ReceitaSalva(Integer id,Integer user,Integer receitas){

        this.id = id;
        this.user = user;
        this.receitas = receitas;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getReceitas() {
        return receitas;
    }

    public void setReceitas(Integer receitas) {
        this.receitas = receitas;
    }

}