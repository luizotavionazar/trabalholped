import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Gerarsenha {
    public void gerarsenha(int senha){
        senha++;
        LocalDateTime horario = LocalDateTime.now();
        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horariop = horario.format(formatacao);
        String senhacomhorario = senha+" - "+horariop;
        try {
            // Criando um FileWriter no modo de append (para não sobrescrever o arquivo)
            FileWriter escritorArquivo = new FileWriter("BDsenhas.txt", true); // true para adicionar ao final do arquivo
            escritorArquivo.write(senhacomhorario + "\n"); // Escreve os dados e uma nova linha
            escritorArquivo.close(); // Fecha o arquivo após escrever
            System.out.println("Dado inserido com sucesso!");
            System.out.println("Senha gerada: "+senhacomhorario);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }
}
