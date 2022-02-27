import Time.Day;
import Time.Minute;
import Time.Month;

import java.io.*;

public class main {
    public static void main(String[] args) throws IOException {

        DataReader datesFromFile = new DataReader();
        datesFromFile.fileRead();

        System.out.println(datesFromFile.dates.size());

        Converter converter = new Converter();
        converter.getImplementationInfo();
        System.out.println(converter.convert(datesFromFile.dates));


        Day newDay = new Day(new int[]{1,2,3,4,5,6,7,8,9,10});
        Day constDay = new Day(new int[]{7,7,7,7});

        Month perDay = new Month(new int[]{1,4,7,10});



    }


}