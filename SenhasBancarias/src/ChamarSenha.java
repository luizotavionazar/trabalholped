import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ChamarSenha {
    public void chamar_senha(Queue<String> fila){
        Queue<String> filaPR = new LinkedList<>();//fila auxiliar para administrar aas prioridades das senhas
        Queue<String> filaCL = new LinkedList<>();//fila auxiliar para administrar aas prioridades das senhas
        Queue<String> filaNC = new LinkedList<>();//fila auxiliar para administrar aas prioridades das senhas

        for (String senha : fila) {//retira da fila principal e reparte entre as filas auxiliáreis de acordo com a prioridade
            if (senha.startsWith("PR")) {
                filaPR.offer(senha);
            } else if (senha.startsWith("CL")) {
                filaCL.offer(senha);
            } else if (senha.startsWith("NC")) {
                filaNC.offer(senha);
            }
        }

        String senha = null;
        if (!filaPR.isEmpty()) {//separa a senha mais prioritária e armazena na variável
            senha = filaPR.poll();
        } else if (!filaCL.isEmpty()) {
            senha = filaCL.poll();
        } else if (!filaNC.isEmpty()) {
            senha = filaNC.poll();
        }

        fila.clear(); //limpa a fila principal e insere novamente as senhas em ordem prioritária e numérica
        fila.addAll(filaPR);
        fila.addAll(filaCL);
        fila.addAll(filaNC);

        if (senha != null) {//impressão das senhas 
            atualizarBD(senha);
            System.out.println("SENHA: > " + senha);
        } else {
            System.out.println("Nenhuma senha na fila.");
        }
    }
        private void atualizarBD(String senha){//metodo para atualzar o BD, com a alteração de 0 para 1 ao final da linha quando a senha é chamada
        String caminhoArquivo="BDsenhas.txt";
        List<String> linhas = new ArrayList<>();
        String naochamada = "0", chamada = "1";

        try(BufferedReader chamarsenha = new BufferedReader(new FileReader(caminhoArquivo))) {//Varredura das linhas do BD
            String linha;
            while ((linha = chamarsenha.readLine())!= null) {
                if(linha.startsWith(senha)){//Etapa de alteração das linhas
                    linha= linha.replaceFirst(",\\s*"+naochamada+"\\s*$", ", " + chamada);
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(BufferedWriter escreversenha = new BufferedWriter (new FileWriter(caminhoArquivo))){//escreve de volta no BD com a alteração
            for (String linha : linhas) {
                escreversenha.write(linha);
                escreversenha.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}