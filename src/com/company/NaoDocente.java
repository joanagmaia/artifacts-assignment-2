package com.company;

import java.io.Serializable;

public class NaoDocente extends Funcionario implements Serializable{
    String cargo;
    NaoDocente(int num,String nome,String area_inv,String email){
        super(num,nome,email,nome);
        this.cargo = area_inv;
    }
}
