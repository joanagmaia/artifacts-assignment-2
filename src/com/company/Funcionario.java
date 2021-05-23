package com.company;

import java.io.Serializable;
import java.util.*;


public class Funcionario extends Pessoa implements Serializable {
    int num;
    String categoria;
    ArrayList<Exame> exames = null;

    Funcionario(int num, String categoria,String nome,String email){
        super(nome,email);
        this.num=num;
        this.categoria = categoria;
    }

    public ArrayList<Exame> getExames(){return this.exames;}
    public int getNum(){return this.num;}
    @Override
    public String toString(){
        return String.format("%s-%d",this.getNome(),this.getNum());
    }
}

