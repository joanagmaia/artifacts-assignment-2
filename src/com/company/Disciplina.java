package com.company;

import javax.print.Doc;
import java.io.Serializable;
import java.util.ArrayList;

public class Disciplina implements Serializable {
    private String nome = new String();
    Docente responsavel;
    ArrayList<Docente> assistentes = new ArrayList<>();
    ArrayList<Aluno> inscritos = new ArrayList<>();
    ArrayList<Exame> exames = new ArrayList<>();

    public Disciplina(String nome, Docente responsavel, ArrayList<Docente> assistentes){
        this.nome = nome;
        this.responsavel = responsavel;
        this.assistentes = assistentes;
    }
    public ArrayList<Exame> getExames(){return this.exames;}
    public String getNome(){ return this.nome;}
    public ArrayList<Aluno> getInscritos(){return this.inscritos;}
    @Override
    public String toString(){return this.getNome();}
}

