package com.company;

import java.io.*;


public class Ficheiro {

    private ObjectInputStream iS;
    private ObjectOutputStream oS;

    /**
     * Abre ficheiro para leitura
     * @param nomeDoFicheiro
     * @return
     */
    public boolean abreLeitura(String nomeDoFicheiro) {
        try {
            iS = new ObjectInputStream(new FileInputStream(nomeDoFicheiro));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Abre ficheiro para escrita
     * @param nomeDoFicheiro
     * @throws IOException
     */
    public void abreEscrita(String nomeDoFicheiro) throws IOException {
        oS = new ObjectOutputStream(new FileOutputStream(nomeDoFicheiro));
    }

    /**
     * Le um objeto do ficheiro
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object leObjecto() throws IOException, ClassNotFoundException {
        return iS.readObject();
    }

    /**
     * Escreve um objeto no ficheiro
     * @param o
     * @throws IOException
     */
    public void escreveObjecto(Object o) throws IOException {
        oS.writeObject(o);
    }

    /**
     * Fecha ficheiro de leitura
     * @throws IOException
     */

    public void fechaLeitura() throws IOException {
        iS.close();
    }

    /**
     * Fecha ficheiro de escrita
     * @throws IOException
     */

    public void fechaEscrita() throws IOException {
        oS.close();
    }
}
