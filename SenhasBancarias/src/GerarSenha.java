import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.InputMismatchException;

public class GerarSenha {
    public int gerar_senha(Scanner in, int seque){
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

        try { //Escreve o registro no Banco de Dados
            FileWriter escreveArquivo = new FileWriter("BDsenhas.txt", true); //True para adicionar ao final do arquivo, não sobscrevendo os dados
            escreveArquivo.write(registro + "\n");
            escreveArquivo.close();

            System.out.println("");
            System.out.println(" > Sucesso!");
            System.out.println(" > Sua senha é: "+senha);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar a senha!");
            System.out.print("Descrição: "); e.printStackTrace(); }
            
        return seque; }}