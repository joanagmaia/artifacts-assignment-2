package com.company;

import com.company.Geral;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import sun.misc.IOUtils;

import static org.junit.jupiter.api.Assertions.*;

class listaExamesFunc_TestSuit {
    //funcionaros =  0 to 7
    //funcionarios(Professors) = 8 to 27
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    String readFile(){
        try{
            Ficheiro dados = new Ficheiro();
            dados.abreLeitura("resultados.txt");

            Scanner in = new Scanner(new FileReader("resultados.txt"));

            StringBuilder sb = new StringBuilder();
            while(in.hasNext()) {
                sb.append(in.next());
            }
            in.close();
            return sb.toString();
        }
        catch(Exception e){
            systemOut.println("Error opening file:" + e);
        }
        return "error";
    }

    @Test
    @DisplayName("TCLEF1: listaExamesFunc Control Flow Path 1")
    void TCLEF1() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2A = "Erro ao abrir o ficheiro";
        String expected2B = "Lista de Funcionarios está vazia";
        String input1 = "1";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        //geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().startsWith(expected1));
        assert(testOut.toString().contains(expected2A) || testOut.toString().contains(expected2B));
    }

    @Test
    @DisplayName("TCLEF2: listaExamesFunc Control Flow Path 2")
    void TCLEF2() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?";
        String expected3 = "GG - Dia: 1/1/2021 às 1:0 com duracao de 1 horas - Epoca Normal ";
        String input1 = "17";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().contains(expected2));
        assert(testOut.toString().endsWith(expected3 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF3: listaExamesFunc Control Flow Path 3")
    void TCLEF3() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?";
        String expected3 = "GG - Dia: 1/1/2021 às 1:0 com duracao de 1 horas - Epoca Normal ";
        String expectedFile1 = "GG-Dia:1/1/2021às1:0comduracaode1horas-EpocaNormal";
        String input1 = "17";
        String input2 = "1";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        String file = readFile();
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().contains(expected2));
        assert(testOut.toString().endsWith(expected3 + "\r\n"));
        assert(file.equals(expectedFile1));
    }

    @Test
    @DisplayName("TCLEF4: listaExamesFunc Control Flow Path 4")
    void TCLEF4() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?(0-n/1-n):";
        String input1 = "2";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF5: listaExamesFunc Control Flow Path 5")
    void TCLEF5() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?";
        String expected3 = "GG - Dia: 1/1/2021 às 1:0 com duracao de 1 horas - Epoca Normal ";
        String input1 = "3";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().contains(expected2));
        assert(testOut.toString().endsWith(expected3 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF6: listaExamesFunc Control Flow Path 6")
    void TCLEF6() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?";
        String expected3 = "GG - Dia: 1/1/2021 às 1:0 com duracao de 1 horas - Epoca Normal ";
        String expectedFile1 = "GG-Dia:1/1/2021às1:0comduracaode1horas-EpocaNormal";
        String input1 = "3";
        String input2 = "1";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().contains(expected2));
        assert(testOut.toString().endsWith(expected3 + "\r\n"));
        String file = readFile();
        assert(file.equals(expectedFile1));
    }

    @Test
    @DisplayName("TCLEF7: listaExamesFunc Control Flow Path 7")
    void TCLEF7() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?(0-n/1-n):";
        String input1 = "7";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    //to test TCLEF8 It requires a way to load a file that only has 10 funcionarios
    //Still trying to figure out how to use mockito to solve this issue

    @Test
    @DisplayName("TCLEF9: listaExamesFunc Control Flow Path Loop 2")
    void TCLEF9() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?";
        String expected3 = "PA - Dia: 1/1/2021 às 1:0 com duracao de 1 horas - Epoca Normal ";
        String expected4 = "PA - Dia: 2/1/2021 às 1:0 com duracao de 1 horas - Epoca Normal ";
        String expected5 = "OA - Dia: 3/1/2021 às 3:0 com duracao de 1 horas - Epoca Normal ";
        String expected6 = "EG - Dia: 4/1/2021 às 4:0 com duracao de 1 horas - Epoca Normal ";
        String expected7 = "EB - Dia: 5/1/2021 às 5:0 com duracao de 1 horas - Epoca Normal ";
        String expected8 = "DD - Dia: 6/1/2021 às 6:0 com duracao de 2 horas - Epoca Especial";
        String expected9 = "IA - Dia: 7/1/2021 às 7:0 com duracao de 1 horas - Epoca Normal ";
        String expected10 = "GO - Dia: 8/1/2021 às 8:0 com duracao de 3 horas - Epoca Especial";
        String expected11 = "EB - Dia: 9/1/2021 às 9:0 com duracao de 1 horas - Epoca Especial";
        String expected12 = "GG - Dia: 10/1/2021 às 14:0 com duracao de 1 horas - Epoca Especial";

        String input1 = "4";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().contains(expected2));
        assert(testOut.toString().contains(expected3));
        assert(testOut.toString().contains(expected4));
        assert(testOut.toString().contains(expected5));
        assert(testOut.toString().contains(expected6));
        assert(testOut.toString().contains(expected7));
        assert(testOut.toString().contains(expected8));
        assert(testOut.toString().contains(expected9));
        assert(testOut.toString().contains(expected10));
        assert(testOut.toString().contains(expected11));
        assert(testOut.toString().contains(expected12));
    }

    @Test
    @DisplayName("TCLEF10: listaExamesFunc Control Flow Path Loop 2")
    void TCLEF10() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?";
        String expected3 = "PA - Dia: 1/1/2021 às 1:0 com duracao de 1 horas - Epoca Normal ";
        String expected4 = "PA - Dia: 2/1/2021 às 1:0 com duracao de 1 horas - Epoca Normal ";
        String expected5 = "OA - Dia: 3/1/2021 às 3:0 com duracao de 1 horas - Epoca Normal ";
        String expected6 = "EG - Dia: 4/1/2021 às 4:0 com duracao de 1 horas - Epoca Normal ";
        String expected7 = "EB - Dia: 5/1/2021 às 5:0 com duracao de 1 horas - Epoca Normal ";
        String expected8 = "DD - Dia: 6/1/2021 às 6:0 com duracao de 2 horas - Epoca Especial";
        String expected9 = "IA - Dia: 7/1/2021 às 7:0 com duracao de 1 horas - Epoca Normal ";
        String expected10 = "GO - Dia: 8/1/2021 às 8:0 com duracao de 3 horas - Epoca Especial";
        String expected11 = "EB - Dia: 9/1/2021 às 9:0 com duracao de 1 horas - Epoca Especial";
        String expected12 = "GG - Dia: 10/1/2021 às 14:0 com duracao de 1 horas - Epoca Especial";

        String input1 = "18";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().contains(expected2));
        assert(testOut.toString().contains(expected3));
        assert(testOut.toString().contains(expected4));
        assert(testOut.toString().contains(expected5));
        assert(testOut.toString().contains(expected6));
        assert(testOut.toString().contains(expected7));
        assert(testOut.toString().contains(expected8));
        assert(testOut.toString().contains(expected9));
        assert(testOut.toString().contains(expected10));
        assert(testOut.toString().contains(expected11));
        assert(testOut.toString().contains(expected12));
    }

    @Test
    @DisplayName("TCLEF11: listaExamesFunc input1 Boundary Equivalence 1")
    void TCLEF11() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Numero de Funcionario invalido";
        String input1 = "-1";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF12: listaExamesFunc input1 Boundary Equivalence 2")
    void TCLEF12() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?(0-n/1-n):";
        String input1 = "0";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF13: listaExamesFunc input1 Boundary Equivalence 3")
    void TCLEF13() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?(0-n/1-n):";
        String input1 = "27";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF14: listaExamesFunc input1 Boundary Equivalence 4")
    void TCLEF14() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Numero de Funcionario invalido";
        String input1 = "28";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF15: listaExamesFunc input1 Invalid Equivalence 1")
    void TCLEF15() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Numero de Funcionario invalido";
        String input1 = "_1";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF16: listaExamesFunc input1 Invalid Equivalence 2")
    void TCLEF16() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Numero de Funcionario invalido";
        String input1 = "1s";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF17: listaExamesFunc input1 Invalid Equivalence 3")
    void TCLEF17() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Numero de Funcionario invalido";
        String input1 = null;
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF18: listaExamesFunc input2 Boundary Equivalence 1")
    void TCLEF18() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Opcao invalida";
        String input1 = "17";
        String input2 = "-1";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF19: listaExamesFunc input2 Boundary Equivalence 2")
    void TCLEF19() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?";
        String expected3 = "GG - Dia: 1/1/2021 às 1:0 com duracao de 1 horas - Epoca Normal ";
        String input1 = "17";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().contains(expected2));
        assert(testOut.toString().endsWith(expected3 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF20: listaExamesFunc input2 Boundary Equivalence 3")
    void TCLEF20() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?";
        String expected3 = "GG - Dia: 1/1/2021 às 1:0 com duracao de 1 horas - Epoca Normal ";
        String expectedFile1 = "GG-Dia:1/1/2021às1:0comduracaode1horas-EpocaNormal";
        String input1 = "17";
        String input2 = "1";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        String file = readFile();
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().contains(expected2));
        assert(testOut.toString().endsWith(expected3 + "\r\n"));
        assert(file.equals(expectedFile1));
    }

    @Test
    @DisplayName("TCLEF21: listaExamesFunc input2 Boundary Equivalence 4")
    void TCLEF21() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Opcao invalida";
        String input1 = "17";
        String input2 = "2";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF22: listaExamesFunc input2 Invalid Equivalence 1")
    void TCLEF22() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Opcao invalida";
        String input1 = "17";
        String input2 = "0-";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF23: listaExamesFunc input2 Invalid Equivalence 2")
    void TCLEF23() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Opcao invalida";
        String input1 = "17";
        String input2 = "no";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }

    @Test
    @DisplayName("TCLEF24: listaExamesFunc input2 Invalid Equivalence 3")
    void TCLEF24() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Opcao invalida";
        String input1 = "17";
        String input2 = null;

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.inicializa();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().endsWith(expected2 + "\r\n"));
    }
}