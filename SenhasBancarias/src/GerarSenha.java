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
        String prioridade= null;

        while (control) {
            System.out.println("");
            System.out.println("──────────────────────────────");
            System.out.println("     SOLICITAR NOVA SENHA     ");
            System.out.println("──────────────────────────────");
            System.out.println(" > [1] NC - Não sou cliente   ");
            System.out.println(" > [2] CL - Sou cliente       ");
            System.out.println(" > [3] PR - Preferencial      ");
            System.out.println("──────────────────────────────");
            System.out.println("");
            System.out.print("Selecione o tipo da senha: ");

            do {
                try {
                    opc= in.nextInt();
                    control1= true;
                } catch (InputMismatchException e) {
                    System.out.println("");
                    System.out.println("Informe um valor válido!");
                    control1= false; }
                in.nextLine();
                
            } while (!control1);
                switch (opc) {
                    case 1:
                        prioridade= "NC";
                        control=false;
                        break;
                    case 2:
                        prioridade= "CL";
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

        LocalDateTime horario= LocalDateTime.now();
        DateTimeFormatter horario_format= DateTimeFormatter.ofPattern("dd/MM/YYYY - HH:mm:ss ");
        String hora= horario.format(horario_format);
        String senha= null;

        if (seque<10) {
            senha= prioridade + "0" + seque; }
        else {
            senha= prioridade + seque;}

        String registro= senha + " - " + hora;

        try {
            // Criando um FileWriter no modo de append (para não sobrescrever o arquivo)
            FileWriter escritorArquivo = new FileWriter("BDsenhas.txt", true); // true para adicionar ao final do arquivo
            escritorArquivo.write(registro + "\n"); // Escreve a senha e adiciona uma nova linha
            escritorArquivo.close(); // Fecha o arquivo após escrever
            System.out.println("");
            System.out.println(" > Sucesso!");
            System.out.println(" > Sua senha é: "+senha);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar a senha!");
            System.out.print("Descrição: "); e.printStackTrace(); }
    return seque; }}
