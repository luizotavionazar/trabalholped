import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.*;

public class SenhasBancaria {

    public void menu(Queue<String> fila) {
        System.out.println("");
        System.out.println("─────────────────────────");
        System.out.println("   CONTROLE DE SENHAS    ");
        System.out.println("─────────────────────────");
        System.out.println(" > [1] Gerar senha       ");

        if (!fila.isEmpty()) { //Alterar condição após criar a fila certa
            System.out.println(" > [2] Chamar senha      ");
            System.out.println(" > [3] Ver senhas        ");
            System.out.println(" > [4] Buscar senha      ");
            System.out.println(" > [5] Limpar BD         "); }

        System.out.println(" > [0] Sair              ");
        System.out.println("─────────────────────────");
        System.out.println("");
        System.out.print("Escolha a opção desejada: "); 
    }

    public boolean verificar_acesso(int opc, Queue<String> fila){ //Alterar os parâmetros após criar as variaveis necessárias
    if (fila.isEmpty()) { //Alterar condição após criar a fila certa
        if (opc==2||
            opc==3||
            opc==4||
            opc==5) {
            System.out.println("");
            System.out.println("Necessário gerar uma senha primeiro!");
            return false; }}
    return true; }

    public static void main(String[] args) throws Exception {
        String caminhoarquivo= "BDsenhas.txt";
        File arquivo = new File(caminhoarquivo);
        try {
            if (arquivo.createNewFile()){
                FileWriter escreveArquivo = new FileWriter("BDsenhas.txt", true); // true para adicionar ao final do arquivo
                escreveArquivo.write("SENHA, CPF/CNPJ, DATA GERAÇÃO, SE FOI CHAMADA: 0- NÃO 1- SIM\n────────────────────────────────────────────────────────────\n"); // Escreve a senha e adiciona uma nova linha
                escreveArquivo.close(); // Fecha o arquivo após escrever
            }
        } catch (IOException e) {
            e.printStackTrace();}

        Scanner in= new Scanner(System.in);       
        SenhasBancaria metodo= new SenhasBancaria();
        GerarSenha metodo1= new GerarSenha();
        Queue<String> fila= new LinkedList<>();

        boolean control= true, control1= true;
        int opc= 0, seque = 0;

        while (control) {

        do {
            metodo.menu(fila);
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
            case 0:
                control= false;
                break;
            case 1:
                seque= metodo1.gerar_senha(in, seque);
                break;
            case 2:
    
                if (metodo.verificar_acesso(opc, fila)) {
                    }
    
                break;
            case 3:
    
                if (metodo.verificar_acesso(opc, fila)) {
                    }
    
                break;
            case 4:
    
                if (metodo.verificar_acesso(opc, fila)) {
                    }
    
                break;
            case 5:

                if (metodo.verificar_acesso(opc, fila)) {
                    }

            break;
            default:
                System.out.println("");
                System.out.println("Opção inválida!");
                break;  }}

    in.close();}}