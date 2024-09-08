import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Scanner;

public class SenhasBancaria {
    public void menu() {
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

    public void adicionar_senha() {

    }

    public void remover_senha() { //subscrever não é a mesma coisa de substituir

    }

    public void listar_senhas() {

    }

    public void pesquisar_senha() {

    }

    public void limpa_arquivo() {

    }

    public boolean verificar_acesso(int opc){ //Alterar os parâmetros após criar as variaveis necessárias
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
    Scanner in= new Scanner(System.in);       
    SenhasBancaria metodo= new SenhasBancaria();
    
    boolean control= true, control1= true;
    int opc= 0;

    while (control) {

        do {
            metodo.menu();
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
                metodo.adicionar_senha();
                break;
            case 2:
    
                if (metodo.verificar_acesso(opc)) {
                    metodo.remover_senha();}
    
                break;
            case 3:
    
                if (metodo.verificar_acesso(opc)) {
                    metodo.listar_senhas();}
    
                break;
            case 4:
    
                if (metodo.verificar_acesso(opc)) {
                    metodo.pesquisar_senha();}
    
                break;
            case 5:

                if (metodo.verificar_acesso(opc)) {
                    metodo.limpa_arquivo();}

            break;
            default:
                System.out.println("");
                System.out.println("Opção inválida!");
                break;  }}

    in.close();}
}