package com.company;

import java.io.Serializable;

public class Data implements Serializable{
    private int dia,mes,ano,duracao;
    private String dataString;

    public float getHora() {
        return hora;
    }
    public void setHora(float hora) {
        this.hora = hora;
    }
    public int getDuracao(){
        return this.duracao;
    }

    private float hora;//HORA POR EXEMPLO 18:30 = 18.5 duracao em horas
    public Data(float hora,int dia,int mes,int ano,int duracao,String dataString){
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.duracao = duracao;
        this.dataString = dataString;
    }
    public int getAno(){return this.ano;}
    public int getMes(){return this.mes;}
    public int getDia(){return this.dia;}

    /**
     * Este metodo verifica se duas datas tem algum momento em que se sobrepoem
     * @param data
     * @return
     */

    boolean verificaSobreposicao(Data data){
        if(!(this.dia == data.dia && this.mes == data.mes && this.ano == data.ano))
            return false;
        if(this.hora>=data.hora && this.hora + this.duracao <data.hora || data.hora<= this.hora && data.hora + data.duracao > this.hora ){
            return true;
        }
        else
            return false;
    }

    /**
     * Este metodo verifica a validade de uma data inserida
     * @param data
     * @return
     */

    public boolean verifica_data(Data data){
        if(data.ano>=2016){
            if(data.mes>=1&&data.mes<13){
                if(data.mes==1||data.mes==3||data.mes==5||data.mes==7||data.mes==8||data.mes==10||data.mes==12){
                    if(data.dia>0&&data.dia<=31){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if(data.mes==2){
                    if(data.dia>0&&data.dia<=29){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    if(data.dia>0&&data.dia<=30){
                        return true;
                    }
                }
            }
            else{
                return false;
            }

        }
        else{
            return false;
        }
        return false;
    }

    @Override
    public String toString(){
        int hora = (int)this.hora;
        int minutos = (int)(((this.hora/hora) *60)/100);
        String str = String.format("Dia: %d/%d/%d às %d:%d com duracao de %d horas",this.getDia(),this.getMes(),this.getAno(),hora,minutos,this.getDuracao());
        return str;
    }

}

