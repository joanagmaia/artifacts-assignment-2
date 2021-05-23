package com.company;

import com.company.Exame;

import java.io.Serializable;
import java.util.ArrayList;

public class Sala implements Serializable {
    private String nome;
    ArrayList<Exame> exames = new ArrayList<>();

    public Sala(){}
    public Sala(String nome){
        this.nome = nome;
    }
    @Override
    public String toString(){
        return this.nome;
    }
    public ArrayList<Exame> getExames(){return this.exames;}
    public String getNomeSala(){
        return this.nome;
    }



}
