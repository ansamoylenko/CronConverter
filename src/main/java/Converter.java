import Time.Minute;
import Time.Hour;
import Time.Day;
import Time.Month;
import Time.WeekDay;
import Time.Result;

import Time.Time;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

interface DatesToCronConverter
{
    public String convert(List<Calendar> dates);
    public void getImplementationInfo();
}

public class Converter implements DatesToCronConverter
{
    //Метод convert() на вход принимает список дат (порядок не важен) формата (“yyyy-MM-dd'T’HH:mm:ss”), на выход строка вида “0 * * * * MON”.

    @Override
    public String convert(List<Calendar> dates)
    {
        ArrayList<Integer> minutes = new ArrayList<>();
        ArrayList<Integer> hours = new ArrayList<>();
        ArrayList<Integer> days = new ArrayList<>();
        ArrayList<Integer> months = new ArrayList<>();
        ArrayList<Integer> weekDays = new ArrayList<>();

        for(Calendar date: dates)
        {
            minutes.add(date.get(Calendar.MINUTE));
            hours.add(date.get(Calendar.HOUR));
            days.add(date.get(Calendar.DAY_OF_MONTH));
            months.add(date.get(Calendar.MONTH)+1);
            weekDays.add(date.get(Calendar.DAY_OF_WEEK));
        }

        Time cronMinute = new Minute(minutes.stream().mapToInt(i->i).toArray());
        Time cronHour = new Hour(hours.stream().mapToInt(i->i).toArray());
        Time cronDay = new Day(days.stream().mapToInt(i->i).toArray());
        Time cronMonth = new Month(months.stream().mapToInt(i->i).toArray());
        Time cronWeekDay = new WeekDay(weekDays.stream().mapToInt(i->i).toArray());

        //"0 0/30 8-9 * * *"

        Time[] mas = new Time[5];

        mas[0] = cronMinute;
        mas[1] = cronHour;
        mas[2] = cronDay;
        mas[3] = cronMonth;
        mas[4] = cronWeekDay;

        String result  = "";

        boolean flag = false;

        for(int i = 4; i >= 0; i--)
        {
            String res = Check(mas[i]);

            if (res =="" || flag) result = "*" + result;
            else if(!flag)
            {
                result = res + result;
                flag = true;
            }

            result = " " + result;





        }


        //return  result;

        return "0 " + Check(cronMinute) + " " + Check(cronHour) + " " + Check(cronDay) + " " + Check(cronMonth) + " "+ Check(cronWeekDay);
        //return "0 " + Check(cronMinute) + " " + Check(cronHour) + " " + Check(cronDay) + " " + Check(cronMonth) + " "+ Check(cronWeekDay);
        //return "+";
    }

    public String Check(Time time)
    {
        Result regularity= time.isRegularity();
        Result range = time.isRange();
        Result constant = time.isConstant();

        if(constant.getFlag()) return constant.toString();
        else if(range.getFlag()) return range.toString();
        else if(regularity.getFlag()) return regularity.toString();
        else return "*";
    }

    @Override
    public void getImplementationInfo()
    {
        // ФИО, имя класса реализации, пакет, ссылка на github
        System.out.println("Самойленко Александр Николаевич, class: " + this.getClass().getSimpleName() + " https://github.com/ansamoylenko");
    }
}

