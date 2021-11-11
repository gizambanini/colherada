package br.unicamp.retrofitdogmatutino;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class Dog implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("nome")
    private String nome;
    @SerializedName("raca")
    private String raca;
    @SerializedName("image")
    private String imagem;


    public Dog(String id, String nome,String raca,String imagem){

        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.imagem = imagem;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }


}
