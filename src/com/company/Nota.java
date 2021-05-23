package com.company;

import java.io.Serializable;


public class Nota implements Serializable {
    private Aluno aluno;
    private float nota;
    Nota(Aluno aluno){
        this.aluno = aluno;
        this.nota = 0;
    }
    public Aluno getAluno(){
        return this.aluno;
    }

    /**
     * Define valor para a nota de um aluno
     * @param valor
     */
    public void atribuiNota(float valor){
        this.nota = valor;
    }
    public float getNota(){return this.nota;}
    @Override
    public String toString(){
        return String.format("%s -> %f",this.aluno.getNome(),this.getNota());
    }
}
