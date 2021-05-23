package com.company;

import java.io.Serializable;
import java.util.ArrayList;


public class Aluno extends Pessoa implements Serializable {
    int num,ano_matricula;
    Curso curso;
    String estatuto;
    ArrayList<Exame> exames;

    Aluno(String nome, String email,Curso curso,String estatuto,int num,int ano_matricula){
        super(nome,email);
        this.curso = curso;
        this.estatuto = estatuto;
        this.exames = null;
        this.num = num;
        this.ano_matricula = ano_matricula;
    }
    public int getAno(){
        return this.ano_matricula;
    }
    public Curso getCurso(){
        return this.curso;
    }
    public String getEstatuto(){return this.estatuto;}
    public ArrayList<Exame> getExames(){return this.exames;}

    @Override
    public String toString(){return this.getNome();}
}

