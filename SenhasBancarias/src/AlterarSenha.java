import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AlterarSenha {
    public void alterarSenha(Scanner in) {
        LimparTerminal cls= new LimparTerminal();
        boolean control= true, control1= true;
        int opc=0, opc2=0;
        String senha= null, novoCPF= null, prioridade1= null, prioridade2= null;
        File arquivo= new File("BDsenhas.txt"), arquivo2= new File("BDsenhas2.txt");

        while(control){
            System.out.println(""); 
            System.out.print("Informe a senha que deseja alterar: ");
            senha=in.nextLine();
            System.out.println("");
            System.out.println("──────────────────────────────");
            System.out.println("     ALTERAR TIPO DE SENHA    ");
            System.out.println("──────────────────────────────");
            System.out.println(" > [1] NC - Não sou cliente   ");
            System.out.println(" > [2] CL - Sou cliente       ");
            System.out.println(" > [3] PR - Preferencial      ");
            System.out.println("──────────────────────────────");
            System.out.println("");
            System.out.println("1º Selecione o tipo da senha que você quer;");
            System.out.println("2º Selecione para qual tipo você quer altera::");

            while(control){

                do { //Entrada com teste de preenchimento inteiro
                    try {
                        System.out.print(" > ");
                        opc= in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("");
                        System.out.println("Informe um valor válido!");
                        control1= false; }
                    in.nextLine();
                } while (!control1);
                
                switch (opc) {
                    case 1:
                        control=false;
                        prioridade1="NC";
                        break;
                    case 2:
                        novoCPF=null;
                        control=false;
                        prioridade1="CL";
                        break;
                    case 3:
                        control=false;
                        prioridade1="PR";
                        break;
                    default:
                        System.out.println("");
                        System.out.println("Opção inválida!");
                        break; }}
                control=true;

            while(control) {

                do { //Entrada com teste de preenchimento inteiro
                    try {
                        System.out.print(" > ");
                        opc2= in.nextInt();
                        control1= true;
                    } catch (InputMismatchException e) {
                        System.out.println("");
                        System.out.println("Informe um valor válido!");
                        control1= false; }
                    in.nextLine();
                } while (!control1);

                switch (opc2) {
                    case 1:
                        novoCPF=null;
                        control=false;
                        prioridade2="NC";
                        break;
                    case 2:
                        System.out.print("Informe o seu CPF/CNPJ: ");
                        novoCPF=in.nextLine();
                        control=false;
                        prioridade2="CL";
                        break;
                    case 3:
                        novoCPF=null;
                        control=false;    
                        prioridade2="PR";
                        break;
                    default:
                        System.out.println("");
                        System.out.println("Opção inválida!");
                        break; }}}

        cls.limpar_tela();

        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo));
        BufferedWriter escrever = new BufferedWriter(new FileWriter(arquivo2))){
        String linha; // variável para receber as linhas do arquivo
        boolean linha1cabecalho=true, linha2cabecalho=false;

            while ((linha = ler.readLine())!= null) {

                if (linha1cabecalho) {
                    escrever.write(linha);
                    escrever.newLine();
                    linha1cabecalho=false;
                    linha2cabecalho=true; }

                else if (linha2cabecalho) {
                    escrever.write(linha);
                    escrever.newLine();
                    linha2cabecalho=false; }

                else {
                    String[]campo = linha.split(", ");

                    if (campo[0].equals(senha)) {   
                        System.out.println("\nAlterção bem sucedida!");
                        System.out.println("Senha alterada de "+prioridade1+" para "+prioridade2);
                        String novasenha = linha.replaceFirst(prioridade1, prioridade2);

                        if (prioridade2.equals("CL")&& novoCPF != null) {
                            String novalinha = novasenha.replaceFirst(campo[1], novoCPF);
                            escrever.write(novalinha);
                            escrever.newLine(); }

                        else if (prioridade2.equals("NC")||prioridade2.equals("PR")) {
                            String novalinha = novasenha.replaceFirst(campo[1], "null");
                            escrever.write(novalinha);
                            escrever.newLine(); }

                        else{
                        escrever.write(novasenha);
                        escrever.newLine(); }}

                    else{
                        escrever.write(linha);
                        escrever.newLine(); }}}

        } catch (IOException e) {
            e.printStackTrace(); }

        if (arquivo.delete()) {
            arquivo2.renameTo(arquivo); }}}