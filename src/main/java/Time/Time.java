package Time;

import Exception.DatesToCronConvertException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.IntStream;

public abstract class Time
{
    public Time(int[] values)
    {
        this.values = values;
    }

    protected int valueMin;
    protected int valueMax;
    int[] values;

    public Result isConstant()
    {
        for (int value: values)
        {
            if (value != values[0]) return new Result(false, "");
        }
        return new Result(true, String.valueOf(values[0]));
    }

    public Result isRange()
    {
        // Оставляем только уникальные значения
        HashSet<Integer> uniqueValues = new HashSet<>();
        for(int value: values)
        {
            uniqueValues.add(value);
        }

        int max = Collections.max(uniqueValues);
        int min = Collections.min(uniqueValues);

        if (max > valueMax || min < valueMin) new DatesToCronConvertException("Ошибка валидации");

        // Если число уникальных значений не равно числу элементов предпологаемого диапозона, следовательно диапозон неполный
        if (uniqueValues.size() != ( max - min + 1))
        {
            return new Result(false, "");
        }
        return new Result(true, min + "-" + max );
    }

    public Result isRegularity()
    {
        values = IntStream.of(values).distinct().toArray();
        Arrays.sort(values);

        if(values.length == 1) return new Result(false, "");

        int dif = values[1] - values[0];
        int numberOfElements = (valueMax - valueMin)/dif + 1;

        for( int i = 0; i < values.length-1; i++)
        {
            if (((values[i+1] - values[i]) != dif) | (numberOfElements != values.length))
            {
                return new Result(false, "");
            }
        }
        return new Result(true,values[0] + "/" + dif);
    }

}
