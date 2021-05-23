package com.company;

import java.util.*;
import java.io.*;


public class Geral implements Serializable{
    private ArrayList<Exame> exames = new ArrayList<>();
    private ArrayList<Curso> cursos = new ArrayList<Curso>();
    private ArrayList<Aluno> alunos = new ArrayList<>();
    private ArrayList<Sala> salas = new ArrayList<Sala>();
    private ArrayList<Docente> docentes = new ArrayList<Docente>();
    private ArrayList<Funcionario> funcs = new ArrayList<>();
    public Geral(){
    }

    public ArrayList<Aluno> getAlunos(){return this.alunos;}
    public ArrayList<Exame> getExames(){return this.exames;}
    public ArrayList<Curso> getCursos(){return this.cursos;}
    public ArrayList<Sala> getSalas() {return this.salas;}
    public ArrayList<Docente> getDocentes(){return this.docentes;}
    public ArrayList<Funcionario> getFuncs(){return this.funcs;}

    /**
     *Metodo que guarda as mudanças executadas nas variaveis em memoria para o ficheiro de dados
     */
    public void atualiza(){
        try {
            Ficheiro dados = new Ficheiro();
            File f = new File("dados.obj");
            f.createNewFile();
            dados.abreEscrita("dados.obj");
            dados.escreveObjecto(this.getDocentes());
            dados.escreveObjecto(this.getFuncs());
            dados.escreveObjecto(this.getCursos());
            dados.escreveObjecto(this.getAlunos());
            dados.escreveObjecto(this.getSalas());
            dados.escreveObjecto(this.getExames());
            dados.fechaEscrita();
        } catch (Exception e) {
            System.out.println("Erro "+e);
        }

    }

    /**
     * Metodo para carregar os dados guardados do ficheiro de dados para a memoria
     */

    public void inicializa(){
        try {
            Ficheiro dados = new Ficheiro();
            dados.abreLeitura("dados.obj");
            this.docentes = (ArrayList<Docente>) dados.leObjecto();
            this.funcs = (ArrayList<Funcionario>) dados.leObjecto();
            this.cursos = (ArrayList<Curso>) dados.leObjecto();
            this.alunos = (ArrayList<Aluno>) dados.leObjecto();
            this.salas = (ArrayList<Sala>) dados.leObjecto();
            this.exames = (ArrayList<Exame>) dados.leObjecto();

            dados.fechaLeitura();
        }catch (Exception e){
            System.out.println("Erro "+e);
        }
    }


    /**
     * Procura uma disciplina tendo em conta o nome, e o curso em que esta se encontra e retorna a disciplina
     * @param nome
     * @param curso
     * @return disciplina
     */
    public Disciplina procuraDisciplina(String nome,Curso curso){
        ArrayList<Disciplina> disciplinas = curso.getDisciplinas();
        for(Disciplina disciplina:disciplinas){
            if(disciplina.getNome().equals(nome)){
                return disciplina;
            }
        }
        return null;
    }

    /**
     * Procura e devolve um Curso baseado no nome
     * @param nome
     * @return
     */
    public Curso procuraCurso(String nome){
        for(Curso curso: cursos){
            if(nome.equals(curso.getNome()))
                return curso;
        }
        return null;
    }

    /**
     * Compara duas datas e devolve true se forem iguais, false se forem diferentes
     * @param d1
     * @param d2
     * @return
     */
    boolean comparaData(Data d1,Data d2){
        if(d1.getAno()==d2.getAno() && d1.getMes() == d2.getMes() &&d1.getDia() ==d2.getDia() && d1.getHora() == d2.getHora())
            return true;
        else
            return false;
    }

    /**
     * pede ao Utilizador input parametros para gerar uma data
     * @return
     */
    public Data pedeData(){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        String[] valsDia,valsHora;
        Data data;
        String dataString;
        while(true) {
            while (true) {
                System.out.println("Data (dd/mm/aaaa): ");
                dataString = reader.next();
                valsDia = dataString.split("/");
                if (valsDia.length == 3) {
                    break;
                }
                System.out.println("Inseriu uma data inválida, tente de novo");
            }
            int dia = Integer.parseInt(valsDia[0]);
            int mes = Integer.parseInt(valsDia[1]);
            int ano = Integer.parseInt(valsDia[2]);
            while (true) {
                System.out.println("Hora (hh:mm) : ");
                dataString = reader.next();
                valsHora = dataString.split(":");
                if (valsHora.length == 2) {
                    break;
                }
                System.out.println("Inseriu uma hora inválida, tente de novo");
            }
            System.out.println("Duracao(em horas) : ");
            int duracao = reader.nextInt();
            float hora,aux1;
            int aux = Integer.parseInt(valsHora[0]);
            hora = aux;
            aux = Integer.parseInt(valsHora[1]);
            aux1 = (aux/60);
            hora = hora + (aux1);
            data = new Data(hora, dia, mes, ano, duracao,dataString);
            if (data.verifica_data(data))
                return data;
        }
    }

    /**
     * Baseado no nome da sala procura e devolve a sala chamada
     * @param nome
     * @return
     */
    public Sala procuraSala(String nome){
        for(Sala sala:salas){
            if(sala.getNomeSala().equals(nome)){
                return sala;
            }
        }
        return null;
    }

    /**
     * Este metodo adiciona um exame à lista de exames da classe Geral
     */
    public void addExame() {
        ArrayList<Integer> vigilantes_no_exame = new ArrayList<>();
        ArrayList<Sala> salas1 = new ArrayList<>();
        Docente docente;
        Data data;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        Disciplina disciplina;
        ArrayList<Nota> notas = null;
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("1 - Epoca Normal,2 - Epoca Especial: ");
        int epoca = reader.nextInt();
        System.out.println(this.getCursos());
        System.out.println("Curso do exame: ");
        String cursoNome = reader.next();
        Curso curso = procuraCurso(cursoNome);
        if (curso == null) {
            System.out.println("Curso não existe");
            return;
        }
        System.out.println(curso.getDisciplinas());
        System.out.println("Disciplina do exame: ");
        String discNome = reader.next();
        disciplina = procuraDisciplina(discNome, curso);
        if (disciplina == null) {
            System.out.println("Disciplina não existe");
            return;
        }
        data = pedeData();
        int i;
        for(i= 0;i<this.getDocentes().size();++i) {
            String str = String.format("%d - %s",i+1,this.getDocentes().get(i));
            System.out.println(str);
        }
        while (true) {
            System.out.println("Docente responsavel do exame: ");
            int num = reader.nextInt();
            if (num < 0 && num >= this.getDocentes().size()) {
                System.out.println("Numero do docente fora dos limites");
            }
            docente = this.getDocentes().get(num-1);
            if (docente == null) {
                System.out.println("Docente não existe");
                return;
            }
            if (docente.getExames() != null) {
                boolean sobreposto = false;
                for (Exame exame : docente.getExames()) {
                    if (exame.getData().verificaSobreposicao(data)) {
                        sobreposto = true;
                        break;
                    }
                }
                if(!sobreposto){
                    break;
                }else
                    System.out.println("Ja existe um exame nessa hora para esse docente");

            }else{
                break;
            }
        }
        for(i=0;i<this.getFuncs().size();i++){
            System.out.printf("%d - %s\n",i+1,this.getFuncs().get(i));
        }
        while (true) {
            boolean sobreposto = false;
            System.out.println("Adicione vigilante para o exame (0 para parar): ");
            int funcN = reader.nextInt();
            if (funcN == 0){
                break;
            }

            if (funcN<0 || funcN >= this.getFuncs().size())
                System.out.println("Opcao invalida");
            Funcionario funcionario = this.getFuncs().get(funcN-1);
            if (funcionario == null) {
                System.out.println("Docente não existe");
            }if (vigilantes_no_exame.contains(funcN)) {
                System.out.println("Vigilante já convocado para este exame");
            }
            if (!(vigilantes_no_exame.contains(funcN))) {
                vigilantes_no_exame.add(funcN);
            }
            if (funcionario.exames != null) { //VERIFICA A DISPONIBILIDADE
                for (Exame aux : funcionario.exames) {
                    if (aux.getData().verificaSobreposicao(data)) {
                        sobreposto = true;
                        System.out.println("Professor ocupado neste horario");
                        sobreposto = true;
                    }
                }
                if(!sobreposto)
                    break;
            }
            if (!sobreposto )
                funcionarios.add(funcionario);
        }
        System.out.println(salas);
        Sala sala = new Sala();
        while (true) {
            System.out.println("Adicione Sala para o exame (stop para parar): ");
            String salaN = reader.next();
            if (salaN.equals("stop"))
                break;
            sala = procuraSala(salaN);
            if (sala == null) {
                System.out.println("Sala não existe");
                return;
            }
            boolean sobreposto = false;
            if(salas1.contains(sala)){
                System.out.println("Sala já no exame");
                sobreposto = true;
            }
            for (Exame aux : sala.getExames()) {
                if (aux.getData().verificaSobreposicao(data)) {
                    sobreposto = true;
                    System.out.println("Sala ocupada nesse horario");
                    break;
                }
            }
            if (!sobreposto)
                salas1.add(sala);
        }//verifica disponibilidade
        if (epoca == 1) {
            ExameNormal exame = new ExameNormal(disciplina, docente, salas1, data, notas, funcionarios);
            this.getExames().add(exame);
            disciplina.getExames().add(exame);
            docente.getExames().add(exame);
            sala.getExames().add(exame);
        }else {
            ExameEspecial exame = new ExameEspecial(disciplina, docente, salas1, data, notas, funcionarios);
            this.getExames().add(exame);
            disciplina.getExames().add(exame);
            docente.getExames().add(exame);
            sala.getExames().add(exame);
        }
    }


    /**
     * Dado um exame este metodo vai pedir os alunos inscritos na disciplina do exame que devem ser inscritos neste exame
     * @param exame
     */
    public void inscreverAlunos(Exame exame){
        Scanner reader= new Scanner(System.in);
        for(int i = 0;i<exame.getDisciplina().getInscritos().size();i++){
            System.out.printf("%d - %s\n",i+1,exame.getDisciplina().getInscritos().get(i));
        }
        while (true) {
            System.out.println("Aluno (0 para parar):");
            int count = 0;
            int num = reader.nextInt();
            if (num ==0)
                break;
            Aluno aluno = exame.getDisciplina().getInscritos().get(num-1);
            if(exame.getExclusividade()) {
                if (aluno.getAno() >= aluno.getCurso().getDuracao() || aluno.getEstatuto().equals("Normal") == false && exame.getDisciplina().getInscritos().contains(aluno)) {
                    Nota nota = new Nota(aluno);
                    exame.getNotas().add(nota);
                } else {
                    System.out.println("Aluno não eligivel");
                }
            }else{
                Nota nota = new Nota(aluno);
                exame.getNotas().add(nota);
            }

        }

    }

    /**
     *Lista todos os exames existentes ou entao os exames relativos a uma disciplina
     */
    public void listarExames(){
        try (PrintWriter out = new PrintWriter("resultados.txt")) {
            Disciplina disciplina;
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            while (true) {
                System.out.println("1 - Listar todos os exames\n2 - Listar exames de uma disciplina");
                int escolha = reader.nextInt();
                if (escolha == 1) {
                    if (exames == null) {
                        System.out.println("Nada a mostrar");
                    } else {
                        for (Exame exame : exames) {
                            System.out.println(exame);
                        }
                        break;
                    }
                } else if (escolha == 2) {
                    System.out.println("Curso :");
                    String cursoNome = reader.next();
                    Curso curso = procuraCurso(cursoNome);
                    if (curso == null) {
                        System.out.println("Não existe esse curso...");
                        break;
                    }
                    System.out.println("Disciplina :");
                    String discNome = reader.next();
                    disciplina = procuraDisciplina(discNome, curso);
                    if (disciplina == null) {
                        System.out.println("Não existe essa disciplina...");
                        break;
                    }
                    for (Exame exame : disciplina.exames) {
                        System.out.println(exame);
                    }
                    break;

                } else {
                    System.out.println("Opção invalida, tente de novo");
                }
            }
            while (true) {
                System.out.println("Deseja que os ficheiros sejam listados? (s ou n)");
                String escolha = reader.next();
                if (escolha.equals("s")) {
                    //TODO
                    break;
                }
                if (escolha.equals("n") == false && escolha.equals("s") == false) {
                    System.out.println("Opção invalida, tente de novo");
                }
                if (escolha.equals("n"))
                    break;
            }
            return;
        }catch (Exception e){
            System.out.println("Erro a abrir ficheiro");
        }
    }

    /**
     * Metodo de lançar notas de um exame para todos os alunos inscritos no exame
     */
    public void lancarNotas() {
        Scanner reader = new Scanner(System.in);
        Exame exame = pedeExame();
        if(exame != null) {
            ArrayList<Nota> notas = exame.getNotas();
            if (notas != null) {
                for (Nota nota : notas) {
                    String str = String.format("%s - Nota :", nota.getAluno().getNome());
                    System.out.println();
                    float valor = reader.nextFloat();
                    nota.atribuiNota(valor);
                    nota.getAluno().getExames().add(exame);
                }
                exame.marcarCorrigido();
            } else {
                System.out.println("Não ha alunos inscritos nesse exame");
            }
        }
    }

    /**
     * Verifica se as notas de um exame ja foram atribuidas e caso ja tenham sido atribuidas mostra-as com a possibilidade de as listar em ficheiro de texto
     */
    /*
    * SELECTED*/
    public void listarNotasExame() {
        try (PrintWriter out = new PrintWriter("resultados.txt")) {
            Exame exame = pedeExame();
            if (exame != null) {
                if (exame.getCorrigido()) {
                    for (Nota nota : exame.getNotas()) {
                        System.out.println(nota);
                    }
                } else
                    System.out.println("Notas deste exame ainda não atribuidas");
            }
        }catch (Exception e){
            System.out.println("Erro a abrir ficheiros");
        }
    }

    /**
     * Baseado em input do utilizador devolve um Exame
     * @return
     */
    public Exame pedeExame() {
        int i;
        Scanner reader = new Scanner(System.in);// Reading from System.in
        System.out.println(cursos);
        System.out.println("Curso :");
        String cursoNome = reader.next();
        Curso curso = procuraCurso(cursoNome);
        System.out.println(curso.getDisciplinas());
        System.out.println("Disciplina :");
        String discNome = reader.next();
        Disciplina disciplina = procuraDisciplina(discNome, curso);
        if(disciplina.getExames().size()!=0) {
            for (i = 0; i < disciplina.exames.size(); i++) {
                System.out.printf("%d - %s \n", i, disciplina.getExames().get(i).getData());
            }
            System.out.print("Opcao :");
            int exNum = reader.nextInt();
            return disciplina.getExames().get(exNum);
        }else{
            System.out.println("Nao ha exames dessa disciplina");
            return null;
        }
    }

    /**
     * Lista os vigilantes de um exame com a possibilidade de as listasr num ficheiro de texto
     */
    public void ListarFuncsExame(){
        Exame exame = pedeExame();
        if(exame!=null) {
            System.out.println(exame.getResponsavel());
            for (Funcionario vigilante : exame.getVigilantes()) {
                System.out.println(vigilante);
            }
        }
    }

    /**
     * Lista os exames em que um aluno esta inscrito e pode devolver um ficheiro de texto com os resultados
     * SELECTED
     */
    public void listaexamesAluno(){
        try (PrintWriter out = new PrintWriter("resultados.txt")) {
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            for (int i = 0; i < this.getAlunos().size(); i++) {
                System.out.printf("%d - %s\n", i, this.getAlunos().get(i));
            }
            int num = reader.nextInt();
            Aluno aluno = alunos.get(num);
            if (aluno.getExames() != null) {
                for (Exame exame : aluno.getExames()) {
                    System.out.print(exame);
                    if (exame.getCorrigido()) {
                        for (Nota nota : exame.getNotas()) {
                            if (nota.getAluno().equals(aluno)) {
                                System.out.printf(" - %d", nota.getNota());
                            }
                        }
                    } else
                        System.out.println("Ainda não corrigido");
                }
            } else
                System.out.println("Nao ha exames para este aluno");
        }catch (Exception e){
            System.out.println("Erro a abrir ficheiro");
        }
    }

    /**
     * lista os exames em que um funcionario esta convocado e pode devolver um ficheiro de texto com os resultados
     */
    public void listaExamesFunc(){
        try(PrintWriter out = new PrintWriter( "resultados.txt" )) {
            Scanner reader = new Scanner(System.in);
            int i;
            for (i = 0; i < this.getFuncs().size(); i++) {
                System.out.printf("%d - %s\n", i, this.getFuncs().get(i));
            }
            System.out.println("Num do Funcionario :");
            int num = reader.nextInt();
            System.out.println("Deseja que o resultado seja escrito num ficheiro?(0-n/1-n):");
            int write = reader.nextInt();
            Funcionario func = this.getFuncs().get(num);
            if (func.getExames() == null) {
                for (Exame exame : exames) {
                    if (exame.getVigilantes().contains(func))
                        System.out.println(exame);
                    if (write == 1)
                        out.println(exame);
                }
            } else {
                for (Exame exame : func.getExames()) {
                    System.out.println(exame);
                    if (write == 1)
                        out.println(exame);
                }
            }
        }catch (Exception e){
            System.out.println("Erro ao abrir o ficheiro");
        }
    }

    /**
     * Esta funçao permite alterar varios parametros de um exame, como os vigilantes, as salas e os alunos inscritos
     */
    public void alteraExame(){
        Exame exame = pedeExame();
        if(exame != null) {
            Scanner sc = new Scanner(System.in);
            int escolha;
            while (true) {
                System.out.println("\t\t---O que pretende alterar---\n");
                System.out.println("1-> Vigilantes\n");
                System.out.println("2-> Salas\n");
                System.out.println("3-> Inscrever aluno\nOpção: ");
                System.out.println("0-> Sair");
                escolha = sc.nextInt();
                if (escolha == 1) {
                    while (true) {
                        System.out.println("\t\t---Vigilantes---\n");
                        System.out.println("1-> Adicionar vigilante");
                        System.out.println("2-> Remover vigilante\nOpção:");
                        escolha = sc.nextInt();
                        if (escolha == 1) {
                            for (int i = 0; i < this.getFuncs().size(); i++) {
                                System.out.printf("%d - %s \n", i, this.getFuncs().get(i));
                            }
                            while (true) {
                                System.out.println("Numero (0 para parar):");
                                int num = sc.nextInt();
                                if (num == 0)
                                    break;
                                Funcionario func = this.getFuncs().get(num);
                                if (!exame.getVigilantes().contains(func))
                                    exame.getVigilantes().add(func);
                                else
                                    System.out.println("Ja esta convocado para este exame");
                            }
                        }
                        if (escolha == 2) {
                            for (int i = 0; i < this.getFuncs().size(); i++) {
                                System.out.printf("%d - %s \n", i, this.getFuncs().get(i));
                            }
                            while (true) {
                                System.out.println("Numero (0 para parar):");
                                int num = sc.nextInt();
                                if (num == 0)
                                    break;
                                Funcionario func = this.getFuncs().get(num);
                                if (exame.getVigilantes().contains(func))
                                    exame.getVigilantes().remove(func);
                                else
                                    System.out.println("Nao esta convocado para este exame");
                            }
                        }
                        if (escolha != 1 || escolha != 2) {
                            System.out.println("\t\tOpção inválida\n");
                        } else
                            break;
                    }
                }
                if (escolha == 2) {
                    while (true) {
                        System.out.println("\t\t---Salas---");
                        System.out.println("1-> Acrescentar sala:");
                        System.out.println("2-> Remover sala\nOpção:");
                        escolha = sc.nextInt();
                        if (escolha == 1) {
                            System.out.println("Sala:");
                            String nome = sc.next();
                            Sala sala = procuraSala(nome);
                            exame.getSalas().add(sala);

                        }
                        if (escolha == 2) {
                            System.out.println("Sala:");
                            String nome = sc.next();
                            Sala sala = procuraSala(nome);
                            exame.getSalas().remove(sala);
                        }
                        if (escolha != 1 && escolha != 2) {
                            System.out.println("Opcao invalida\n");
                        }
                    }
                }
                if (escolha == 3) {
                    this.inscreverAlunos(exame);
                }
                if (escolha != 1 || escolha != 2 || escolha != 3) {
                    System.out.println("Opcao invalida\n");
                }
                if (escolha == 0)
                    break;
            }
        }
    }

}

