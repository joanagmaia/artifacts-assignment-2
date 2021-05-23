package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Curso implements Serializable {
    String nome,grau;
    private int duracao;
    private ArrayList<Disciplina> disciplinas = new ArrayList<>();

    public Curso(String nome){
        this.nome = nome;
        if(nome.charAt(0)=='L'){
            this.grau = "Mestrado";
            this.duracao = 5;
        }else{
            this.grau = "Licenciatura";
            this.duracao = 3;
        }
    }
    public ArrayList<Disciplina> getDisciplinas(){return this.disciplinas;}
    @Override
    public String toString(){
        return this.nome;
    }
    public String getNome(){ return this.nome;}
    public int getDuracao(){return this.duracao;}
}

