package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Geral_pedeData {

    @Mock
    Exame exame;

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
    void testPedeDataCorrectValues() {
        Data expectedData = new Data(18.0f, 1, 6, 2021, 2, "18:00");
        Geral geral = new Geral();

        String input = "01/06/2021" + System.lineSeparator() + "18:00" + System.lineSeparator() + "2";
        provideInput(input);

        // The pedeData method should return a Data with the expected values
        assertEquals(geral.pedeData().toString(), expectedData.toString());
    }

    @Test
    void testPedeDataIncorrectValues() {
        Data expectedData = new Data(18.0f, 1, 6, 2021, 2, "18:00");
        Geral geral = new Geral();

        String input = "01/062021" + System.lineSeparator() +
                       "01/06/2021" + System.lineSeparator() +
                       "1800" + System.lineSeparator() +
                       "18:00" + System.lineSeparator() +
                       "2";
        provideInput(input);

        // The pedeData method should return a Data with the expected values
        assertEquals(geral.pedeData().toString(), expectedData.toString());
    }
}