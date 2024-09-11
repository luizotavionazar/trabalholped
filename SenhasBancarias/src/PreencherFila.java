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
    DataHora dataHoraUtil = new DataHora();  // Instância da sua classe DataHora
    LocalDateTime agora = LocalDateTime.now(); // Data e hora atuais
    LocalDateTime umDiaAtras = agora.minusDays(1); // Subtrai 24 horas

    try (BufferedReader ler = new BufferedReader(new FileReader(caminhoArquivo))) { // Lê o arquivo de senhas
        String linha;

        while ((linha = ler.readLine()) != null) { // Lê cada linha do arquivo
            
            if (linha.matches("[A-Za-z]{2}\\d{2}, \\w*, \\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}, [01]")) { 
                // Divide a linha nos campos: senha, CPF, data/hora, e se foi chamada ou não
                String[] campos = linha.split(", ");
                String senha = campos[0];
                String dataHoraStr = campos[2];
                int foiChamada = Integer.parseInt(campos[3]);

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

    //public Queue<String> fila(Queue<String> fila){
    //    String caminhoArquivo= "BDsenhas.txt";
    //    try(BufferedReader ler = new BufferedReader(new FileReader(caminhoArquivo))) { //Varre as senhas gravadas no BD, para assim carregar a fila atual
    //        String linha= null, ultimaLinha = null, ultimoRegistro= null;
    //        char ultimoCaractere= 0;
    //        
    //            while((linha= ler.readLine()) != null){ //Verifica se a tupla esta vazia
    //                ultimaLinha= linha;}
    //            if (ultimaLinha!= null) { //Captura ultimo caractere para verificar se a senha já foi chamada
    //                ultimoCaractere= ultimaLinha.charAt(ultimaLinha.length()-1); }
    //                
    //            if (ultimaLinha.matches("[A-Za-z]{2}\\d{2}, \\w+, \\d{2}/\\d{2}/\\d{4}, \\d{2}:\\d{2}:\\d{2} .*")) { //Captura da Data/Hora do último registro da Sacola
    //                String[] campos= ultimaLinha.split(", "); //Divide a linha em campos, usando a vírgula como separador (0 = senha, 1 = CPF, 2 = data, 3= hora)
    //                ultimoRegistro= campos[2]; }
    //            
    //            if (ultimoCaractere=='0') { //Adiciona na fila se a senha não foi chamad
    //                fila.add(senha); }} //CORRIGIR PREENCHIMENTO DA FILA
    //    
    //    return fila;
    //}

    

}
