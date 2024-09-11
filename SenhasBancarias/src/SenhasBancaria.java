import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.*;

public class SenhasBancaria {
    public void menu(Queue<String> fila) { //Visualização do Menu
        System.out.println("");
        System.out.println("─────────────────────────");
        System.out.println("   CONTROLE DE SENHAS    ");
        System.out.println("─────────────────────────");
        System.out.println(" > [1] Gerar senha       ");

        if (!fila.isEmpty()) { //Exibe as opções apenas quando existe senha gerada
            System.out.println(" > [2] Chamar senha      ");
            System.out.println(" > [3] Ver senhas        ");
            System.out.println(" > [4] Alterar senha     ");
            System.out.println(" > [5] Limpar BD         ");}

        System.out.println(" > [0] Sair              ");
        System.out.println("─────────────────────────");
        System.out.println("");
        System.out.print("Escolha a opção desejada: "); }

    public boolean verificar_acesso(int opc, Queue<String> fila){ //Verifica se existe senhas na fila, para utilizar as opções 2 a 5 do menu
    if (fila.isEmpty()) {
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
        GerarSenha gerar= new GerarSenha();
        ChamarSenha chamar= new ChamarSenha();
        ListarSenha listar= new ListarSenha();
        LimparTerminal cls= new LimparTerminal();
        AlterarSenha alterar= new AlterarSenha();
        Queue<String> fila= new LinkedList<>();
        PreencherFila capturaFila = new PreencherFila();

        boolean control= true, control1= true;
        int opc= 0, seque=0;

        String caminhoArquivo= "BDsenhas.txt";
        File arquivo = new File(caminhoArquivo);
        try { //Na 1º execução, cria o Banco de Dados das senhas, incluindo uma orientação de como o usuário lê o arquivo
            if (arquivo.createNewFile()){ 
                FileWriter escreveArquivo = new FileWriter("BDsenhas.txt", true);
                escreveArquivo.write("SENHA, CPF/CNPJ, DATA GERAÇÃO, SE FOI CHAMADA: 0- NÃO 1- SIM\n────────────────────────────────────────────────────────────\n");
                escreveArquivo.close(); }
        } catch (IOException e) {
            e.printStackTrace();}
        
        try(BufferedReader ler = new BufferedReader(new FileReader(caminhoArquivo))){ //Verifica última senha gravada no BD, para assim atualizar a sequência
            String linha= null;
            String ultimaLinha = null;

            while((linha= ler.readLine()) != null){ //Verifica se a tupla esta vazia
                ultimaLinha= linha;}

            if (ultimaLinha != null){
                if (ultimaLinha.matches("[A-Za-z]{2}\\d{2}.*")) { //Ignora os 2 primeiros caracteres do registro, captura o 3º e 4º para obter a sequência e ignora o resto
                    String ultimoNum= ultimaLinha.substring(2, 4);
                    seque=Integer.parseInt(ultimoNum); }}}

        capturaFila.recebeFila(fila);// Preenche a fila com senhas do último dia que não foram chamadas
        for (String elemento : fila) {
            System.out.println(elemento);
        }
        
        while (control) { //Núcleo do programa, chamada das funcionalidades

            do { //Entrada com teste de preenchimento inteiro
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
            
            cls.limpar_tela();
            System.out.println("");
            switch (opc) {
                case 0:
                    control= false;
                    break;
                case 1:
                    seque= gerar.gerar_senha(in, seque, fila);
                    break;
                case 2:
            
                    if (metodo.verificar_acesso(opc, fila)) {
                        chamar.chamar_senha(fila); }

                    break;
                case 3:

                    if (metodo.verificar_acesso(opc, fila)) {
                        listar.listar_senha(in); }    

                    break;
                case 4:

                if (metodo.verificar_acesso(opc, fila)) {
                    alterar.alterarSenha(in); }

                    break;
                case 5:
                                            
                    if (metodo.verificar_acesso(opc, fila)) {
                        try (FileWriter writer = new FileWriter(caminhoArquivo, false)) { //Limpa o arquivo de texto
                        seque=0;
                        } catch (IOException e) {
                            e.printStackTrace();}
                            FileWriter escreveArquivo = new FileWriter("BDsenhas.txt", true); //Adiciona o cabeçalho do arquivo de texto
                            escreveArquivo.write("SENHA, CPF/CNPJ, DATA GERAÇÃO, SE FOI CHAMADA: 0- NÃO 1- SIM\n────────────────────────────────────────────────────────────\n");
                            escreveArquivo.close();

                        while (fila.size()>0) {
                            fila.poll(); }}
                    
                break;
                default:
                    System.out.println("");
                    System.out.println("Opção inválida!");
                    break;  }}
    in.close();}}