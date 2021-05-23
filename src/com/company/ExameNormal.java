package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class ExameNormal extends Exame implements Serializable {
    public ExameNormal(){
    }

    public ExameNormal(Disciplina disciplina, Docente docente, ArrayList<Sala> s,Data data, ArrayList<Nota> notas,ArrayList<Funcionario> vigilantes){
        super(disciplina,docente,s,data,vigilantes);
    }
    @Override
    public String toString(){
        return String.format("%s - %s - Epoca Normal ",this.getDisciplina(),this.getData());
    }
}
