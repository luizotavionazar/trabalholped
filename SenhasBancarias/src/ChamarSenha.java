import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ChamarSenha {
    public void chamar_senha(Queue<String> fila){
        String caminhoArquivo="BDsenhas.txt";
        List<String> linhas = new ArrayList<>();
        String senha;
        String naochamada = "0", chamada = "1";
        senha=fila.poll();

        try(BufferedReader chamarsenha = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = chamarsenha.readLine())!= null) {
                if(linha.startsWith(senha)){
                    linha= linha.replaceFirst(",\\s*"+naochamada+"\\s*$", ", " + chamada);
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(BufferedWriter escreversenha = new BufferedWriter (new FileWriter(caminhoArquivo))){
            for (String linha : linhas) {
                escreversenha.write(linha);
                escreversenha.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("SENHA: > "+senha);
    }
}