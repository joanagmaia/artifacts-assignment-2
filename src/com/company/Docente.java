package com.company;

import java.io.Serializable;
import java.util.ArrayList;


public class Docente extends Funcionario implements Serializable {
    String area_inv;
    Docente(int num,String nome,String area_inv,String email){
        super(num,nome,nome,email);
        this.area_inv= area_inv;
        this.exames = new ArrayList<Exame>();
    }
    @Override
    public String toString(){
        return this.getNome();
    }
}
