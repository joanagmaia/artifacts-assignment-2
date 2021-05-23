package com.company;

import java.io.Serializable;


public abstract class Pessoa implements Serializable {
    private String nome,email;

    public Pessoa(String nome,String email){
        this.nome = nome;
        this.email=email;
    }
    public String getNome(){
        return this.nome;
    }
}

