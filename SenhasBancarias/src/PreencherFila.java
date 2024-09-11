import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;

public class PreencherFila {

    // Método para preencher a fila com senhas geradas nas últimas 24 horas e não chamadas
    public void recebeFila(Queue<String> fila) {
        String caminhoArquivo = "BDsenhas.txt";
        LocalDateTime agora = LocalDateTime.now(); // Data e hora atuais
        LocalDateTime umDiaAtras = agora.minusDays(1); // Subtrai 24 horas
        
        try (BufferedReader ler = new BufferedReader(new FileReader(caminhoArquivo))) { // Lê o arquivo de senhas
            String linha;
    
            while ((linha = ler.readLine()) != null) { // Lê cada linha do arquivo
                
                if (linha.matches("[A-Za-z]{2}\\d{2}, \\w*, \\d{2}/\\d{2}/\\d{4}, \\d{2}:\\d{2}:\\d{2}, [01]")) { 
                    // Divide a linha nos campos: senha, CPF, data/hora, e se foi chamada ou não
                    String[] campos = linha.split(", ");
                    String senha = campos[0];
                    String dataStr = campos[2];
                    String horaStr = campos[3];
                    int foiChamada = Integer.parseInt(campos[4]);

                    // Junta data e hora em uma única string
                    String dataHoraStr = dataStr + " " + horaStr;
    
                    // Converte a data e hora da string para LocalDateTime
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime dataHoraRegistro = LocalDateTime.parse(dataHoraStr, formatter);
                    
                    // Verifica se a senha foi gerada nas últimas 24 horas e se ainda não foi chamada
                    if (dataHoraRegistro.isAfter(umDiaAtras) && foiChamada == 0) {
                        fila.add(senha); // Adiciona a senha à fila
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    