import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataHora {
    public String horario(String dataHora){
        LocalDateTime horario= LocalDateTime.now(); //Captura da Data e Hora
        DateTimeFormatter data= DateTimeFormatter.ofPattern("dd/MM/YYYY");
        DateTimeFormatter hora= DateTimeFormatter.ofPattern("HH:mm:ss");
        String dataHoje= horario.format(data);
        String horaAgora= horario.format(hora);
        dataHora= dataHoje+", "+horaAgora;
        return dataHora;}}