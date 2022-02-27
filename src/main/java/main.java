import com.samoylenko.alexander.Converter;

import java.io.*;

public class main {
    public static void main(String[] args) throws IOException {

        DataReader datesFromFile = new DataReader();
        datesFromFile.fileRead();

        Converter converter = new Converter();
        converter.getImplementationInfo();

        System.out.println(converter.convert(datesFromFile.dates));

    }
}