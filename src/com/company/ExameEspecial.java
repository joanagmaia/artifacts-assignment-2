package com.company;

import java.io.Serializable;
import java.util.ArrayList;


public class ExameEspecial extends Exame implements Serializable{
    public ExameEspecial(){}

    public ExameEspecial(Disciplina disciplina, Docente docente, ArrayList<Sala> s, Data data, ArrayList<Nota> notas, ArrayList<Funcionario> vigilantes){
        super(disciplina,docente,s,data,vigilantes);
        this.atribuiExclusividade();
    }

    @Override
    public String toString(){
        String str = String.format("%s - %s - Epoca Especial",this.getDisciplina(),this.getData());
        return str;
    }
}

