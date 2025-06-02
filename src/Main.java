import algoritmos.Algoritmos;
import utils.AlgoritmoConstrutor;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Main{
    public static void main(String[] args) throws ParseException {
        String caminhoArquivo = "resource.txt";
        AlgoritmoConstrutor algoritmoConstrutor = new AlgoritmoConstrutor();
        algoritmoConstrutor.leitorArquivo(caminhoArquivo);

        Algoritmos algoritmos = new Algoritmos(algoritmoConstrutor);
        algoritmos.executar();

    }
}