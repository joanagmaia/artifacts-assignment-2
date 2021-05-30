package com.company.testCases;

import com.company.Data;
import com.company.Geral;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("pedeData Control Flow Test")
public class Geral_pedeData_controlFlowTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private String expectedString = "Dia: 01/06/2021 às 18:00 com duracao de 2 horas";

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
    @DisplayName("TCPD1: Test Case for PPD1")
    void testIndependentPath1() {
        Data expectedData = new Data(18.0f, 1, 6, 2021, 2, "18:00");
        Geral geral = new Geral();

        String input = "01/06/2021" + System.lineSeparator() +
                       "18:00" + System.lineSeparator() +
                       "2";
        provideInput(input);

        // The pedeData method should return a Data with the expected values
        assertEquals(geral.pedeData().toString(), expectedData.toString(), expectedString);
    }

    @Test
    @DisplayName("TCPD2: Test Case for PPD2")
    void testIndependentPath2() {
        Data expectedData = new Data(18.0f, 1, 6, 2021, 2, "18:00");
        Geral geral = new Geral();

        String input = "01/062021" + System.lineSeparator() +
                       "01/06/2021" + System.lineSeparator() +
                       "1800" + System.lineSeparator() +
                       "18:00" + System.lineSeparator() +
                       "2";
        provideInput(input);

        // The pedeData method should return a Data with the expected values
        assertEquals(geral.pedeData().toString(), expectedData.toString(), expectedString);
    }

    @Test
    @DisplayName("TCPD3: Test Case for PPD3")
    void testLoopPath1() {
        Data expectedData = new Data(18.0f, 1, 6, 2021, 2, "18:00");
        Geral geral = new Geral();

        String input = "01/062021" + System.lineSeparator() +
                "0106/2021" + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "1800" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(input);

        // The pedeData method should return a Data with the expected values
        assertEquals(geral.pedeData().toString(), expectedData.toString(), expectedString);
    }

    @Test
    @DisplayName("TCPD4: Test Case for PPD4")
    void testLoopPath2() {
        Data expectedData = new Data(18.0f, 1, 6, 2021, 2, "18:00");
        Geral geral = new Geral();

        String input = "01/06/2021" + System.lineSeparator() +
                "1800" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(input);

        // The pedeData method should return a Data with the expected values
        assertEquals(geral.pedeData().toString(), expectedData.toString(), expectedString);
    }

    @Test
    @DisplayName("TCPD5: Test Case for PPD5")
    void testLoopPath3() {
        Data expectedData = new Data(18.0f, 1, 6, 2021, 2, "18:00");
        Geral geral = new Geral();

        String input = "01/06/2021" + System.lineSeparator() +
                "1800" + System.lineSeparator() +
                "1900" + System.lineSeparator() +
                "2000" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(input);

        // The pedeData method should return a Data with the expected values
        assertEquals(geral.pedeData().toString(), expectedData.toString(), expectedString);
    }

    @Test
    @DisplayName("TCPD6: Test Case for PPD6")
    void testLoopPath4() {
        Data expectedData = new Data(18.0f, 1, 6, 2021, 2, "18:00");
        Geral geral = new Geral();

        String input = "40/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(input);

        // The pedeData method should return a Data with the expected values
        assertEquals(geral.pedeData().toString(), expectedData.toString(), expectedString);
    }

    @Test
    @DisplayName("TCPD7: Test Case for PPD7")
    void testLoopPath5() {
        Data expectedData = new Data(18.0f, 1, 6, 2021, 2, "18:00");
        Geral geral = new Geral();

        String input = "40/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "00/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "00/00/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(input);

        // The pedeData method should return a Data with the expected values
        assertEquals(geral.pedeData().toString(), expectedData.toString(), expectedString);
    }
}