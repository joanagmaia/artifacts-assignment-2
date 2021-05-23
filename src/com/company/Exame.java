package com.company;

import java.io.Serializable;
import java.util.ArrayList;


public abstract class Exame implements Serializable  {
    private ArrayList<Sala> salas ;
    private Disciplina disciplina;
    private Docente responsavel;
    private ArrayList<Nota> notas;
    private ArrayList<Funcionario> vigilantes;
    private Data data;
    private boolean corrigido = false;
    private boolean exclusivo = false;

    public Exame(){
    }

    public Exame(Disciplina disciplina, Docente docente, ArrayList<Sala> s, Data data,
                 ArrayList<Funcionario> vigilantes){
        this.disciplina = disciplina;
        this.responsavel = docente;
        this.salas = s;
        this.data = data;
        this.notas = new ArrayList<>();
        this.vigilantes = vigilantes;
    }
    public ArrayList<Funcionario> getVigilantes(){return this.vigilantes;}
    public Docente getResponsavel(){return this.responsavel;}
    public void atribuiExclusividade(){
        this.exclusivo = true;
    }
    public boolean getExclusividade(){
        return this.exclusivo;
    }
    public boolean getCorrigido(){return this.corrigido;}
    public Data getData(){
        return this.data;
    }
    public Disciplina getDisciplina(){ return this.disciplina; }
    public ArrayList<Sala> getSalas(){return this.salas;}

    /**
     * Este metodo marca o exame como corrigido
     */
    public void marcarCorrigido(){
        this.corrigido = true;
    }

    public ArrayList<Nota> getNotas(){
        return this.notas;
    }


}
