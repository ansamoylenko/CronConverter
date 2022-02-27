import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import Exception.DatesToCronConvertException;

public class DataReader
{
    BufferedReader reader;
    ArrayList<Calendar> dates;

    public DataReader() throws FileNotFoundException
    {
        dates = new ArrayList<>();
        File file = new File("src/main/resources/dates");

        //создаем объект FileReader для объекта File
        FileReader fr = new FileReader(file);

        //создаем BufferedReader с существующего FileReader для построчного считывания
        reader = new BufferedReader(fr);
    }

    public void fileRead() throws IOException
    {
        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
        String cronFormat = "mm HH dd MM EEE";

        DateFormat df = new SimpleDateFormat(dateFormat);
        DateFormat cf = new SimpleDateFormat(cronFormat, Locale.ENGLISH);
        Calendar dates = new GregorianCalendar();

        // считываем сначала первую строку
        String line = reader.readLine();

        while (line != null)
        {
            try
            {
                dates.setTime(df.parse(line.replace("\"", "")));
            }
            catch (Exception e)
            {
                new DatesToCronConvertException("Parsing error in line");
                line = reader.readLine();
                continue;
            }

            line = reader.readLine();
        }
    }

}
