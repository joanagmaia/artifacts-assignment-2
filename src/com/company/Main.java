package com.company;
import java.util. *;

public class Main {

    public static void main(String[] args) {
        Geral ne = new Geral();
        ne.inicializa();
        System.out.println();
        Scanner sc = new Scanner(System.in);
        int escolha;
        while (true) {
            try {
                System.out.println("\t\t---Menu---\n");
                System.out.println("1-> Criar exame");
                System.out.println("2-> Alterar exame");
                System.out.println("3-> Listar exames");
                System.out.println("4-> Listar alunos e respetivas notas de um exame");
                System.out.println("5-> Listar notas de um aluno em todos os exames inscrito");
                System.out.println("6-> Listar exames de um vigilante");
                System.out.println("7-> Lancar notas de um exame");
                System.out.println("8-> Sair");
                System.out.println("9-> Guardar");
                System.out.print("Opção: ");
                escolha = sc.nextInt();
                if (escolha < 1 || escolha > 9) {
                    System.out.println("\t\tOpção inválida\n");
                }

                if (escolha == 1) {
                    ne.addExame();
                }
                if (escolha == 2) {
                    ne.alteraExame();
                }
                if (escolha == 3) {
                    ne.listarExames();
                }
                if (escolha == 4) {
                    ne.listarNotasExame();
                }
                if (escolha == 5) {
                    ne.listaexamesAluno();
                }
                if (escolha == 6) {
                    ne.listaExamesFunc();
                }
                if (escolha == 7) {
                    ne.lancarNotas();
                }
                if (escolha == 8) {
                    break;
                }
                if (escolha == 9) {
                    ne.atualiza();
                }
            }catch (Exception e){
                System.out.println("Erro "+ e);
            }
        }
        ne.atualiza();
    }
}


