import Time.Minute;
import Time.Hour;
import Time.Day;
import Time.Month;
import Time.WeekDay;
import Time.Result;

import Time.Time;

import java.util.ArrayList;
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
            months.add(date.get(Calendar.MONTH));
            weekDays.add(date.get(Calendar.DAY_OF_WEEK));
        }

        //for(Calendar date: dates) System.out.println(date.getTime());
        //for(Integer minute: minutes) System.out.println(minute);


        Time cronMinute = new Minute(minutes.stream().mapToInt(i->i).toArray());
        Time cronHour = new Hour(hours.stream().mapToInt(i->i).toArray());
        Time cronDay = new Day(days.stream().mapToInt(i->i).toArray());
        Time cronMonth = new Month(months.stream().mapToInt(i->i).toArray());
        Time cronWeekDay = new WeekDay(weekDays.stream().mapToInt(i->i).toArray());


        return Check(cronHour);


    }

    public String Check(Time time)
    {
        Result regularity= time.isRegularity();
        Result range = time.isRange();
        Result constant = time.isConstant();

        if(regularity.getFlag()) return regularity.toString();
        else if(range.getFlag()) return range.toString();
        else if(constant.getFlag()) return constant.toString();
        else return " * ";
    }

    @Override
    public void getImplementationInfo()
    {
        System.out.println("Самойленко Александр Николаевич, " + this.getClass().getSimpleName() + " git: ");
    }
}
    //реализации интерфейса (ФИО, имя класса реализации, пакет, ссылка на github).
