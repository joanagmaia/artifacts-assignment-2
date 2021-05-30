package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("pedeData Test Suit")
class pedeData_TestSuit {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private String expectedString = "Dia: 01/06/2021 às 18:00 com duracao de 2 horas";
    private String invalidDate = "Inseriu uma data inválida, tente de novo";
    private String invalidTime = "Inseriu uma hora inválida, tente de novo";
    private String invalidDuration = "Inseriu uma duracao inválida, tente de novo";
    private String askDate = "Data (dd/mm/aaaa): ";
    private String askTime = "Hora (hh:mm) : ";
    private String askDuration = "Duracao(em horas) : ";

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
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(input);
        String expectedOutcome = geral.pedeData().toString();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[0], askDate);
        assertEquals(outputs[1], invalidDate);
        assertEquals(outputs[2], askDate);
        assertEquals(outputs[3], invalidDate);
        assertEquals(outputs[4], askDate);
        assertEquals(outputs[5], askTime);
        assertEquals(outputs[6], askDuration);
        assertEquals(expectedOutcome, expectedData.toString(), expectedString);
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
        String expectedOutcome = geral.pedeData().toString();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[0], askDate);
        assertEquals(outputs[1], askTime);
        assertEquals(outputs[2], invalidTime);
        assertEquals(outputs[3], askTime);
        assertEquals(outputs[4], askDuration);
        assertEquals(expectedOutcome, expectedData.toString(), expectedString);
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
        String expectedOutcome = geral.pedeData().toString();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[0], askDate);
        assertEquals(outputs[1], askTime);
        assertEquals(outputs[2], invalidTime);
        assertEquals(outputs[3], askTime);
        assertEquals(outputs[4], invalidTime);
        assertEquals(outputs[5], askTime);
        assertEquals(outputs[6], invalidTime);
        assertEquals(outputs[7], askTime);
        assertEquals(outputs[8], askDuration);
        assertEquals(expectedOutcome, expectedData.toString(), expectedString);
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
        String expectedOutcome = geral.pedeData().toString();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[0], askDate);
        assertEquals(outputs[1], askTime);
        assertEquals(outputs[2], askDuration);
        assertEquals(outputs[3], askDate);
        assertEquals(outputs[4], askTime);
        assertEquals(outputs[5], askDuration);
        assertEquals(expectedOutcome, expectedData.toString(), expectedString);
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
        String expectedOutcome = geral.pedeData().toString();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[0], askDate);
        assertEquals(outputs[1], askTime);
        assertEquals(outputs[2], askDuration);
        assertEquals(outputs[3], askDate);
        assertEquals(outputs[4], askTime);
        assertEquals(outputs[5], askDuration);
        assertEquals(outputs[6], askDate);
        assertEquals(outputs[7], askTime);
        assertEquals(outputs[8], askDuration);
        assertEquals(outputs[9], askDate);
        assertEquals(outputs[10], askTime);
        assertEquals(outputs[11], askDuration);
        assertEquals(expectedOutcome, expectedData.toString(), expectedString);
    }

    @Test
    @DisplayName("TCPD8: Test Case for value = null")
    void testInput1Partition1Boundary1() {
        String dataString = null;
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD9: Test Case for value = 11/11/1111")
    void testInput1Partition1Boundary2() {
        Integer dataString = 11/11/1111;
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD10: Test Case for value = \"11/11/2021\"")
    void testInput1Partition1Boundary3() {
        String dataString = "11/11/2021";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], askTime);
    }

    @Test
    @DisplayName("TCPD11: Test Case for value = \"11/11/20211\"")
    void testInput1Partition1Boundary4() {
        String dataString = "11/11/20211";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD12: Test Case for value = \"11/11\"")
    void testInput1Partition1Boundary5() {
        String dataString = "11/11";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD13: Test Case for value = \"11/11/2021\"")
    void testInput1Partition2Boundary1() {
        String dataString = "11/11/2021";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], askTime);
    }

    @Test
    @DisplayName("TCPD14: Test Case for value = \"00/00/0000\"")
    void testInput1Partition2Boundary2() {
        String dataString = "00/00/0000";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD15: Test Case for value = \"01/01/2021\"")
    void testInput1Partition2Boundary3() {
        String dataString = "01/01/2021";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], askTime);
    }

    @Test
    @DisplayName("TCPD16: Test Case for value = \"11111/2021\"")
    void testInput1Partition2Boundary4() {
        String dataString = "11111/2021";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD17: Test Case for value = \"11/1112021\"")
    void testInput1Partition2Boundary5() {
        String dataString = "11/1112021";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD18: Test Case for value = \"currentDate - 1\"")
    void testInput1Partition3Boundary1() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = formatter.format(new Date(System.currentTimeMillis()-24*60*60*1000));
        Geral geral = new Geral();

        systemOut.println(dataString);

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD19: Test Case for value = \"28/02/2021\"")
    void testInput1Partition3Boundary2() {
        String dataString = "28/02/2021";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], askTime);
    }

    @Test
    @DisplayName("TCPD20: Test Case for value = \"30/02/2021\"")
    void testInput1Partition3Boundary3() {
        String dataString = "30/02/2021";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD21: Test Case for value = \"31/04/2021\"")
    void testInput1Partition3Boundary4() {
        String dataString = "31/04/2021";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD22: Test Case for value = \"30/04/2021\"")
    void testInput1Partition3Boundary5() {
        String dataString = "30/04/2021";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], askTime);
    }

    @Test
    @DisplayName("TCPD23: Test Case for value = \"11/13/2021\"")
    void testInput1Partition3Boundary6() {
        String dataString = "11/13/2021";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD24: Test Case for value = \"11/00/2021\"")
    void testInput1Partition3Boundary7() {
        String dataString = "11/00/2021";
        Geral geral = new Geral();

        String inputs = dataString + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[1], invalidDate);
    }

    @Test
    @DisplayName("TCPD25: Test Case for value = \"null\"")
    void testInput2Partition1Boundary1() {
        String dataString = null;
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                dataString + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[2], invalidTime);
    }

    @Test
    @DisplayName("TCPD26: Test Case for value = \"11\"")
    void testInput2Partition1Boundary2() {
        String dataString = "11";
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                dataString + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "01/06/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[2], invalidTime);
    }

    @Test
    @DisplayName("TCPD27: Test Case for value = \"11:11\"")
    void testInput2Partition1Boundary3() {
        String dataString = "11:11";
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                dataString + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[2], askDuration);
    }

    @Test
    @DisplayName("TCPD28: Test Case for value = \"11:111\"")
    void testInput2Partition1Boundary4() {
        String dataString = "11:111";
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                dataString + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[2], invalidTime);
    }

    @Test
    @DisplayName("TCPD29: Test Case for value = \"00:00\"")
    void testInput2Partition2Boundary1() {
        String dataString = "00:00";
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                dataString + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[2], askDuration);
    }

    @Test
    @DisplayName("TCPD30: Test Case for value = \"00:01\"")
    void testInput2Partition2Boundary2() {
        String dataString = "00:01";
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                dataString + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[2], askDuration);
    }

    @Test
    @DisplayName("TCPD31: Test Case for value = \"11111\"")
    void testInput2Partition2Boundary3() {
        String dataString = "11111";
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                dataString + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[2], invalidTime);
    }

    @Test
    @DisplayName("TCPD32: Test Case for value = \"24:00\"")
    void testInput2Partition3Boundary1() {
        String dataString = "24:00";
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                dataString + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[2], invalidTime);
    }

    @Test
    @DisplayName("TCPD33: Test Case for value = \"23:60\"")
    void testInput2Partition3Boundary2() {
        String dataString = "23:60";
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                dataString + System.lineSeparator() +
                "2";
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        assertEquals(outputs[2], invalidTime);
    }

    @Test
    @DisplayName("TCPD34: Test Case for value = -1")
    void testInput3Partition1Boundary1() {
        Integer dataString = -1;
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                dataString;
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        if(outputs.length == 4)
            assertEquals(outputs[3], invalidDuration);
        else
            assert(false);
    }

    @Test
    @DisplayName("TCPD35: Test Case for value = 0")
    void testInput3Partition1Boundary2() {
        Integer dataString = 0;
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                "18:00" + System.lineSeparator() +
                dataString;
        provideInput(inputs);
        geral.pedeData();

        String[] outputs = testOut.toString().split(System.lineSeparator());

        if(outputs.length == 4)
            assertEquals(outputs[3], invalidDuration);
        else
            assert(false);
    }

    @Test
    @DisplayName("TCPD36: Test Case for value = 1")
    void testInput3Partition1Boundary3() {
        Integer dataString = 1;
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                "11:11" + System.lineSeparator() +
                dataString;
        provideInput(inputs);

        assertEquals(geral.pedeData(), "Dia: 11/11/2021 às 11:11 com duracao de 1 horas");
    }

    @Test
    @DisplayName("TCPD37: Test Case for value = 2147483647")
    void testInput3Partition1Boundary4() {
        Integer dataString = 2147483647;
        Geral geral = new Geral();

        String inputs = "11/11/2021" + System.lineSeparator() +
                "11:11" + System.lineSeparator() +
                dataString;
        provideInput(inputs);

        assertEquals(geral.pedeData(), "Dia: 11/11/2021 às 11:11 com duracao de 2147483647 horas");
    }
}