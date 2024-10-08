import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ListarSenha {
    public void listar_senha(Scanner in) {
        LimparTerminal cls= new LimparTerminal();
        String caminhoArquivo= "BDsenhas.txt";
        boolean control= true, control1= true;
        int opc= 0, numerolinha=0;

        while (control) {
            System.out.println(""); //Visualização das opções de gerar senha
            System.out.println("──────────────────────────────────");
            System.out.println("        LISTAGEM DE SENHAS        ");
            System.out.println("──────────────────────────────────");
            System.out.println(" > [1] Senhas não chamadas        ");
            System.out.println(" > [2] Senhas chamadas            ");
            System.out.println(" > [3] Todas as senhas geradas    ");
            System.out.println(" > [0] Retornar ao menu principal ");
            System.out.println("──────────────────────────────────");
            System.out.println("");
            System.out.print("Selecione o tipo de listagem: ");
        
            do { //Entrada com teste de preenchimento inteiro
                    try {
                        opc= in.nextInt();
                        control1= true;
                    } catch (InputMismatchException e) {
                        System.out.println("");
                        System.out.println("Informe um valor válido!");
                        control1= false; }
                in.nextLine();
                } while (!control1);
            
            cls.limpar_tela();

            switch (opc) {
                case 0:
                    control= false;
                    cls.limpar_tela();
                    break;
                case 1:
                    System.out.println("");
                    System.out.println("Senhas não chamadas:");
                    System.out.println("");

                    try (BufferedReader ler = new BufferedReader(new FileReader(caminhoArquivo))){
                        String linha;
                        
                        while((linha= ler.readLine()) != null){
                            linha=linha.trim();
                            String[]campo=linha.split(",");
                            if(campo[campo.length - 1 ].trim().equals("0")){
                                System.out.println(campo[0].trim()); }}
                            
                    } catch (IOException e) {
                        e.printStackTrace(); }

                    break;
                case 2:
                    System.out.println("");
                    System.out.println("Senhas chamadas: ");
                    System.out.println("");
                    try (BufferedReader ler= new BufferedReader(new FileReader(caminhoArquivo))){
                        String linha;
                        
                        while((linha= ler.readLine()) != null){
                            linha=linha.trim();
                            String[]campo=linha.split(",");
                        
                            if(campo[campo.length- 1 ].trim().equals("1")){
                                System.out.println(campo[0].trim()); }}
                            
                    } catch (IOException e) {
                        e.printStackTrace(); }
                    break;
                case 3:
                    System.out.println("");
                    System.out.println("Todas as senhas geradas:");
                    System.out.println("");
                    try (BufferedReader ler= new BufferedReader(new FileReader(caminhoArquivo))) {
                        String linha;
                        
                        while((linha= ler.readLine()) != null) {
                            numerolinha++;
                        
                            if (numerolinha<=2) {
                                continue; }
                            
                            linha=linha.trim();
                            String[]campo=linha.split(",");
                            System.out.println(campo[0].trim()); }
                            
                    } catch (IOException e) {
                        e.printStackTrace(); }
                    break;
                default:
                    break; }}}}