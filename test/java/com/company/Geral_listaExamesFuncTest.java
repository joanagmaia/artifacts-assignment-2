package com.company;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import sun.awt.windows.WPrinterJob;

import static org.junit.jupiter.api.Assertions.*;

class Geral_listaExamesFuncTest {

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

    @Test
    void listaExamesFuncA() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?(0-n/1-n):";
        String input1 = "17";
        String input2 = "0";

        provideInput(input1);

        Geral geral = new Geral();
        geral.listaExamesFunc();

        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().contains(expected2));
        assert(false);

    }
    @Test
    void listaExamesFuncB() {
        String expected1 = "Num do Funcionario :"; //this is start of expected1 string
        String expected2 = "Deseja que o resultado seja escrito num ficheiro?(0-n/1-n):";
        String input1 = "17";
        String input2 = "0";

        String input = input1 + System.lineSeparator() + input2;
        provideInput(input);

        Geral geral = new Geral();
        geral.listaExamesFunc();

        systemOut.println(testOut);
        systemOut.println("teste:");
        assert(testOut.toString().contains(expected1));
        assert(testOut.toString().contains(expected2));

    }
}