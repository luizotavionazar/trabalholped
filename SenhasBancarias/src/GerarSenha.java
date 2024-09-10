import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Queue;

public class GerarSenha {
    public int gerar_senha(Scanner in, int seque, Queue<String> fila){
        boolean control= true, control1= true;
        int opc= 0;
        long ident= 0;
        String prioridade= null;

        while (control) {
            System.out.println(""); //Visualização das opções de gerar senha
            System.out.println("──────────────────────────────");
            System.out.println("     SOLICITAR NOVA SENHA     ");
            System.out.println("──────────────────────────────");
            System.out.println(" > [1] NC - Não sou cliente   ");
            System.out.println(" > [2] CL - Sou cliente       ");
            System.out.println(" > [3] PR - Preferencial      ");
            System.out.println("──────────────────────────────");
            System.out.println("");
            System.out.print("Selecione o tipo da senha: ");

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

            switch (opc) { //Define as prioridades
                case 1:
                    prioridade= "NC";
                    control=false;
                    break;
                case 2:
                    prioridade= "CL"; //Cliente necessita de preencher CPF/CNPJ
                    
                    do { //Entrada com teste de preenchimento inteiro
                        System.out.print("Informe o CPF/CNPJ do titular da conta: ");
                        try {
                            ident= in.nextLong();
                            control1= true;
                        } catch (InputMismatchException e) {
                            System.out.println("");
                            System.out.println("Informe um valor válido!");
                            control1= false; }
                        in.nextLine();
                    } while (!control1);

                    control=false;
                    break;
                case 3:
                    prioridade= "PR";
                    control=false;
                    break;
                default:
                    System.out.println("");
                    System.out.println("Opção inválida!");
                    break;  }}
        seque++;

        LocalDateTime horario= LocalDateTime.now(); //Captura da Data e Hora
        DateTimeFormatter horario_format= DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss ");
        String hora= horario.format(horario_format);
        String senha= null, registro= null;

        if (seque<10) { //Adiciona 0 a esquerda quando a sequência possui 1 digito
            senha= prioridade+"0"+seque; }
        else {
            senha= prioridade+seque; }
 
        if (prioridade.equals("CL")) { //Montagem do registro da senha com todas as suas informações
            registro= senha+", "+ident+", "+hora+", 0"; }
        else {
            registro= senha+", null, "+hora+", 0"; }

        String caminhoArquivo= "BDsenhas.txt";
        try { //Escreve o registro no Banco de Dados
            FileWriter escreveArquivo = new FileWriter("BDsenhas.txt", true); //True para adicionar ao final do arquivo, não sobscrevendo os dados
            escreveArquivo.write(registro + "\n");
            escreveArquivo.close();

            try(BufferedReader ler = new BufferedReader(new FileReader(caminhoArquivo))) { //Varre as senhas gravadas no BD, para assim carregar a fila atual
            String linha= null, ultimaLinha = null, ultimoRegistro= null;
            char ultimoCaractere= 0;
            
                while((linha= ler.readLine()) != null){ //Verifica se a tupla esta vazia
                    ultimaLinha= linha;}
                if (ultimaLinha!= null) { //Captura ultimo caractere para verificar se a senha já foi chamada
                    ultimoCaractere= ultimaLinha.charAt(ultimaLinha.length()-1); }
                    
                if (ultimaLinha.matches("[A-Za-z]{2}\\d{2}, \\w+, \\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2} .*")) { //Captura da Data/Hora do último registro da Sacola
                    String[] campos = ultimaLinha.split(", "); //Divide a linha em campos, usando a vírgula como separador (0 = senha, 1 = CPF, 2 = data)
                    ultimoRegistro= campos[2]; }
                
                if (ultimoCaractere=='0') { //Adiciona na fila se a senha não foi chamad
                    fila.add(senha); }} //CORRIGIR PREENCHIMENTO DA FILA

            for (int i = 0; i < 20; i++) {
                System.out.println("");
                System.out.println(fila.peek());
                System.out.println("");
            }

            System.out.println("");
            System.out.println(" > Sucesso!");
            System.out.println(" > Sua senha é: "+senha);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar a senha!");
            System.out.print("Descrição: "); e.printStackTrace(); }
            
        return seque; }}